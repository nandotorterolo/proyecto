package com.ftorterolo.config

import java.util.concurrent.{TimeUnit, _}

import com.ftorterolo.SemDasboard.views.RootGridLayoutBase
import com.ftorterolo.util.JsonUtil
import org.slf4j.LoggerFactory

// http://www.ics.uci.edu/~cs237/lectures/old/chandylamport.pdf
class Detector_001 extends DetectorQueues {

  val logger = LoggerFactory.getLogger(this.getClass)
  val sendExecutor = Executors.newSingleThreadScheduledExecutor()
  val receiveExecutor = Executors.newSingleThreadScheduledExecutor()
  val vector_D001: Array[Int] = Array(0, 0, 0, 0)
  var traficoSnapshot = Trafico(0, 0, 0, 0)
  var snapshot:String = "false"
  var trafico_001 = Array(0, 0, 0, 0)
  var trafico_002 = Array(0, 0, 0, 0)
  var trafico_003 = Array(0, 0, 0, 0)
  var trafico_004 = Array(0, 0, 0, 0)
  var layoutBase:RootGridLayoutBase = null

  def start(initialDelay: Long, period: Long, unit: TimeUnit, layout:RootGridLayoutBase) = {
    layoutBase= layout
    sendExecutor.scheduleAtFixedRate(sendRunner, initialDelay, period, TimeUnit.SECONDS)
    receiveExecutor.scheduleAtFixedRate(receiveRunner, initialDelay + 10, period/3, TimeUnit.SECONDS)
  }

  def shutdown() = {
    sendExecutor.shutdown()
    receiveExecutor.shutdown()
  }

  def sendRunner = new Runnable() {
    override def run(): Unit = {

      vector_D001.update(0, vector_D001(0) + 1)
      val lamport = JsonUtil.toJson(vector_D001)

      val r = scala.util.Random
      val (autosR, motosR, escuelaR, omnibusR)= (r.nextInt(10), r.nextInt(10), r.nextInt(5),r.nextInt(10))

      trafico_001.update(0,autosR)
      trafico_001.update(1,motosR)
      trafico_001.update(2,escuelaR)
      trafico_001.update(3,omnibusR)

      val trafico = Map(Transportes.Auto.nombre -> autosR, Transportes.Moto.nombre -> motosR, Transportes.Escuela.nombre -> escuelaR, Transportes.Omnibus.nombre -> omnibusR )

      val d1_to_d2 = Mensaje(emisor = Detectores.D001.id, receptor = Detectores.D002.id, trafico)
      val d1_to_d3 = Mensaje(emisor = Detectores.D001.id, receptor = Detectores.D003.id, trafico)
      val d1_to_d4 = Mensaje(emisor = Detectores.D001.id, receptor = Detectores.D004.id, trafico)

      MessageHandler.sendMessage(queueUrlD002, JsonUtil.toJson(d1_to_d2), lamport, snapshot)
      MessageHandler.sendMessage(queueUrlD003, JsonUtil.toJson(d1_to_d3), lamport, snapshot)
      MessageHandler.sendMessage(queueUrlD004, JsonUtil.toJson(d1_to_d4), lamport, snapshot)

      val text1 = "D1:" + JsonUtil.toJson(lamport) + s"\n$d1_to_d2"
      layoutBase.setLabelD001(text1)

      //      println(s"$lamport")
    }
  }

  def receiveRunner = new Runnable() {
    override def run(): Unit = {
      val messages = MessageHandler.receiveMessage(queueUrlD001)

//      println(s"size ${messages.length}")
      if (messages.nonEmpty) {
        val mensaje = JsonUtil.fromJson[Mensaje](messages.head.getBody)
        val lamport = JsonUtil.fromJson[(Int, Int, Int, Int)](messages.head.getMessageAttributes.get(MessageHandler.Lamport).getStringValue)
//        val snapshot = JsonUtil.fromJson[String](messages.head.getMessageAttributes.get(MessageHandler.Snapshot).getStringValue)

//        logger.debug(s"Snapshot $snapshot")

        if (mensaje.receptor.equals(Detectores.D001.id)) {
          vector_D001.update(0, vector_D001(0) + 1)
          vector_D001.update(1, Math.max(vector_D001(1), lamport._2))
          vector_D001.update(2, Math.max(vector_D001(2), lamport._3))
          vector_D001.update(3, Math.max(vector_D001(3), lamport._4))

          if (mensaje.emisor.equals(Detectores.D002.id) ){// && vector_D001(1) == lamport._2) {
            trafico_002.update(0,mensaje.trafico.getOrElse(Transportes.Auto.nombre,0))
            trafico_002.update(1,mensaje.trafico.getOrElse(Transportes.Moto.nombre,0))
            trafico_002.update(2,mensaje.trafico.getOrElse(Transportes.Escuela.nombre,0))
            trafico_002.update(3,mensaje.trafico.getOrElse(Transportes.Omnibus.nombre,0))
            val text2 = "D2: " + JsonUtil.toJson(lamport) + s"\n$mensaje"
            layoutBase.setLabelD002(text2)
          }

          if (mensaje.emisor.equals(Detectores.D003.id) ){//&& vector_D001(2) == lamport._3) {
            trafico_003.update(0,mensaje.trafico.getOrElse(Transportes.Auto.nombre,0))
            trafico_003.update(1,mensaje.trafico.getOrElse(Transportes.Moto.nombre,0))
            trafico_003.update(2,mensaje.trafico.getOrElse(Transportes.Escuela.nombre,0))
            trafico_003.update(3,mensaje.trafico.getOrElse(Transportes.Omnibus.nombre,0))
            val text3 = "D3: " + JsonUtil.toJson(lamport) + s"\n$mensaje"
            layoutBase.setLabelD003(text3)
          }

          if (mensaje.emisor.equals(Detectores.D004.id) ){//&& vector_D001(3) == lamport._4) {
            trafico_004.update(0,mensaje.trafico.getOrElse(Transportes.Auto.nombre,0))
            trafico_004.update(1,mensaje.trafico.getOrElse(Transportes.Moto.nombre,0))
            trafico_004.update(2,mensaje.trafico.getOrElse(Transportes.Escuela.nombre,0))
            trafico_004.update(3,mensaje.trafico.getOrElse(Transportes.Omnibus.nombre,0))
            val text4 = "D4: " + JsonUtil.toJson(lamport) + s"\n$mensaje"
            layoutBase.setLabelD004(text4)
          }

          // save message and delete from the quede
          MessageHandler.deleteMessage(queueUrlD001,messages.head.getReceiptHandle)
        }
        else {
          logger.error("This message is in wrong place")
        }
      }
      else {
        logger.error("messages Empty in D001")
      }
    }
  }

}