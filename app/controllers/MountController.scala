package controllers

import play.api.mvc._
import javax.inject.{ Inject, Singleton }
import scala.concurrent.{ Future, ExecutionContext }
import play.api.libs.json._

import models.Models._
import services.ReviewsService
import services.MultiService

@Singleton
class MountController @Inject()(
  implicit val ec: ExecutionContext,
  val multiService: MultiService,
  val reviewsService: ReviewsService
) extends Controller {
  implicit val reviewsRowFormat = Json.format[ReviewsRow]
  implicit val whiskiesRowFormat = Json.format[WhiskiesRow]

  def index = Action {
    Ok(views.html.index())
  }

  def init = Action.async { req =>
    // Future[Seq[(ReviewsRow, WhiskiesRow)]]
    val allReviewsWithWhisky = multiService.getAllReviewsWithWhisky()

    for ( seq <- allReviewsWithWhisky ) yield {
      val reviewWithWhiskyList = for (x <- seq) yield {
        val (w, r) = x
        Json.obj(
          "w" -> w,
          "r" -> r
        )
      }
      Ok(Json.obj(
        "reviews" -> reviewWithWhiskyList
      ))
    }


    //for {
    //  // Seq[(ReviewsRow, WhiskiesRow)]
    //  allReviewsWithWhisky <- multiService.getAllReviewsWithWhisky()
    //} yield {
    //  Ok(
    //    Json.obj(
    //    "reviews" -> "hoge"
    //  ))
    //}
  }
}
