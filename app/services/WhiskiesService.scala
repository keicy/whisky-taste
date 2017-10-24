package services

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

import models.Models._
import daos._

@Singleton
class WhiskiesService @Inject()(
  val dbConfigProvider: DatabaseConfigProvider,
  implicit val ec: ExecutionContext,
  val whiskiesDAO: WhiskiesDAO,
  val reviewsDAO: ReviewsDAO
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._
  val reviews = reviewsDAO.reviews
  val whiskies = whiskiesDAO.whiskies

  def all(): Future[Seq[WhiskiesRow]] = db.run(whiskies.result)

  def createWithReview(
    whisky: WhiskiesRow,
    review: ReviewsRow
  ): Future[(WhiskiesRow, ReviewsRow)] = {
    // 処理 (更新、トランザクショナル, 同一名称ボトルがあれば追加しない)
    // 1. 同名ボトルがあるか確認
    // 2. あればそれをもらう / なければボトル追加 -> 登録ボトルをもらう
    // 3. レビュー登録 -> 登録レビューをもらう
    // 4. ペアにして返す

    val action = for {
      alreadyExistOneOp <- whiskies.filter(_.whiskyName === whisky.whiskyName).result.headOption
      targetW <- alreadyExistOneOp.fold[DBIO[WhiskiesRow]](
        // 見つからなかったので新規登録してそれを返す
        whiskies returning whiskies += whisky
      )(
        // 見つかったものをそのまま返す
        w => DBIO.successful(w)
      )
      // レビューを新規登録してそれを返す
      targetR <- reviews returning reviews += ReviewsRow(None, targetW.whiskyId, review.score, review.comment, None)
    } yield (targetW, targetR)

    db.run(action.transactionally) // 上記3つのクエリをトランザクショナルに実行
  }

  def create(whisky: WhiskiesRow): Future[WhiskiesRow] = {
    db.run(whiskies returning whiskies += whisky)
  }
}
