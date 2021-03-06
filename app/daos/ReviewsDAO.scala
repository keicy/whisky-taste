package daos

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.{ JdbcProfile, GetResult => GR }
import java.time.{ LocalDate }
import java.sql.Date

import models.Models.ReviewsRow

@Singleton
class ReviewsDAO @Inject()(
  val dbConfigProvider: DatabaseConfigProvider,
  val whiskiesDAO: WhiskiesDAO // 同一パッケージのため import は不要
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._
    val whiskies = whiskiesDAO.whiskies

  class Reviews(_tableTag: Tag) extends profile.api.Table[ReviewsRow](_tableTag, "reviews") {
    /* Slick が LocalDate を扱うための変換定義 */
    implicit val localDateToDate = MappedColumnType.base[LocalDate, Date](
      l => Date.valueOf(l),
      d => d.toLocalDate
    )

    def * = (reviewId, whiskyId, score, comment, postedDate) <> (ReviewsRow.tupled, ReviewsRow.unapply)
    /* 全てのレコードを Option型 で受け取る関数( OUTER JON に有効) */
    def ? = (reviewId, whiskyId, Rep.Some(score), comment, postedDate).shaped.<>({r=>import r._; _1.map(_=> ReviewsRow.tupled((_1, _2, _3.get, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    val reviewId: Rep[Option[Int]] = column[Option[Int]]("review_id", O.AutoInc, O.PrimaryKey, O.Default(None))
    val whiskyId: Rep[Option[Int]] = column[Option[Int]]("whisky_id", O.Default(None))
    val score: Rep[Short] = column[Short]("score", O.Default(10))
    val comment: Rep[Option[String]] = column[Option[String]]("comment", O.Length(200,varying=true), O.Default(None))
    val postedDate: Rep[Option[LocalDate]] = column[Option[LocalDate]]("posted_date", O.AutoInc ,O.Default(None)) // DB側で値が自動挿入されるので `O.AutoInc` を付与してクエリ文に含まれないようにする

    val whiskiy = foreignKey("reviews_whisky_id_fkey", whiskyId, whiskies)(r => r.whiskyId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }

  /* 素のSQL文発行時のための変換定義 */
  implicit def GetResultReviewsRow(implicit e0: GR[Option[Int]], e1: GR[Short], e2: GR[Option[String]], e3: GR[Option[LocalDate]]): GR[ReviewsRow] = GR{
    prs => import prs._
    ReviewsRow.tupled((<<?[Int], <<?[Int], <<[Short], <<?[String], <<?[LocalDate]))
  }

  val reviews = new TableQuery(tag => new Reviews(tag))
}
