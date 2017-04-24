package com.vsf.dashboardImpl.dao.config

import com.vsf.util.UsesLogger

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Try


// TODO down level of logger, setup a startup and shoutdown application
object InitDbConfiguration extends App with DbConfiguration with UsesLogger{
  import scala.concurrent.ExecutionContext.Implicits.global

  val employs = new EmploysRepository(config)

  def log(operation: String, res: Try[Unit]) = {
    if (res.isSuccess)
      logger.info(s"$operation, ok")
    else
      logger.warn(s"$operation, sth was wrong!")
  }

  val f = for {
    // Table 'USERS' already exists
    _ <- employs.drop().andThen {case res: Try[Unit]=> log("drop", res)}
    _ <- employs.create().andThen {case res: Try[Unit] => log("create", res)}

  } yield ()

  Await.result(f, Duration.Inf)
  logger.info("Finish InitDbConfiguration")

}
