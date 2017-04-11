package com.vsf.dashboardImpl.activemq

import com.typesafe.config.Config


trait Mq {

  def config: Config

  type MsgId

  def msgWithTimestamp(prefix: String): String = prefix + System.currentTimeMillis().toString

  def extractTimestamp(msg: String): Long = msg.toLong

  trait MqSender {
    /**
      * Synchronous - must wait for the messages to be sent
      */
    def send(msgs: List[String])

    def close() {}
  }

  trait MqReceiver {
    def receive(maxMsgCount: Int): List[(MsgId, String)]

    /**
      * Can be asynchronous
      */
    def ack(ids: List[MsgId])

    def close() {}
  }

  def createSender(): MqSender
  def createReceiver(): MqReceiver

  def close() {}
}

