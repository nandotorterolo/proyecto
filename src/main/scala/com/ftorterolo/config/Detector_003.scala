package com.ftorterolo.config

import java.util.concurrent._

import com.ftorterolo.util.JsonUtil
import org.slf4j.LoggerFactory

class Detector_003  {

  val logger = LoggerFactory.getLogger(this.getClass)
  val detector_D003 = Executors.newSingleThreadScheduledExecutor()
  val vector_D003:Array[Int] = Array(0,0,0,0)

  def runnable= new Runnable() {
    override def run(): Unit = {

      val r = scala.util.Random
      val autosR = r.nextInt(10)
      val motosR = r.nextInt(10)
      val omnibusR = r.nextInt(10)

      //vector are 0-based
      vector_D003.update(2,vector_D003(2)+1)

      val trafico = Map(Transportes.Auto.id -> autosR, Transportes.Moto.id -> motosR, Transportes.Omnibus.id -> omnibusR )

      val d3_to_d1 = Mensaje(emisor=Detectores.D003.id, receptor=Detectores.D001.id,trafico)
      val d3_to_d2 = Mensaje(emisor=Detectores.D003.id, receptor=Detectores.D002.id,trafico)
      val d3_to_d4 = Mensaje(emisor=Detectores.D003.id, receptor=Detectores.D004.id,trafico)

      val lamport = JsonUtil.toJson(vector_D003)
      MessageHandler.sendMessage(JsonUtil.toJson(d3_to_d1), lamport)
      MessageHandler.sendMessage(JsonUtil.toJson(d3_to_d2), lamport)
      MessageHandler.sendMessage(JsonUtil.toJson(d3_to_d4), lamport)

//      println(s"$lamport")
    }
  }

  def start(initialDelay: Long, period: Long, unit: TimeUnit) = {
    detector_D003.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS)
  }

  def shutdown() = detector_D003.shutdown()

}