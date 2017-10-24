package daos

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.{ JdbcProfile, GetResult => GR }

import models.Models.WhiskiesRow

@Singleton
class WhiskiesDAO @Inject()(
  val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  class Whiskies(_tableTag: Tag) extends Table[WhiskiesRow](_tableTag, "whiskies") {
    def * = (whiskyId, whiskyName, distilleryName, country, region, strength) <> (WhiskiesRow.tupled, WhiskiesRow.unapply)
    /* 全てのレコードを Option型 で受け取る関数( OUTER JON に有効) */
    def ? = (whiskyId, Rep.Some(whiskyName), distilleryName, country, region, strength).shaped.<>({r=>import r._; _1.map(_=> WhiskiesRow.tupled((_1, _2.get, _3, _4, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    val whiskyId: Rep[Option[Int]] = column[Option[Int]]("whisky_id", O.AutoInc, O.PrimaryKey)
    val whiskyName: Rep[String] = column[String]("whisky_name", O.Length(100,varying=true))
    val distilleryName: Rep[Option[String]] = column[Option[String]]("distillery_name", O.Length(30,varying=true), O.Default(None))
    val country: Rep[Option[String]] = column[Option[String]]("country", O.Length(30,varying=true), O.Default(None))
    val region: Rep[Option[String]] = column[Option[String]]("region", O.Length(30,varying=true), O.Default(None))
    val strength: Rep[Option[Float]] = column[Option[Float]]("strength", O.Default(None))
  }

  /* 素のSQL文発行時のための変換定義 */
  implicit def GetResultWhiskiesRow(implicit e0: GR[Option[Int]], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Float]]): GR[WhiskiesRow] = GR{
    prs => import prs._
    WhiskiesRow.tupled((<<?[Int], <<[String], <<?[String], <<?[String], <<?[String], <<?[Float]))
  }

  val whiskies = new TableQuery(tag => new Whiskies(tag))
}
