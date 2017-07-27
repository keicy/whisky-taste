lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    commonSettings,
    libraryDependencies ++= library
  )

lazy val commonSettings = Seq(
  name := "whisky-taste",
  version := "1.0-SNAPSHOT",
  scalaVersion := "2.11.7"
)

lazy val library = Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.typesafe.play" %% "play-slick" % "2.1.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.1.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.2.0",
  "com.h2database" % "h2" % "1.4.196"
)
