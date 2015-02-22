package no.mesan.lunsjtavle.db

import java.sql.Timestamp

import scala.slick.driver.MySQLDriver.simple._

case class Period(id: Int, startDate: Timestamp, endDate: Timestamp)

class PeriodTable(tag: Tag) extends Table[Period](tag, "period") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def startDate = column[Timestamp]("startDate")

  def endDate = column[Timestamp]("startDate")

  def * = (id, startDate, endDate) <>(Period.tupled, Period.unapply)
}

trait PeriodRepository extends LunsjtavleRepository {

  val periods = TableQuery[PeriodTable]

  def findById(id: Int): Period = databaseConnection.withSession { implicit session =>
    periods.filter(_.id === id).firstOption.get
  }

  def create(startDate: Timestamp, endDate: Timestamp): Period = databaseConnection.withSession { implicit session =>
    // TODO: Finn ut hvorfor dette ikke virker
    val newPeriodId = periods.returning(periods.map(_.id)) += new Period(0, startDate, endDate)
    findById(newPeriodId)
  }
}
