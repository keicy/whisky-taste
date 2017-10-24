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
    val wJsr = (postData \ "whisky").get.validate[WhiskiesRow]
    val rJsr = (postData \ "review").get.validate[ReviewsRow]

    val res = for { // JsResult型はOption型と同様の扱い方
      w <- wJsr
      r <- rJsr
    } yield {
      whiskiesService.createWithReview(w, r)
    }

    // res: Op[Fu(w, r)] をエラー処理しつつ返す
    res.fold(
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
