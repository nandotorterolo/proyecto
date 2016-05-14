name := "dashboard"

version := "1.0"

scalaVersion := "2.11.8"

crossPaths := false

val vaadinVersion = "7.6.0"

libraryDependencies ++= Seq(
  "com.vaadin" % "vaadin-server" % vaadinVersion,
  "com.vaadin" % "vaadin-client-compiled" % vaadinVersion,
  "com.vaadin" % "vaadin-themes" % vaadinVersion,
  "org.slf4j" % "slf4j-api" % "1.7.21",
  "joda-time" % "joda-time" % "2.9.3",
  "org.joda" % "joda-convert" % "1.8.1",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
)

enablePlugins(JettyPlugin)