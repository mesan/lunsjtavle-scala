package no.mesan.lunsjtavle.db

import java.sql.Timestamp
import scala.slick.driver.MySQLDriver.simple._

case class Registration(id: Int, userId: Int, date: Timestamp)

class RegistrationTable(tag: Tag) extends Table[Registration](tag, "registration") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def userId = column[Int]("user_id", O.NotNull)

  def date = column[Timestamp]("date", O.NotNull)

  def * = (id, userId, date) <>(Registration.tupled, Registration.unapply)

  def user = foreignKey("user_id", userId, TableQuery[UserTable])(_.id)
}

trait RegistrationRepository extends LunsjtavleRepository {

  val registrations = TableQuery[RegistrationTable]

  def findRegistrationsForUser(id: Int, date: Timestamp): List[Registration] =
    databaseConnection.withSession { implicit session =>
      registrations.filter(_.date === date).list
    }

  def createForUser(userId: Int, date: Timestamp): Registration = databaseConnection.withSession { implicit session =>
    val newRegistrationId = registrations.returning(registrations.map(_.id)) += new Registration(0, userId, date)
    registrations.filter(_.id === newRegistrationId).firstOption.get
  }

  def delete(id: Int) = databaseConnection.withSession { implicit session =>
    registrations.filter(_.id === id).delete
  }
}

