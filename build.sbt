import com.typesafe.config.ConfigFactory

lazy val commonSettings = Seq(
  scalaVersion := "2.11.7",
  ensimeScalaVersion in ThisBuild := "2.11.7"
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

/* 
 * メインのPlayプロジェクト
 */
lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "whisky-taste",
    version := "1.0",
    commonSettings,
    libraryDependencies ++= library,
    byHandSlickCodeGenCommand <<= slickCodeGenTask // コンソールから `$ sbt gen-models` でモデルを手動で作成
    ,sourceGenerators in Compile <+= slickCodeGenTask // コンパイル時に自動で実施される設定 (ファイル修正のたびに走ってしまうので使用は見送り)
  ).dependsOn(codegen)

/* 
 * Slickのコードジェネレータの挙動をカスタマイズするためのサブプロジェクト
 */
lazy val codegen = project // ベースディレクトリが ID(`val codegen`) と同じ文字列の時は `(project in file("codegen"))` と書かずに省略できる
  .settings(
    scalaVersion := "2.11.7",
    scalacOptions := Seq("-feature", "-unchecked", "-deprecation"),
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.2.0",
      "com.typesafe.slick" %% "slick-codegen" % "3.2.0",
      "com.h2database" % "h2" % "1.4.196"
    )
  )

lazy val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

// DBのテーブルからモデルクラスのコードを自動生成する設定 //
lazy val byHandSlickCodeGenCommand = TaskKey[Seq[File]]("gen-models")
lazy val slickCodeGenTask = (baseDirectory, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val slickDriver = conf.getString("slick.dbs.default.driver").dropRight(1)
  val jdbcDriver = conf.getString("slick.dbs.default.db.driver")
  val url = conf.getString("slick.dbs.default.db.url")
  val outputFolder = (dir / "app").getPath
  val pkg = "models"
  val user = conf.getString("slick.dbs.default.db.user")
  val password = conf.getString("slick.dbs.default.db.password")
  // toError(r.run("codegen.CustomizedCodeGenerator", cp.files, Seq(slickDriver, jdbcDriver, url, outputFolder, pkg, user, password), s.log))

  val uri = url + ", driver = " + jdbcDriver

  toError(r.run("codegen.CustomizedCodeGenerator", cp.files, Seq(), s.log))
  val fname = s"${outputFolder}/${pkg}/Tables.scala"
  Seq(file(fname))
}
