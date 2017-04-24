package com.vsf.util

import org.slf4j.{Logger, LoggerFactory}

trait UsesLogger {
  val logger: Logger = LoggerFactory.getLogger(this.getClass)
}
