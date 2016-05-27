package com.ftorterolo.config

import java.util.concurrent._

import com.ftorterolo.util.JsonUtil
import org.slf4j.LoggerFactory

class Detector_005  {

  val logger = LoggerFactory.getLogger(this.getClass)
  val executor = Executors.newSingleThreadScheduledExecutor()
  
  def runnable= new Runnable() {
    override def run(): Unit = {
      println(s"run")
    }
  }

  def start(initialDelay: Long, period: Long, unit: TimeUnit) = {
    executor.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS)
  }

  def shutdown() = executor.shutdown()

}