package com.vsf.dashboardImpl.dao

import com.vsf.dashboardImpl.model._

import scala.concurrent.Future

trait DAO {

  def startup():Unit

  def getAllEmployees: Future[Seq[Empleado]]

}
