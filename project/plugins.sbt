logLevel := Level.Warn

resolvers += "sbt-vaadin-plugin repo" at "http://henrikerola.github.io/repository/releases"

addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "2.1.0")

addSbtPlugin("org.vaadin.sbt" % "sbt-vaadin-plugin" % "1.2.0")