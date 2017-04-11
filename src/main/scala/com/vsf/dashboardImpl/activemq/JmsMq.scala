package com.vsf.dashboardImpl.activemq

import javax.jms._
import scala.annotation.tailrec

trait JmsMq extends Mq {

  def connectionFactory: ConnectionFactory

  val QueueName = "test"

  override type MsgId = Message

  override def close() {}

  override def createSender() = new MqSender {
    val connection: Connection = connectionFactory.createConnection("admin", "admin")
    connection.start()

    /**
      * First parameter set no transactional,
      * Second shows that, session will confirm successful reception of the messages by a client acknowledgment.
      */
    val session: Session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE)

    val destination: Queue = session.createQueue(QueueName)

    val producer: MessageProducer = session.createProducer(destination)
    producer.setDeliveryMode(DeliveryMode.PERSISTENT)

    override def send(msgs: List[String]) {
      msgs.foreach(msg => producer.send(session.createTextMessage(msg)))
    }

    override def close(): Unit = {
      session.close()
      connection.close()
    }
  }

  override def createReceiver() = new MqReceiver {
    val connection: Connection = connectionFactory.createConnection("admin", "admin")
    connection.start()

    val session: Session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE)

    val destination: Queue = session.createQueue(QueueName)

    val consumer: MessageConsumer = session.createConsumer(destination)

    override def receive(maxMsgCount: Int): List[(MsgId, String)] = {
      doReceive(Nil, 1000L, maxMsgCount)
    }

    @tailrec
    private def doReceive(acc: List[(MsgId, String)], waitForMsgs: Long, count: Int): List[(MsgId, String)] = {
      if (count == 0) {
        acc
      }
      else {
        val message = consumer.receive(waitForMsgs)
        if (message == null) {
          acc
        }
        else {
          doReceive((message, message.asInstanceOf[TextMessage].getText) :: acc, 100L, count - 1)
        }
      }
    }

    override def ack(ids: List[MsgId]): Unit = {
      ids.foreach { id =>
        id.acknowledge()
      }
    }

    override def close(): Unit = {
      session.close()
      connection.close()
    }
  }
}