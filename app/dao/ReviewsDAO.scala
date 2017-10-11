package dao

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

import models.Tables.{ Reviews, ReviewsRow }

@Singleton
class ReviewsDAO @Inject()(
  dbConfigProvider: DatabaseConfigProvider,
  implicit val ec: ExecutionContext
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  def all(): Future[Seq[ReviewsRow]] = db.run(Reviews.result)
  def create(review: ReviewsRow): Future[String] = db.run(Reviews += review)
    .map(_ => "Review successfully added")
    .recover {case ex: Exception => ex.getCause.getMessage}
}
