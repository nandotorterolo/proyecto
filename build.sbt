name := "proyecto"

version := "1.0"

scalaVersion := "2.11.8"

crossPaths := false

val vaadinVersion = "7.6.5"

resolvers += "Scaladin Snapshots" at "http://henrikerola.github.io/repository/snapshots/"    // scaladin

libraryDependencies ++= Seq(
  "com.vaadin" % "vaadin-server" % vaadinVersion,
  "com.vaadin" % "vaadin-client-compiled" % vaadinVersion,
  "com.vaadin" % "vaadin-themes" % vaadinVersion,
  "org.vaadin.addons" %% "scaladin" % "3.2-SNAPSHOT",
  "org.slf4j" % "slf4j-api" % "1.7.21",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "joda-time" % "joda-time" % "2.9.3",
  "org.joda" % "joda-convert" % "1.8.1",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.7.3",
  "com.typesafe" % "config" % "1.3.1",
  "org.apache.activemq" % "activemq-client" % "5.14.2"
)

enablePlugins(JettyPlugin)