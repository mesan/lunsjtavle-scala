package no.mesan.lunsjtavle.rest

import javax.ws.rs._
import javax.ws.rs.core.MediaType

import no.mesan.lunsjtavle.db.UserRepository
import no.mesan.lunsjtavle.db.User


@Path("/user")
class UserRestService extends UserRepository {

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def allUsers(): Seq[User] = {
    findAllUsers()
  }

  @GET
  @Path("/{id: [0-9]+}")
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def user(@PathParam("id") id: Int) = {
    findUser(id)
  }

  @POST
  @Path("/{name}")
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def createUser(@PathParam("name") name: String) = {
    create(name)
  }

  @PUT
  @Path("/{id: [0-9]+}/{name}")
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def updateUser(@PathParam("id") id: Int, @PathParam("name") name: String) = {
    update(id, name)
  }

  @DELETE
  @Path("/{id: [0-9]+}")
  def deleteUser(@PathParam("id") id: Int) = {
    delete(id)
    "OK"
  }
}