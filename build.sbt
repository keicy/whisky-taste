import com.typesafe.config.ConfigFactory

lazy val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    commonSettings,
    libraryDependencies ++= library,
    byHandSlickCodeGenCommand <<= slickCodeGenTask // コンソールから `$ sbt gen-models` でモデルを手動で作成
   // ,sourceGenerators in Compile <+= slickCodeGenTask // コンパイル時に自動で実施される設定 (ファイル修正のたびに走ってしまうので使用は見送り)
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
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "mysql" % "mysql-connector-java" % "5.1.44",
  "com.h2database" % "h2" % "1.4.196"
)

/* DBのテーブルからモデルクラスのコードを自動生成する設定 */
lazy val byHandSlickCodeGenCommand = TaskKey[Seq[File]]("gen-models")
lazy val slickCodeGenTask = (baseDirectory, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val slickDriver = conf.getString("slick.dbs.default.driver").dropRight(1)
  val jdbcDriver = conf.getString("slick.dbs.default.db.driver")
  val url = conf.getString("slick.dbs.default.db.url")
  // val outputFolder = (dir / "app").getPath // 本来はこちら
  val outputFolder = (dir / "ref").getPath // 参考実装として使うのみのためrefフォルダへ作成
  val pkg = "models"
  val user = conf.getString("slick.dbs.default.db.user")
  val password = conf.getString("slick.dbs.default.db.password")
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Seq(slickDriver, jdbcDriver, url, outputFolder, pkg, user, password), s.log))
  val fname = s"${outputFolder}/${pkg}/Tables.scala"
  Seq(file(fname))
}
