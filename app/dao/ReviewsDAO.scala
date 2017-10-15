package dao

import play.api.Logger
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import java.time.{LocalDate}
import java.sql.Date

import models.Models.ReviewsRow

@Singleton
class ReviewsDAO @Inject()(
  val dbConfigProvider: DatabaseConfigProvider,
  implicit val ec: ExecutionContext
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._
  import ReviewsDAO._

  def all(): Future[Seq[ReviewsRow]] = db.run(reviews.result)

  def create(review: ReviewsRow): Future[String] = {
    Logger.debug(s"Insert Data = ${review}.")
    db.run(reviews += review)
      .map(_ => "Review successfully added.")
      .recover {case ex: Exception => ex.getCause.getMessage}
  }
}

object ReviewsDAO {
  //import slick.model.ForeignKeyAction //TODO いらない?一旦OUT.
  import slick.jdbc.{GetResult => GR}
  val profile = slick.jdbc.PostgresProfile
  import profile.api._

  class Reviews(_tableTag: Tag) extends profile.api.Table[ReviewsRow](_tableTag, "reviews") {
    implicit val localDateToDate = MappedColumnType.base[LocalDate, Date](
      l => Date.valueOf(l),
      d => d.toLocalDate
    )

    def * = (reviewId, whiskyName, score, comment, postedDate) <> (ReviewsRow.tupled, ReviewsRow.unapply)
    def ? = (Rep.Some(reviewId), Rep.Some(whiskyName), Rep.Some(score), comment, Rep.Some(postedDate)).shaped.<>({r=>import r._; _1.map(_=> ReviewsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    val reviewId: Rep[Option[Int]] = column[Option[Int]]("review_id", O.AutoInc, O.PrimaryKey, O.Default(None))
    val whiskyName: Rep[String] = column[String]("whisky_name", O.Length(100,varying=true))
    val score: Rep[Short] = column[Short]("score", O.Default(10))
    val comment: Rep[Option[String]] = column[Option[String]]("comment", O.Length(200,varying=true), O.Default(None))
    val postedDate: Rep[Option[LocalDate]] = column[Option[LocalDate]]("posted_date", O.AutoInc ,O.Default(None)) // DB側で値が自動挿入されるので `` を付与してクエリ文に含まれないようにする
  }

  // 素のSQL文発行時のための変換定義
  implicit def GetResultReviewsRow(implicit e0: GR[Option[Int]], e1: GR[String], e2: GR[Short], e3: GR[Option[String]], e4: GR[Option[LocalDate]]): GR[ReviewsRow] = GR{
    prs => import prs._
    ReviewsRow.tupled((<<?[Int], <<[String], <<[Short], <<?[String], <<?[LocalDate]))
  }

  lazy val reviews = new TableQuery(tag => new Reviews(tag))
}
