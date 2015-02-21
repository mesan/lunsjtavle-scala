package no.mesan.lunsjtavle.db

import scala.slick.driver.MySQLDriver
import scala.slick.driver.MySQLDriver.simple._

trait LunsjtavleRepository {

  private val databaseUrl: String = "jdbc:mysql://localhost:3306/lunsjtavle"
  private val driverClass: String = "com.mysql.jdbc.Driver"
  private val username: String = "root"
  private val password: String = "root"

  val databaseConnection: MySQLDriver.Backend#DatabaseDef =
    Database.forURL(databaseUrl, driver = driverClass, user = username, password = password)

}
