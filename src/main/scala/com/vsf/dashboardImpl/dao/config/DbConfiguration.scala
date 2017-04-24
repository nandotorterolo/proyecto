package com.vsf.dashboardImpl.dao.config

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

trait Db {
  val config: DatabaseConfig[JdbcProfile]
  val db: JdbcProfile#Backend#Database = config.db
}

trait DbConfiguration {
  lazy val config = DatabaseConfig.forConfig[JdbcProfile]("areasegura.db")
}

// https://codequs.com/p/B1IogRLY/scala-tutorial-create-crud-with-slick-and-mysql/
trait EmploysTable { this: Db =>
  import config.profile.api._

  case class Employ(id: Option[Int], email: String, firstName: Option[String], lastName: Option[String])

  // TODO configure case sensitive mysql
  class Employs(tag: Tag) extends Table[Employ](tag, "USERS") {
    // Columns
    def id = column[Int]("USER_ID", O.PrimaryKey, O.AutoInc)
    def email = column[String]("USER_EMAIL", O.Length(512))
    def firstName = column[Option[String]]("USER_FIRST_NAME", O.Length(64))
    def lastName = column[Option[String]]("USER_LAST_NAME", O.Length(64))

    // Indexes
    def emailIndex = index("USER_EMAIL_IDX", email, unique=true)

    // Select
    def * = (id.?, email, firstName, lastName) <> (Employ.tupled, Employ.unapply)
  }
  val employs = TableQuery[Employs]
}

class EmploysRepository(val config: DatabaseConfig[JdbcProfile]) extends Db with EmploysTable {
  import slick.dbio.DBIOAction
  import config.profile.api._

  def create() = db.run(DBIOAction.seq(employs.schema.create))
  def drop() = db.run(DBIOAction.seq(employs.schema.drop))
}

