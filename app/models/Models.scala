package models

import java.time.{LocalDate}

object Models {
  case class ReviewsRow(
    reviewId: Option[Int] = None,
    whiskyId: Int,
    score: Short = 10,
    comment: Option[String] = None,
    postedDate: Option[LocalDate] = None
  )

  case class WhiskiesRow(
    whiskyId: Option[Int] = None,
    whiskyName: String,
    distilleryName: Option[String] = None,
    country: Option[String] = None,
    region: Option[String] = None,
    strength: Option[Float] = None
  )
}
