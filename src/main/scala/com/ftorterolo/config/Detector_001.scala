package com.ftorterolo.config

import java.util.concurrent._

import com.ftorterolo.util.JsonUtil
import org.slf4j.LoggerFactory

class Detector_001 {

  val logger = LoggerFactory.getLogger(this.getClass)
  val sendExecutor = Executors.newSingleThreadScheduledExecutor()
  val receiveExecutor = Executors.newSingleThreadScheduledExecutor()
  val vector_D001: Array[Int] = Array(0, 0, 0, 0)

  def sendRunner = new Runnable() {
    override def run(): Unit = {

      vector_D001.update(0, vector_D001(0) + 1)
      val lamport = JsonUtil.toJson(vector_D001)

      val r = scala.util.Random
      val (autosR, motosR, omnibusR )= (r.nextInt(10), r.nextInt(10), r.nextInt(10))
      val trafico = Map(Transportes.Auto.id -> autosR, Transportes.Moto.id -> motosR, Transportes.Omnibus.id -> omnibusR )

      val d1_to_d2 = Mensaje(emisor = Detectores.D001.id, receptor = Detectores.D002.id, trafico)
      val d1_to_d3 = Mensaje(emisor = Detectores.D001.id, receptor = Detectores.D003.id, trafico)
      val d1_to_d4 = Mensaje(emisor = Detectores.D001.id, receptor = Detectores.D004.id, trafico)

      MessageHandler.sendMessage(JsonUtil.toJson(d1_to_d2), lamport)
      MessageHandler.sendMessage(JsonUtil.toJson(d1_to_d3), lamport)
      MessageHandler.sendMessage(JsonUtil.toJson(d1_to_d4), lamport)

      //      println(s"$lamport")
    }
  }

  def receiveRunner = new Runnable() {
    override def run(): Unit = {
      val messages = MessageHandler.receiveMessage()

      println(s"size ${messages.length}")
      if (messages.nonEmpty) {
        val mensaje = JsonUtil.fromJson[Mensaje](messages.head.getBody)

          println(s"nonEmpty messages: $mensaje")   // non sempty messages Mensaje(1,2,Map(6 -> 1, 9 -> 2, 0 -> 4))

          val lamport = JsonUtil.fromJson[(Int, Int, Int, Int)](messages.head.getMessageAttributes.get(MessageHandler.Lamport).getStringValue)
          println(s"lamport: $lamport")

        if (mensaje.receptor.equals(Detectores.D001.id)) {
          vector_D001.update(0, vector_D001(0) + 1)
          vector_D001.update(1, Math.max(vector_D001(1), lamport._2))
          vector_D001.update(2, Math.max(vector_D001(2), lamport._3))
          vector_D001.update(3, Math.max(vector_D001(3), lamport._4))
          // borrar y acumular resultados
          MessageHandler.deleteMessage(messages.head.getReceiptHandle)
        }
        println(s"non sempty messages $mensaje $lamport")
      }else
        println("empty messages")

    }
  }

  def start(initialDelay: Long, period: Long, unit: TimeUnit) = {
    sendExecutor.scheduleAtFixedRate(sendRunner, initialDelay, period, TimeUnit.SECONDS)
    receiveExecutor.scheduleAtFixedRate(receiveRunner, initialDelay + 10, period, TimeUnit.SECONDS)
  }

  def shutdown() = {
    sendExecutor.shutdown()
    receiveExecutor.shutdown()
  }

}