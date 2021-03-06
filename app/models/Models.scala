package models

import java.time.{LocalDate}

object Models {
  case class WhiskiesRow(
    whiskyId: Option[Int] = None,
    whiskyName: String,
    distilleryName: Option[String] = None,
    country: Option[String] = None,
    region: Option[String] = None,
    strength: Option[Float] = None
  )

  case class ReviewsRow(
    reviewId: Option[Int] = None,
    whiskyId: Option[Int] = None,
    score: Short = 10,
    comment: Option[String] = None,
    postedDate: Option[LocalDate] = None
  )
}
