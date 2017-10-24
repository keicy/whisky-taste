package controllers

import play.api.mvc._
import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }
import play.api.libs.json._

import models.Models._
import services._

@Singleton
class MountController @Inject()(
  implicit val ec: ExecutionContext,
 // val multiService: MultiService,
  val whiskiesService: WhiskiesService,
  val reviewsService: ReviewsService
) extends Controller {
  implicit val reviewsRowFormat = Json.format[ReviewsRow]
  implicit val whiskiesRowFormat = Json.format[WhiskiesRow]

  def index = Action {
    Ok(views.html.index())
  }

  def init = Action.async { req =>
    // val allReviewsWithWhiskyF: Future[Seq[(ReviewsRow, WhiskiesRow)]] = multiService.getAllReviewsWithWhisky()
   
    for {
    //  tSeq <- allReviewsWithWhiskyF
      wSeq <- whiskiesService.all()
      rSeq <- reviewsService.all()
    } yield { //=> Future
      Ok(Json.obj(
        "whiskies" -> wSeq,
        "reviews" -> rSeq
      ))

      /*
      val reviewWithWhiskyList = for (t <- tSeq) yield {
        val (r, w) = t
        Json.obj(
          "reviewId" -> r.reviewId,
          "score" -> r.score,
          "comment" -> r.comment,
          "postedDate" -> r.postedDate,
          "whiskyId" -> w.whiskyId,
          "whiskyName" -> w.whiskyName,
          "distilleryName" -> w.distilleryName,
          "country" -> w.country,
          "region" -> w.region,
          "strength" -> w.strength
        )
      }

      Ok(Json.obj(
        "reviews" -> reviewWithWhiskyList,
        "whiskies" -> seq
      ))
       */
    }
  }
}
