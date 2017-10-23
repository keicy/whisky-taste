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
  implicit val ec: ExecuytionContext,
  val whiskiesService: WhiskiesService,
  val reviewsService: ReviewsService
) extends Controller {
  implicit val whiskiesRowFormat = Json.format[WhiskiesRow]
  implicit val reviewsRowFormat = Json.format[ReviewsRow]

  def createWithReview = Action.async(json) { req => {
    val postData = req.body
    val wR = (postData \ "whisky").get.validate[WhiskiesRow]
    val rR = (postData \ "review").get.validate[ReviewsRow]

    val res = for { // Option型と同様の扱い方
      w <- wR
      r <- rR 
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
      w_r => w_r.map(xxx => {
        val (w, r) = xxx

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

        Ok(Json.obj(
          "whisky" -> w,
          "review" -> reviewWithWhisky))
      })
    )
  }}
}
