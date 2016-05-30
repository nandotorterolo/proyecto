package com.ftorterolo.config

import java.util.concurrent._

import com.ftorterolo.util.JsonUtil
import org.slf4j.LoggerFactory

class Detector_004  extends DetectorQueues  {

  val logger = LoggerFactory.getLogger(this.getClass)
  val sendExecutor = Executors.newSingleThreadScheduledExecutor()
  val receiveExecutor = Executors.newSingleThreadScheduledExecutor()
  val vector_D004:Array[Int] = Array(0,0,0,0)

  def start(initialDelay: Long, period: Long, unit: TimeUnit) = {
    sendExecutor.scheduleAtFixedRate(sendRunner, initialDelay, period, TimeUnit.SECONDS)
    receiveExecutor.scheduleAtFixedRate(receiveRunner, initialDelay + 10, period/3, TimeUnit.SECONDS)
  }

  def shutdown() = {
    sendExecutor.shutdown()
    receiveExecutor.shutdown()
  }

  def sendRunner = new Runnable() {
    override def run(): Unit = {

      //vector are 0-based
      vector_D004.update(3,vector_D004(3)+1)
      val lamport = JsonUtil.toJson(vector_D004)

      val r = scala.util.Random
      val (autosR, motosR, escuelaR, omnibusR)= (r.nextInt(10), r.nextInt(10), r.nextInt(5),r.nextInt(10))
      val trafico = Map(Transportes.Auto.id -> autosR, Transportes.Moto.id -> motosR, Transportes.Escuela.id -> escuelaR, Transportes.Omnibus.id -> omnibusR )

      val d4_to_d1 = Mensaje(emisor=Detectores.D004.id, receptor=Detectores.D001.id,trafico)
      val d4_to_d2 = Mensaje(emisor=Detectores.D004.id, receptor=Detectores.D002.id,trafico)
      val d4_to_d3 = Mensaje(emisor=Detectores.D004.id, receptor=Detectores.D003.id,trafico)

      MessageHandler.sendMessage(queueUrlD001, JsonUtil.toJson(d4_to_d1), lamport)
      MessageHandler.sendMessage(queueUrlD002, JsonUtil.toJson(d4_to_d2), lamport)
      MessageHandler.sendMessage(queueUrlD003, JsonUtil.toJson(d4_to_d3), lamport)
//      println(s"$lamport")
    }
  }

  def receiveRunner = new Runnable() {
    override def run(): Unit = {
      val messages = MessageHandler.receiveMessage(queueUrlD004)

      if (messages.nonEmpty) {
        val mensaje = JsonUtil.fromJson[Mensaje](messages.head.getBody)
        val lamport = JsonUtil.fromJson[(Int, Int, Int, Int)](messages.head.getMessageAttributes.get(MessageHandler.Lamport).getStringValue)

        if (mensaje.receptor.equals(Detectores.D004.id)) {
          vector_D004.update(0, Math.max(vector_D004(0), lamport._1))
          vector_D004.update(1, Math.max(vector_D004(1), lamport._2))
          vector_D004.update(2, Math.max(vector_D004(2), lamport._3))
          vector_D004.update(3, vector_D004(3) + 1)
          // save message and delete from the quede
          MessageHandler.deleteMessage(queueUrlD004,messages.head.getReceiptHandle)
        }
        else {
          logger.error("This message is in wrong place")
        }
      }
    }
  }
}