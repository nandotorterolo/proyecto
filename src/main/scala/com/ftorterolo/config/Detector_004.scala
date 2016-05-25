package com.ftorterolo.config

import java.util.concurrent._

import com.ftorterolo.util.JsonUtil
import org.slf4j.LoggerFactory

class Detector_004  {

  val logger = LoggerFactory.getLogger(this.getClass)
  val detector_D004 = Executors.newSingleThreadScheduledExecutor()
  val vector_D004:Array[Int] = Array(0,0,0,0)

  def runnable= new Runnable() {
    override def run(): Unit = {

      val r = scala.util.Random
      val autosR = r.nextInt(10)
      val motosR = r.nextInt(10)
      val omnibusR = r.nextInt(10)

      //vector are 0-based
      vector_D004.update(3,vector_D004(3)+1)

      val trafico = Map(Transportes.Auto.id -> autosR, Transportes.Moto.id -> motosR, Transportes.Omnibus.id -> omnibusR )

      val d4_to_d1 = Mensaje(emisor=Detectores.D004.id, receptor=Detectores.D001.id,trafico)
      val d4_to_d2 = Mensaje(emisor=Detectores.D004.id, receptor=Detectores.D002.id,trafico)
      val d4_to_d3 = Mensaje(emisor=Detectores.D004.id, receptor=Detectores.D003.id,trafico)

      val lamport = JsonUtil.toJson(vector_D004)
      MessageHandler.sendMessage(JsonUtil.toJson(d4_to_d1), lamport)
      MessageHandler.sendMessage(JsonUtil.toJson(d4_to_d2), lamport)
      MessageHandler.sendMessage(JsonUtil.toJson(d4_to_d3), lamport)

//      println(s"$lamport")
    }
  }

  def start(initialDelay: Long, period: Long, unit: TimeUnit) = {
    detector_D004.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS)
  }

  def shutdown() = detector_D004.shutdown()

}