package services

import play.api.Logger
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

import models.Models.ReviewsRow
import daos.ReviewsDAO

@Singleton
class ReviewsService @Inject()(
  val dbConfigProvider: DatabaseConfigProvider,
  implicit val ec: ExecutionContext,
  val reviewsDAO: ReviewsDAO
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._
  val reviews = reviewsDAO.reviews

  def all(): Future[Seq[ReviewsRow]] = db.run(reviews.result)

  def create(review: ReviewsRow): Future[ReviewsRow] = {
    Logger.debug(s"Insert Data = ${review}.")
    db.run(reviews returning reviews += review)
  }
}
