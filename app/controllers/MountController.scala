package controllers

import play.api.mvc._
import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }
import play.api.libs.json._

import models.Models.ReviewsRow
import dao.ReviewsDAO

@Singleton
class MountController @Inject()(
  val reviewsDAO: ReviewsDAO,
  implicit val ec: ExecutionContext
) extends Controller {
  implicit val locationFormat = Json.format[ReviewsRow]

  def index = Action {
    Ok(views.html.index())
  }

  def init = Action.async { implicit req =>
    for {
      reviews <- reviewsDAO.all()
    } yield {
      Ok(Json.obj(
        "data" -> Json.obj(
          "reviews" -> reviews)
      ))
    }
  }
}
