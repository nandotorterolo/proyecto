import sbt.internals.ProjectSettings

name := "proyecto"

version := "1.0"

scalaVersion in ThisBuild := "2.11.8"

crossPaths in ThisBuild := false

scalacOptions in ThisBuild := Seq("-deprecation")

resolvers in ThisBuild  ++= Seq(
  "Scaladin Snapshots" at "http://henrikerola.github.io/repository/snapshots/",   // scaladin
  "vaadin-addons" at "http://maven.vaadin.com/vaadin-addons",                     // vaadin addons
  "sbt-vaadin-plugin repo" at "http://henrikerola.github.io/repository/releases"
)

lazy val root = project.in(file(".")).enablePlugins(JettyPlugin).settings(vaadinWebSettings :_*).settings(
  name := "proyecto",
  artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) => "proyecto." + artifact.extension },
  libraryDependencies ++= Dependencies.deps,
  vaadinWidgetsets := Seq("com.vaadin.tapio.googlemaps.Widgetset"),
  javaOptions in compileVaadinWidgetsets := Seq("-Xss8M", "-Xmx512M", "-XX:MaxPermSize=512M"),
  vaadinOptions in compileVaadinWidgetsets := Seq("-strict", "-draftCompile"),
  target in compileVaadinWidgetsets := (sourceDirectory in Compile).value / "webapp" / "VAADIN" / "widgetsets",
  skip in compileVaadinWidgetsets in resourceGenerators := true,
  javaOptions in vaadinDevMode ++= Seq("-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"),
  // JavaDoc generation causes problems
  sources in doc in Compile := List(),
  webappWebInfClasses := true
)


