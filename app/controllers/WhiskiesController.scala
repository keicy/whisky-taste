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

  def createWithReview = Action.async(json) { implicit req => {
    Logger.debug(s"POST Data = ${req.body}. @ WhiskiesController.createWithReview()")

    val postData = req.body
    // JsResult型はOption型と同様の扱い方
    val created = for {
      w <- (postData \ "whisky").get.validate[WhiskiesRow]
      r <- (postData \ "review").get.validate[ReviewsRow]
    } yield {
      whiskiesService.createWithReview(w, r)
    }

    // created: JsR[Fu(w, r)] をエラー処理しつつ返す
    created.fold(
      errors => {
        Logger.debug("Bad Request. @ WhiskiesController.createWithReview()")
        Future(
          BadRequest(Json.obj(
            "status" -> "Bad Request",
            "message" -> JsError.toJson(errors)))
        )
      },
      w_r => w_r.map(t => {
        val (w, r) = t
        Ok(Json.obj(
          "newWhiskyWithReview" -> Json.obj(
            "whisky" -> w,
            "review" -> r)))
      })
    )
  }}
}
