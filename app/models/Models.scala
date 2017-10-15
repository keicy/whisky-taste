package models


object Models {

  final case class ReviewsRow(reviewId: Int, whiskyName: String, score: Short = 10, comment: Option[String] = None, postedDate: java.sql.Date)
}
