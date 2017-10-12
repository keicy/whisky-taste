package codegen

import java.net.URI

import slick.backend.DatabaseConfig
import slick.codegen.SourceCodeGenerator
import slick.driver.JdbcProfile
import slick.driver.H2Driver
import slick.model.Model
import scala.collection.mutable
import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Await}
import com.typesafe.config.ConfigFactory

/**
  *  This customizes the Slick code generator to fix type mismatch problems with Play! 2.4 JSON inception
  *  http://stackoverflow.com/questions/31048780/custom-slick-code-generator-3-0-0
  */
object CustomizedCodeGenerator {

  def run(outputDir: Option[String]): Unit = {
    val db = H2Driver.api.Database.forURL("jdbc:h2:file:/h2db",driver="org.h2.Driver")
    //val dc = DatabaseConfig.forURI[JdbcProfile](new URI("slick.dbs.default"))
    val dc = DatabaseConfig.forConfig[JdbcProfile]("slick.dbs.default", ConfigFactory.load("conf/application"))
    val pkg = "models"
    val out = "app"
    //val slickDriver = if(dc.driverIsObject) dc.driverName else "new " + dc.driverName
    val slickDriver = "slick.jdbc.H2Profile"
/*    try {
      val m = Await.result(db.run(dc.profile.createModel(None, false)(ExecutionContext.global).withPinnedSession), Duration.Inf)
      new CustomizedCodeGenerator(m).writeToFile(slickDriver, out, pkg)
    } finally db.close
*/
  }

  def main(args: Array[String]) = {
    run(None)
  }
}

class CustomizedCodeGenerator(model: Model) extends SourceCodeGenerator(model) {
  val models = new mutable.MutableList[String]

  override def packageCode(profile: String, pkg: String, container: String, parentType: Option[String]): String = {
    super.packageCode(profile, pkg, container, parentType) + "\n" + outsideCode
  }

  def outsideCode = s"${indent(models.mkString("\n"))}"

  /**
    * Moves the Row(s) outside of the auto-generated 'trait Tables'
    */
  override def Table = new Table(_) {
    override def EntityType = new EntityTypeDef {
      override def docWithCode: String = {
        models += super.docWithCode.toString + "\n"
        ""
      }
    }
  }
}