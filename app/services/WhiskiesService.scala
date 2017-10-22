package services

import play.api.Logger
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

import models.Models.WhiskiesRow
import daos.WhiskiesDAO

@Singleton
class WhiskiesService @Inject()(
  val dbConfigProvider: DatabaseConfigProvider,
  implicit val ec: ExecutionContext,
  val whiskiesDAO: WhiskiesDAO
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._
  val whiskies = whiskiesDAO.whiskies

  def all(): Future[Seq[WhiskiesRow]] = db.run(whiskies.result)

  def create(whisky: WhiskiesRow): Future[WhiskiesRow] = {
    Logger.debug(s"Insert Data = ${whisky}.")
    db.run(whiskies returning whiskies += whisky)
  }
}
