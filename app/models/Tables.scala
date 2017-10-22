package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = PlayEvolutions.schema ++ Reviews.schema ++ Whiskies.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param hash Database column hash SqlType(varchar), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(timestamp)
   *  @param applyScript Database column apply_script SqlType(text), Default(None)
   *  @param revertScript Database column revert_script SqlType(text), Default(None)
   *  @param state Database column state SqlType(varchar), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(text), Default(None) */
  final case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(varchar), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(timestamp) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(text), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Default(None))
    /** Database column revert_script SqlType(text), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Default(None))
    /** Database column state SqlType(varchar), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(text), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table Reviews
   *  @param reviewId Database column review_id SqlType(serial), AutoInc, PrimaryKey
   *  @param whiskyId Database column whisky_id SqlType(int4)
   *  @param score Database column score SqlType(int2), Default(10)
   *  @param comment Database column comment SqlType(varchar), Length(500,true), Default(None)
   *  @param postedDate Database column posted_date SqlType(date) */
  final case class ReviewsRow(reviewId: Int, whiskyId: Int, score: Short = 10, comment: Option[String] = None, postedDate: java.sql.Date)
  /** GetResult implicit for fetching ReviewsRow objects using plain SQL queries */
  implicit def GetResultReviewsRow(implicit e0: GR[Int], e1: GR[Short], e2: GR[Option[String]], e3: GR[java.sql.Date]): GR[ReviewsRow] = GR{
    prs => import prs._
    ReviewsRow.tupled((<<[Int], <<[Int], <<[Short], <<?[String], <<[java.sql.Date]))
  }
  /** Table description of table reviews. Objects of this class serve as prototypes for rows in queries. */
  class Reviews(_tableTag: Tag) extends profile.api.Table[ReviewsRow](_tableTag, "reviews") {
    def * = (reviewId, whiskyId, score, comment, postedDate) <> (ReviewsRow.tupled, ReviewsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(reviewId), Rep.Some(whiskyId), Rep.Some(score), comment, Rep.Some(postedDate)).shaped.<>({r=>import r._; _1.map(_=> ReviewsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column review_id SqlType(serial), AutoInc, PrimaryKey */
    val reviewId: Rep[Int] = column[Int]("review_id", O.AutoInc, O.PrimaryKey)
    /** Database column whisky_id SqlType(int4) */
    val whiskyId: Rep[Int] = column[Int]("whisky_id")
    /** Database column score SqlType(int2), Default(10) */
    val score: Rep[Short] = column[Short]("score", O.Default(10))
    /** Database column comment SqlType(varchar), Length(500,true), Default(None) */
    val comment: Rep[Option[String]] = column[Option[String]]("comment", O.Length(500,varying=true), O.Default(None))
    /** Database column posted_date SqlType(date) */
    val postedDate: Rep[java.sql.Date] = column[java.sql.Date]("posted_date")

    /** Foreign key referencing Whiskies (database name reviews_whisky_id_fkey) */
    lazy val whiskiesFk = foreignKey("reviews_whisky_id_fkey", whiskyId, Whiskies)(r => r.whiskyId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Reviews */
  lazy val Reviews = new TableQuery(tag => new Reviews(tag))

  /** Entity class storing rows of table Whiskies
   *  @param whiskyId Database column whisky_id SqlType(serial), AutoInc, PrimaryKey
   *  @param whiskyName Database column whisky_name SqlType(varchar), Length(100,true)
   *  @param distilleryName Database column distillery_name SqlType(varchar), Length(30,true), Default(None)
   *  @param country Database column country SqlType(varchar), Length(30,true), Default(None)
   *  @param region Database column region SqlType(varchar), Length(30,true), Default(None)
   *  @param strength Database column strength SqlType(float4), Default(None) */
  final case class WhiskiesRow(whiskyId: Int, whiskyName: String, distilleryName: Option[String] = None, country: Option[String] = None, region: Option[String] = None, strength: Option[Float] = None)
  /** GetResult implicit for fetching WhiskiesRow objects using plain SQL queries */
  implicit def GetResultWhiskiesRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Float]]): GR[WhiskiesRow] = GR{
    prs => import prs._
    WhiskiesRow.tupled((<<[Int], <<[String], <<?[String], <<?[String], <<?[String], <<?[Float]))
  }
  /** Table description of table whiskies. Objects of this class serve as prototypes for rows in queries. */
  class Whiskies(_tableTag: Tag) extends profile.api.Table[WhiskiesRow](_tableTag, "whiskies") {
    def * = (whiskyId, whiskyName, distilleryName, country, region, strength) <> (WhiskiesRow.tupled, WhiskiesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(whiskyId), Rep.Some(whiskyName), distilleryName, country, region, strength).shaped.<>({r=>import r._; _1.map(_=> WhiskiesRow.tupled((_1.get, _2.get, _3, _4, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column whisky_id SqlType(serial), AutoInc, PrimaryKey */
    val whiskyId: Rep[Int] = column[Int]("whisky_id", O.AutoInc, O.PrimaryKey)
    /** Database column whisky_name SqlType(varchar), Length(100,true) */
    val whiskyName: Rep[String] = column[String]("whisky_name", O.Length(100,varying=true))
    /** Database column distillery_name SqlType(varchar), Length(30,true), Default(None) */
    val distilleryName: Rep[Option[String]] = column[Option[String]]("distillery_name", O.Length(30,varying=true), O.Default(None))
    /** Database column country SqlType(varchar), Length(30,true), Default(None) */
    val country: Rep[Option[String]] = column[Option[String]]("country", O.Length(30,varying=true), O.Default(None))
    /** Database column region SqlType(varchar), Length(30,true), Default(None) */
    val region: Rep[Option[String]] = column[Option[String]]("region", O.Length(30,varying=true), O.Default(None))
    /** Database column strength SqlType(float4), Default(None) */
    val strength: Rep[Option[Float]] = column[Option[Float]]("strength", O.Default(None))
  }
  /** Collection-like TableQuery object for table Whiskies */
  lazy val Whiskies = new TableQuery(tag => new Whiskies(tag))
}
