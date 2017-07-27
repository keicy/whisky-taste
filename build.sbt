lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    commonSettings,
    libraryDependencies ++= library,
    byHandSlickCodeGenCommand <<= slickCodeGenTask // コンソールから `sbt gen-models` コマンドでモデルを手動で作成
   ,sourceGenerators in Compile <+= slickCodeGenTask // コンパイル時に自動で実施される設定
  )

lazy val commonSettings = Seq(
  name := "whisky-taste",
  version := "1.0",
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

lazy val byHandSlickCodeGenCommand = TaskKey[Seq[File]]("gen-models")
lazy val slickCodeGenTask = (baseDirectory, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val slickDriver = "slick.jdbc.H2Profile" // [for 3.2.x] 3.1.x の `slick.driver.H2Driver` からパッケージ名とオブジェクト名が変更された
  // val slickDriver = "slick.driver.H2Driver" // [for 3.1.x]
  val jdbcDriver = "org.h2.Driver"
  val url = "jdbc:h2:./h2db;MODE=MYSQL;DB_CLOSE_DELAY=-1"
  val outputFolder = (dir / "app").getPath
  val pkg = "models"
  val user = "sa"
  val password = ""
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Seq(slickDriver, jdbcDriver, url, outputFolder, pkg, user, password), s.log))
  val fname = s"${outputFolder}/${pkg}/Tables.scala"
  Seq(file(fname))
}

lazy val hello = TaskKey[Unit]("greet")
lazy val helloTask = baseDirectory map { (dir) =>
  println(dir / "app")
}
