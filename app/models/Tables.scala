package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.H2Profile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = PlayEvolutions.schema ++ Reviews.schema ++ Users.schema ++ Whiskies.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(integer), PrimaryKey
   *  @param hash Database column hash SqlType(varchar), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(timestamp)
   *  @param applyScript Database column apply_script SqlType(clob)
   *  @param revertScript Database column revert_script SqlType(clob)
   *  @param state Database column state SqlType(varchar), Length(255,true)
   *  @param lastProblem Database column last_problem SqlType(clob) */
  final case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[java.sql.Clob], revertScript: Option[java.sql.Clob], state: Option[String], lastProblem: Option[java.sql.Clob])
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Clob]], e4: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Clob], <<?[java.sql.Clob], <<?[String], <<?[java.sql.Clob]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, Some("public"), "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(integer), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(varchar), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(timestamp) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(clob) */
    val applyScript: Rep[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("apply_script")
    /** Database column revert_script SqlType(clob) */
    val revertScript: Rep[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("revert_script")
    /** Database column state SqlType(varchar), Length(255,true) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true))
    /** Database column last_problem SqlType(clob) */
    val lastProblem: Rep[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("last_problem")
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table Reviews
   *  @param reviewId Database column review_id SqlType(bigint), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(bigint)
   *  @param whiskyId Database column whisky_id SqlType(bigint)
   *  @param postedDate Database column posted_date SqlType(date)
   *  @param title Database column title SqlType(varchar), Length(50,true)
   *  @param score Database column score SqlType(tinyint), Default(10)
   *  @param comment Database column comment SqlType(varchar), Length(1000,true) */
  final case class ReviewsRow(reviewId: Long, userId: Long, whiskyId: Long, postedDate: java.sql.Date, title: String, score: Byte = 10, comment: Option[String])
  /** GetResult implicit for fetching ReviewsRow objects using plain SQL queries */
  implicit def GetResultReviewsRow(implicit e0: GR[Long], e1: GR[java.sql.Date], e2: GR[String], e3: GR[Byte], e4: GR[Option[String]]): GR[ReviewsRow] = GR{
    prs => import prs._
    ReviewsRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Date], <<[String], <<[Byte], <<?[String]))
  }
  /** Table description of table reviews. Objects of this class serve as prototypes for rows in queries. */
  class Reviews(_tableTag: Tag) extends profile.api.Table[ReviewsRow](_tableTag, Some("public"), "reviews") {
    def * = (reviewId, userId, whiskyId, postedDate, title, score, comment) <> (ReviewsRow.tupled, ReviewsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(reviewId), Rep.Some(userId), Rep.Some(whiskyId), Rep.Some(postedDate), Rep.Some(title), Rep.Some(score), comment).shaped.<>({r=>import r._; _1.map(_=> ReviewsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column review_id SqlType(bigint), AutoInc, PrimaryKey */
    val reviewId: Rep[Long] = column[Long]("review_id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(bigint) */
    val userId: Rep[Long] = column[Long]("user_id")
    /** Database column whisky_id SqlType(bigint) */
    val whiskyId: Rep[Long] = column[Long]("whisky_id")
    /** Database column posted_date SqlType(date) */
    val postedDate: Rep[java.sql.Date] = column[java.sql.Date]("posted_date")
    /** Database column title SqlType(varchar), Length(50,true) */
    val title: Rep[String] = column[String]("title", O.Length(50,varying=true))
    /** Database column score SqlType(tinyint), Default(10) */
    val score: Rep[Byte] = column[Byte]("score", O.Default(10))
    /** Database column comment SqlType(varchar), Length(1000,true) */
    val comment: Rep[Option[String]] = column[Option[String]]("comment", O.Length(1000,varying=true))
  }
  /** Collection-like TableQuery object for table Reviews */
  lazy val Reviews = new TableQuery(tag => new Reviews(tag))

  /** Entity class storing rows of table Users
   *  @param userId Database column user_id SqlType(bigint), AutoInc, PrimaryKey
   *  @param userName Database column user_name SqlType(varchar), Length(50,true)
   *  @param password Database column password SqlType(varchar), Length(100,true)
   *  @param email Database column email SqlType(varchar), Length(200,true)
   *  @param registeredDate Database column registered_date SqlType(date)
   *  @param isQuitted Database column is_quitted SqlType(boolean), Default(false) */
  final case class UsersRow(userId: Long, userName: String, password: String, email: String, registeredDate: java.sql.Date, isQuitted: Boolean = false)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Date], e3: GR[Boolean]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[java.sql.Date], <<[Boolean]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, Some("public"), "users") {
    def * = (userId, userName, password, email, registeredDate, isQuitted) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userId), Rep.Some(userName), Rep.Some(password), Rep.Some(email), Rep.Some(registeredDate), Rep.Some(isQuitted)).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(bigint), AutoInc, PrimaryKey */
    val userId: Rep[Long] = column[Long]("user_id", O.AutoInc, O.PrimaryKey)
    /** Database column user_name SqlType(varchar), Length(50,true) */
    val userName: Rep[String] = column[String]("user_name", O.Length(50,varying=true))
    /** Database column password SqlType(varchar), Length(100,true) */
    val password: Rep[String] = column[String]("password", O.Length(100,varying=true))
    /** Database column email SqlType(varchar), Length(200,true) */
    val email: Rep[String] = column[String]("email", O.Length(200,varying=true))
    /** Database column registered_date SqlType(date) */
    val registeredDate: Rep[java.sql.Date] = column[java.sql.Date]("registered_date")
    /** Database column is_quitted SqlType(boolean), Default(false) */
    val isQuitted: Rep[Boolean] = column[Boolean]("is_quitted", O.Default(false))

    /** Uniqueness Index over (email) (database name constraint_index_4) */
    val index1 = index("constraint_index_4", email, unique=true)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))

  /** Entity class storing rows of table Whiskies
   *  @param whiskyId Database column whisky_id SqlType(bigint), AutoInc, PrimaryKey
   *  @param whiskyName Database column whisky_name SqlType(varchar), Length(100,true)
   *  @param distilleryName Database column distillery_name SqlType(varchar), Length(100,true)
   *  @param country Database column country SqlType(varchar), Length(50,true)
   *  @param region Database column region SqlType(varchar), Length(50,true)
   *  @param `type` Database column type SqlType(varchar), Length(50,true)
   *  @param strength Database column strength SqlType(double)
   *  @param postedDate Database column posted_date SqlType(date) */
  final case class WhiskiesRow(whiskyId: Long, whiskyName: String, distilleryName: Option[String], country: Option[String], region: Option[String], `type`: Option[String], strength: Option[Double], postedDate: java.sql.Date)
  /** GetResult implicit for fetching WhiskiesRow objects using plain SQL queries */
  implicit def GetResultWhiskiesRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Double]], e4: GR[java.sql.Date]): GR[WhiskiesRow] = GR{
    prs => import prs._
    WhiskiesRow.tupled((<<[Long], <<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Double], <<[java.sql.Date]))
  }
  /** Table description of table whiskies. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class Whiskies(_tableTag: Tag) extends profile.api.Table[WhiskiesRow](_tableTag, Some("public"), "whiskies") {
    def * = (whiskyId, whiskyName, distilleryName, country, region, `type`, strength, postedDate) <> (WhiskiesRow.tupled, WhiskiesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(whiskyId), Rep.Some(whiskyName), distilleryName, country, region, `type`, strength, Rep.Some(postedDate)).shaped.<>({r=>import r._; _1.map(_=> WhiskiesRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column whisky_id SqlType(bigint), AutoInc, PrimaryKey */
    val whiskyId: Rep[Long] = column[Long]("whisky_id", O.AutoInc, O.PrimaryKey)
    /** Database column whisky_name SqlType(varchar), Length(100,true) */
    val whiskyName: Rep[String] = column[String]("whisky_name", O.Length(100,varying=true))
    /** Database column distillery_name SqlType(varchar), Length(100,true) */
    val distilleryName: Rep[Option[String]] = column[Option[String]]("distillery_name", O.Length(100,varying=true))
    /** Database column country SqlType(varchar), Length(50,true) */
    val country: Rep[Option[String]] = column[Option[String]]("country", O.Length(50,varying=true))
    /** Database column region SqlType(varchar), Length(50,true) */
    val region: Rep[Option[String]] = column[Option[String]]("region", O.Length(50,varying=true))
    /** Database column type SqlType(varchar), Length(50,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[Option[String]] = column[Option[String]]("type", O.Length(50,varying=true))
    /** Database column strength SqlType(double) */
    val strength: Rep[Option[Double]] = column[Option[Double]]("strength")
    /** Database column posted_date SqlType(date) */
    val postedDate: Rep[java.sql.Date] = column[java.sql.Date]("posted_date")
  }
  /** Collection-like TableQuery object for table Whiskies */
  lazy val Whiskies = new TableQuery(tag => new Whiskies(tag))
}
