package controllers

import play.api.Logger
import play.api.mvc._
import javax.inject.{ Inject, Singleton }
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.mvc.BodyParsers.parse.json
import scala.concurrent.{ Future, ExecutionContext }

import models.Models.ReviewsRow
import services.ReviewsService

@Singleton
class ReviewsController @Inject()(
  implicit val ec: ExecutionContext,
  val reviewsService: ReviewsService
) extends Controller {
  implicit val locationFormat = Json.format[ReviewsRow]

  def all = Action.async {
    reviewsService.all().map( reviews => Ok(Json.obj("reviews" -> reviews)))
  }

  def create = Action.async(json) { implicit req => {
    Logger.debug(s"POST Data = ${req.body}. @ ReviewsController.create()")
    req.body.validate[ReviewsRow].fold(
      errors => {
        Logger.debug("Bad Request. @ ReviewsController.create().")
        Future(
        BadRequest(Json.obj(
          "status" -> "Bad Request",
          "message" -> JsError.toJson(errors)))
        )
      },
      review => {
        reviewsService.create(review).map { review =>
          Ok(Json.obj(
            "newReview" -> review))
        }
      }
    )
  }}
}
