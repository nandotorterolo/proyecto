package com.vsf.dashboardImpl.dao.firebase

import java.io.FileInputStream

import com.seancheatham.storage.firebase.FirebaseDatabase
import com.vsf.dashboardImpl.dao.DAO
import com.vsf.dashboardImpl.model.Empleado
import com.vsf.util.UsesLogger
import scala.concurrent.ExecutionContext.Implicits.global
import com.vsf.dashboardImpl.dao.firebase.JsonImplicit._
import scala.concurrent.Future

/**
  * Created by user on 5/6/17.
  */
class FirebaseDao extends DAO with UsesLogger{

  private val file = getClass.getResource("/firebaseServiceAccountKey.json")
  private val serviceAccount = new FileInputStream(file.getFile)
  val db: FirebaseDatabase = FirebaseDatabase.fromServiceAccountKey(serviceAccount, "https://proyecto-858b4.firebaseio.com")


  def startup():Unit = {
    db.database.goOnline()
  }

  override def getAllEmployees: Future[Seq[Empleado]] = {
    db.getCollection("empleados").map(_.flatMap(_.validate[Empleado].asOpt).toList)
  }
}
