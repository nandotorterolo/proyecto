import sbt._
import sbt.Keys._

object Dependencies {

  val vaadinVersion = "7.6.5"

  val deps = Seq(
    "com.vaadin" % "vaadin-server" % vaadinVersion,
    "com.vaadin" % "vaadin-client-compiled" % vaadinVersion,
    "com.vaadin" % "vaadin-themes" % vaadinVersion,
    "com.vaadin" % "vaadin-client" % vaadinVersion,
    "com.vaadin" % "vaadin-client-compiler" % vaadinVersion,
    "com.vaadin" % "vaadin-push" % vaadinVersion,
    "com.vaadin.tapio" % "googlemaps" % "1.3.4",
    "org.slf4j" % "slf4j-api" % "1.7.21",
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "joda-time" % "joda-time" % "2.9.3",
    "org.joda" % "joda-convert" % "1.8.1",
    "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
    "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.7.3",
    "com.typesafe" % "config" % "1.3.1",
    "org.apache.activemq" % "activemq-client" % "5.14.2",   // TODO remove
    "com.typesafe.slick" %% "slick" % "3.2.0",
    "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
    "mysql" % "mysql-connector-java" % "5.1.41",
    "com.seancheatham" %% "storage-firebase" % "0.1.3"
  )

}

// "org.vaadin.addons" %% "scaladin" % "3.2-SNAPSHOT",
// "com.google.firebase" % "firebase-admin" % "4.0.3"
