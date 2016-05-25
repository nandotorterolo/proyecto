package com.ftorterolo.config

import java.util.concurrent._

import com.ftorterolo.util.JsonUtil
import org.slf4j.LoggerFactory

class Detector_002  {

  val logger = LoggerFactory.getLogger(this.getClass)
  val detector_D002 = Executors.newSingleThreadScheduledExecutor()
  val vector_D002:Array[Int] = Array(0,0,0,0)

  def runnable= new Runnable() {
    override def run(): Unit = {

      val r = scala.util.Random
      val autosR = r.nextInt(10)
      val motosR = r.nextInt(10)
      val omnibusR = r.nextInt(10)

      //vector are 0-based
      vector_D002.update(1,vector_D002(1)+1)

      val trafico = Map(Transportes.Auto.id -> autosR, Transportes.Moto.id -> motosR, Transportes.Omnibus.id -> omnibusR )

      val d2_to_d1 = Mensaje(emisor=Detectores.D002.id, receptor=Detectores.D001.id,trafico)
      val d2_to_d3 = Mensaje(emisor=Detectores.D002.id, receptor=Detectores.D003.id,trafico)
      val d2_to_d4 = Mensaje(emisor=Detectores.D002.id, receptor=Detectores.D004.id,trafico)

      val lamport = JsonUtil.toJson(vector_D002)
      MessageHandler.sendMessage(JsonUtil.toJson(d2_to_d1), lamport)
      MessageHandler.sendMessage(JsonUtil.toJson(d2_to_d3), lamport)
      MessageHandler.sendMessage(JsonUtil.toJson(d2_to_d4), lamport)

//      println(s"$lamport")
    }
  }

  def start(initialDelay: Long, period: Long, unit: TimeUnit) = {
    detector_D002.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS)
  }

  def shutdown() = detector_D002.shutdown()

}