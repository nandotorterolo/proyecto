package com.vsf.dashboardImpl.dao.firebase

import com.vsf.dashboardImpl.model.{Empleado, Roles}
import play.api.libs.json.{JsPath, Reads}
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

/**
  * Json implicit converters DAO -> Model application
  * be careful with initialization order problem.
  * @see https://www.playframework.com/documentation/2.5.x/ScalaJson
  * @see http://stackoverflow.com/questions/29279955/playframework-json-parsing-null-pointer-exception-when-array-is-present
  */
object JsonImplicit {


  implicit val rolesReads : Reads[Roles]=(
    (JsPath \ "supervisor").readNullable[Boolean] and
      (JsPath \ "operador").readNullable[Boolean]
    )(Roles.apply _)

  implicit val empleadoReads: Reads[Empleado] = (
    (JsPath \ "nombre").read[String]  and
      (JsPath \ "apellido").read[String] and
      (JsPath \ "roles").read[Roles]
    )(Empleado.apply _)

}
