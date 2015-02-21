package no.mesan.lunsjtavle.db

import scala.slick.driver.MySQLDriver.simple._

case class User(id: Int, name: String)

class UserTable(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def * = (id, name) <>(User.tupled, User.unapply)
}

trait UserRepository extends LunsjtavleRepository {

  val users = TableQuery[UserTable]

  def findAllUsers(): Seq[User] = databaseConnection.withSession { implicit session =>
    users.list
  }

  def findUser(id: Int): User = databaseConnection.withSession { implicit session =>
    users.filter(_.id === id).firstOption.get
  }

  def create(name: String): User = databaseConnection.withSession { implicit session =>
    val newUserId = users.returning(users.map(_.id)) += new User(0, name)
    findUser(newUserId)
  }

  def update(id: Int, name: String): User = databaseConnection.withSession { implicit session =>
    val currentName = for {user <- users if user.id === id} yield user.name
    currentName.update(name)
    findUser(id)
  }

  def delete(id: Int) = databaseConnection.withSession { implicit session =>
    users.filter(_.id === id).delete
  }
}