package controllers

import play.api.Logger
import play.api.mvc._
import javax.inject.{ Inject, Singleton }
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.mvc.BodyParsers.parse.json
import scala.concurrent.{ Future, ExecutionContext }
import java.time.{LocalDate}

import models.Models.ReviewsRow
import dao.ReviewsDAO

@Singleton
class ReviewsController @Inject()(
  val reviewsDAO: ReviewsDAO,
  implicit val ec: ExecutionContext
) extends Controller {
  //import ReviewsController._
  implicit val locationFormat = Json.format[ReviewsRow]

  def all = Action.async { implicit req =>
    reviewsDAO.all().map( reviews => Ok(Json.obj("reviews" -> reviews)))
  }

  // 1
  def create = Action.async(json) { implicit req =>
    req.body.validate[ReviewsRow].fold(
      errors => {
        Logger.debug(s"POST Data = ${req.body}. Bad Request.")
        Future(
        BadRequest(Json.obj(
          "status" -> "Bad Request",
          "message" -> JsError.toJson(errors)))
        )
      },
      review => {
        Logger.debug(s"POST Data = ${req.body}. Try to reate new review.")
        reviewsDAO.create(review).map { msg =>
          Ok(Json.obj(
            "status" -> "OK",
            "message" -> msg))
        }
      }
    )
  }

  // 2 どちらでも良い！
  /*
  def createB = Action.async(json) { implicit req =>
    req.body.validate[ReviewsRow] match {
      case review: JsSuccess[ReviewsRow] => {
        reviewsDAO.create(review.get).map (msg =>
          Ok(Json.obj(
            "status" -> "OK",
            "message" -> msg
          )))
      }
      case errors: JsError => {
        Future(BadRequest(Json.obj(
          "status" -> "Bad Request",
          "message" -> JsError.toJson(errors)
        )))
      }
    }
  }
   */

}

object ReviewsController {
  implicit val locationFormat = Json.format[ReviewsRow]
  /*
  implicit val locationFormat: Format[ReviewsRow] = (
    (__ \ 'reviewId).formatNullable[Int] ~
    (__ \ 'whiskyName).format[String] ~
    (__ \ 'score).format[Short] ~
    (__ \ 'comment).formatNullable[String] ~
    (__ \ 'postedDate).formatNullable[LocalDate]
  )(ReviewsRow.apply, unlift(ReviewsRow.unapply))
   */
}
