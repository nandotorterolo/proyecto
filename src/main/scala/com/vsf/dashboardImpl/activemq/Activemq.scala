package com.vsf.dashboardImpl.activemq

import javax.jms.ConnectionFactory

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.activemq.ActiveMQConnectionFactory
import scala.collection.JavaConverters._

class ActiveMq(val config: Config) extends JmsMq {

  override lazy val connectionFactory: ConnectionFactory = {
    val cf = new ActiveMQConnectionFactory(config.getString("host"))
    cf.setOptimizeAcknowledge(true)
    cf.setSendAcksAsync(true)
    cf
  }
}

object ActiveMq {
  val DefaultConfig = ConfigFactory.parseMap(Map("host" -> ActiveMQConnectionFactory.DEFAULT_BROKER_URL).asJava)
}


object ActiveMqTestSend extends App {
  val mq = new ActiveMq(ActiveMq.DefaultConfig)

  val start = System.currentTimeMillis()

  val sender = mq.createSender()
  for (i <- 1 to 10) {
    sender.send(List("a", "b", "c", "d", "e", "f", "g", "h", "i", "j").map(_ + i))
  }
  sender.close()

  mq.close()

  println("Done " + (System.currentTimeMillis() - start))
}
