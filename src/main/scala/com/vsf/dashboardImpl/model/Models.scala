package com.vsf.dashboardImpl.model


/**
  * TODO Definir si el modelo en firebase estará en español o ingles.
  */

case class Employ(name:String, surname: String, socialId: String)

case class Empleado(nombre: String, apellido:String, roles:Roles)
case class Roles(supervisor: Option[Boolean], operador: Option[Boolean])


