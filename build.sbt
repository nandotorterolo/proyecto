name := "proyecto"

version := "1.0"

scalaVersion := "2.11.8"

crossPaths := false

val vaadinVersion = "7.6.5"

resolvers += "Scaladin Snapshots" at "http://henrikerola.github.io/repository/snapshots/"    // scaladin

resolvers += "vaadin-addons" at "http://maven.vaadin.com/vaadin-addons"    // vaadin addons

libraryDependencies ++= Seq(
  "com.vaadin" % "vaadin-server" % vaadinVersion,
  "com.vaadin" % "vaadin-client-compiled" % vaadinVersion % "container",
  "com.vaadin" % "vaadin-themes" % vaadinVersion % "container",
  "com.vaadin" % "vaadin-client" % vaadinVersion,
  "com.vaadin" % "vaadin-client-compiler" % vaadinVersion,
  "com.vaadin.tapio" % "googlemaps" % "1.3.4",
  "org.vaadin.addons" %% "scaladin" % "3.2-SNAPSHOT",
  "org.slf4j" % "slf4j-api" % "1.7.21",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "joda-time" % "joda-time" % "2.9.3",
  "org.joda" % "joda-convert" % "1.8.1",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.7.3",
  "com.typesafe" % "config" % "1.3.1",
  "org.apache.activemq" % "activemq-client" % "5.14.2",
  "com.typesafe.slick" %% "slick" % "3.2.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "mysql" % "mysql-connector-java" % "5.1.41"
)

vaadinWidgetsets := Seq("com.vaadin.tapio.googlemaps.Widgetset")

// Widgetset compilation needs memory and to avoid an out of memory error it usually needs more memory:
javaOptions in compileVaadinWidgetsets := Seq("-Xss8M", "-Xmx512M", "-XX:MaxPermSize=512M")

vaadinOptions in compileVaadinWidgetsets := Seq("-logLevel", "DEBUG", "-strict")

// Compile widgetsets into the source directory (by default themes are compiled into the target directory)
target in compileVaadinWidgetsets := (sourceDirectory in Compile).value / "webapp" / "VAADIN" / "widgetsets"

enablePlugins(JettyPlugin)