package com.vsf.dashboardImpl.dao

import com.vsf.dashboardImpl.model.Employ

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

object Employees {
  def getEmployees: Future[List[Employ]]= {
    def randomStr = Random.alphanumeric.take(10).mkString
    Future {
//      (1 to 20).map(i => Employ(randomStr, randomStr, randomStr)).toList
      (1 to 20).map(i => Employ("randomStr", "randomStr", "randomStr")).toList
    }
  }

}
