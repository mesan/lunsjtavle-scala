package no.mesan.lunsjtavle.db

import java.sql.Timestamp

import scala.slick.driver.MySQLDriver.simple._

case class Period(id: Int, startDate: Timestamp, endDate: Timestamp)

class PeriodTable(tag: Tag) extends Table[Period](tag, "period") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def startDate = column[Timestamp]("startDate")

  def endDate = column[Timestamp]("endDate")

  def * = (id, startDate, endDate) <>(Period.tupled, Period.unapply)
}

trait PeriodRepository extends LunsjtavleRepository {

  val periods = TableQuery[PeriodTable]

  def findById(id: Int): Period = databaseConnection.withSession { implicit session =>
    periods.filter(_.id === id).firstOption.get
  }

  def create(startDate: Timestamp, endDate: Timestamp): Period = databaseConnection.withSession { implicit session =>
    val newPeriodId = periods.returning(periods.map(_.id)) += new Period(0, startDate, endDate)
    findById(newPeriodId)
  }

  def update(id: Int, startDate: Timestamp, endDate: Timestamp) = databaseConnection.withSession { implicit session =>
    (for {period <- periods if period.id === id} yield period.startDate).update(startDate)
    (for {period <- periods if period.id === id} yield period.endDate).update(endDate)
    findById(id)
  }

  def delete(id: Int) = databaseConnection.withSession { implicit session =>
    periods.filter(_.id === id).delete
  }
}
