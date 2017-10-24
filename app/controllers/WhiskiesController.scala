package controllers

import play.api.Logger
import play.api.mvc._
import javax.inject.{ Inject, Singleton }
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.mvc.BodyParsers.parse.json
import scala.concurrent.{ Future, ExecutionContext }

import models.Models._
import services._

@Singleton
class WhiskiesController @Inject()(
  implicit val ec: ExecutionContext,
  val whiskiesService: WhiskiesService,
  val reviewsService: ReviewsService
) extends Controller {
  implicit val whiskiesRowFormat = Json.format[WhiskiesRow]
  implicit val reviewsRowFormat = Json.format[ReviewsRow]

  def createWithReview = Action.async(json) { req => {
    val postData = req.body
    val wJs = (postData \ "whisky").get.validate[WhiskiesRow]
    val rJs = (postData \ "review").get.validate[ReviewsRow]

    val res = for { // Option型と同様の扱い方
      w <- wJs
      r <- rJs
    } yield {
      whiskiesService.createWithReview(w, r)
    }

    // res: Op[F(w, r)] をエラー処理しつつ返す
    res.fold(
      errors => {
        Logger.debug(s"POST Data = ${req.body}. Bad Request.")
        Future(
          BadRequest(Json.obj(
            "status" -> "Bad Request",
            "message" -> JsError.toJson(errors)))
        )
      },
      w_r => w_r.map(t => {
        val (w, r) = t

        /*
        val reviewWithWhisky =Json.obj(
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
         */

        Ok(Json.obj(
          "newWhiskyWithReview" -> Json.obj(
            "whisky" -> w,
            "review" -> r)))
      })
    )
  }}
}
