package no.mesan.lunsjtavle.rest

import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

@Path("/api/user")
class UserRestService extends UserRepository {

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def allUsers(): Seq[User] = {
    findAllUsers()
  }
}

trait UserRepository {
  def findAllUsers(): Seq[User] = {
    Seq[User](new User("Øystein Skadsem", 65), new User("Murphy", 0))
  }
}

case class User(name: String, employeeNumber: Int)
