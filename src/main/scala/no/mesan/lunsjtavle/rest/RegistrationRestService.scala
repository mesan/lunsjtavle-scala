package no.mesan.lunsjtavle.rest

import javax.ws.rs._
import javax.ws.rs.core.MediaType

import no.mesan.lunsjtavle.db.{Registration, RegistrationRepository}

@Path("/registration")
class RegistrationRestService extends RegistrationRepository with LunsjtavleRestService {

  @GET
  @Path("/user/{userId: [0-9]+}/date/{date}")
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def findRegistrations(@PathParam("userId") userId: Int, @PathParam("date") date: String): List[Registration] = {
    findRegistrationsForUser(userId, convertStringToTimestamp(date))
  }

  @POST
  @Path("/user/{userId: [0-9]+}/date/{date}")
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def createRegistration(@PathParam("userId") userId: Int, @PathParam("date") date: String): Registration = {
    createForUser(userId, convertStringToTimestamp(date))
  }

  @DELETE
  @Path("/{id: [0-9]+}")
  def deleteRegistration(@PathParam("id") id: Int) = {
    delete(id)
  }
}
