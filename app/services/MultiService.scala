package services

import play.api.Logger
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

import models.Models._
import daos.WhiskiesDAO
import daos.ReviewsDAO

@Singleton
class MultiService @Inject()(
  val dbConfigProvider: DatabaseConfigProvider,
  implicit val ec: ExecutionContext,
  val whiskiesDAO: WhiskiesDAO,
  val reviewsDAO: ReviewsDAO
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._
  val reviews = reviewsDAO.reviews
  val whiskies = whiskiesDAO.whiskies

  def getAllReviewsWithWhisky(): Future[Seq[(ReviewsRow, WhiskiesRow)]] = {
    val sql = for {
      r <- reviews
      w <- r.whiskiy
    } yield (r, w)
    db.run(sql.result)
  }
}
