package com.ftorterolo.config

import java.util.concurrent._

class CorredorService  {




  val service = Executors.newSingleThreadScheduledExecutor()

  def runnable = new Runnable() {
    override def run(): Unit = println("Loging")
  }


  def start(initialDelay: Long, period: Long,unit: TimeUnit) = {
    service.scheduleAtFixedRate(runnable,3, 3, TimeUnit.SECONDS)
  }

  def shutdown() = service.shutdown()



}