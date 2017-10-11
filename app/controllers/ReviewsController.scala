package controllers

import javax.inject.{ Inject, Singleton }
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.mvc.BodyParsers.parse.json

import models.Tables._
import dao.ReviewsDAO

@Singleton
class ReviewsController @Inject()(
  reviewsDAO: ReviewsDAO
) extends Controller {
  def all = TODO
  def create = TODO
}

object ReviewsController {
  implicit val x = Json.format[ReviewsRow]
}
