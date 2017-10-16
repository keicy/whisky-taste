package models

import java.time.{LocalDate}

object Models {
  case class ReviewsRow(
    reviewId: Option[Int] = None,
    whiskyName: String,
    score: Short = 10,
    comment: Option[String] = None,
    postedDate: Option[LocalDate] = None
  )
}
