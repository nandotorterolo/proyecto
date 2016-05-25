package com.ftorterolo.config

import com.ftorterolo.util.JsonUtil

object TestRandom extends App {

  val r = scala.util.Random

  val autosR = r.nextInt(10)
  val motosR = r.nextInt(10)
  val omnibusR = r.nextInt(10)

  val m1 = Mensaje(emisor=Detectores.D001.id, receptor=Detectores.D002.id,
    Map( Transportes.Auto.id -> autosR, Transportes.Moto.id -> motosR)
  )
  val json = JsonUtil.toJson(m1)

  val m2 = JsonUtil.fromJson[Mensaje](json)
  println(m2)  // Mensaje(1,2,Map(5 -> 1, 2 -> 2))
//  println(MensajeExpandido(m2))  // MensajeExpandido(1D001,2D002,Map(5 -> Auto, 2 -> Moto))
  println(JsonUtil.toJson(json))  // MensajeExpandido(1D001,2D002,Map(5 -> Auto, 2 -> Moto))


  val calle  = JsonUtil.toJson(Calles.Garzon)
  val tipoCalle = JsonUtil.toJson(TipoCalles.Avda)
  val barrio = JsonUtil.toJson(Barrios.Colon)
  val detector = JsonUtil.toJson(Detectores.D001)
  val transporte = JsonUtil.toJson(Transportes.transportes.head)


  val lamport:Array[Int] = Array(0,0,0,0)
  val jsonLamport = JsonUtil.toJson(lamport) // [0,0,0,0]

  val lam = JsonUtil.fromJson[(Int,Int,Int,Int)]("[0,0,0,0]")
  println(lam)

  //  println(transporte)

}
