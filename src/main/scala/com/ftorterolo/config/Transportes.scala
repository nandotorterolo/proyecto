package com.ftorterolo.config

import com.ftorterolo.config.Barrios.Barrio
import com.ftorterolo.config.Calles.Calle
import com.ftorterolo.config.Detectores.Detector
import com.ftorterolo.config.Transportes.Vehiculo
// import com.ftorterolo.util.EnumerationMacros._

/**
  * http://underscore.io/blog/posts/2014/09/03/enumerations.html
  * https://github.com/d6y/enumeration-examples/blob/master/macros/src/main/scala/EnumerationMacros.scala
  */

object  MensajeExpandido {
  def apply(mensaje: Mensaje): MensajeExpandido = {
    val trafico = mensaje.trafico.map{case (transporte, cant) => (Transportes(transporte),cant)}
    MensajeExpandido(Detectores(mensaje.emisor),Detectores(mensaje.receptor), trafico)
  }
}
case class Mensaje(emisor:Detectores.ID, receptor:Detectores.ID, trafico:Map[Transportes.ID,Int])
case class MensajeExpandido(emisor:Detector, receptor:Detector,trafico:Map[Vehiculo,Int])

object Transportes {

  type ID = Int
  def apply(id: Int) :Vehiculo = transportes.find(_.id == id).getOrElse(Transportes.Auto)

  val transportes: Set[Vehiculo] = Set(Auto,Moto,Escuela,Omnibus)

  sealed abstract class Vehiculo(val id: ID, nombre:String, maxPasajeros:Int) {
      override def toString = nombre
  }
  case object Auto extends Vehiculo(1,"Auto",5)
  case object Moto extends Vehiculo(2,"Moto",2)
  case object Escuela extends Vehiculo(3,"Escuela",15)
  case object Omnibus extends Vehiculo(4,"Omnibus",40)
}

object Detectores {

  type ID = Int

  def apply(id: Int): Detector = detectores.find(_.id == id).getOrElse(Detectores.D001)

  val detectores: Set[Detector] =  Set(D001,D002,D003,D004) //sealedInstancesOf[Detector]

  sealed abstract class Detector(val id:ID, nombre:String, barrio:Barrio, calle1: Calle, calle2: Calle, numCarril:Int) {
    override def toString = id + nombre
  }
  case object D001 extends Detector(1,"D001", Barrios.Colon, Calles.Garzon, Calles.Lezica,1)
  case object D002 extends Detector(2,"D002", Barrios.Colon, Calles.Garzon, Calles.Lezica,2)
  case object D003 extends Detector(3,"D003", Barrios.Colon, Calles.Garzon, Calles.Lezica,3)
  case object D004 extends Detector(4,"D004", Barrios.Colon, Calles.Garzon, Calles.Lezica,4)
}

object Barrios {

  sealed abstract class Barrio(nombre:String) {
    override def toString = nombre
  }
  case object Colon extends Barrio("COLON")
}

object Calles {
  import com.ftorterolo.config.TipoCalles.TipoCalle

  sealed abstract class Calle(nombre:String, tipo:TipoCalle) {
    override def toString = nombre
  }
  case object Garzon extends Calle("Garzon", TipoCalles.Avda)
  case object Lezica extends Calle("Lezica", TipoCalles.Avda)
  case object BattleOrdonies extends Calle("Batlle y Ordoñes", TipoCalles.Blvd)
}

object TipoCalles {

  sealed abstract class TipoCalle(tipo:String) {
    override def toString = tipo
  }
  case object Avda extends TipoCalle("Avda")
  case object Blvd extends TipoCalle("Blvd")
}