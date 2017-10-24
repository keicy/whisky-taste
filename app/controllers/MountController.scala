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
  val whiskiesService: WhiskiesService,
  val reviewsService: ReviewsService
) extends Controller {
  implicit val reviewsRowFormat = Json.format[ReviewsRow]
  implicit val whiskiesRowFormat = Json.format[WhiskiesRow]

  def index = Action {
    Ok(views.html.index())
  }

  def init = Action.async {
    for {
      ws <- whiskiesService.all()
      rs <- reviewsService.all()
    } yield { //=> Future
      Ok(Json.obj(
        "whiskies" -> ws,
        "reviews" -> rs
      ))
    }
  }
}
