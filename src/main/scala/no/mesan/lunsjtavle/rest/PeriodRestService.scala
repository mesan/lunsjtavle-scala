package no.mesan.lunsjtavle.rest

import javax.ws.rs._
import javax.ws.rs.core.MediaType

import no.mesan.lunsjtavle.db.{Period, PeriodRepository}

@Path("/period")
class PeriodRestService extends PeriodRepository with LunsjtavleRestService {

  @GET
  @Path("/{id: [0-9]+}")
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def getPeriod(@PathParam("id") id: Int): Period = {
    findById(id)
  }

  @POST
  @Path("/{startDate}/{endDate}")
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def createPeriod(@PathParam("startDate") startDateString: String,
                   @PathParam("endDate") endDateString: String): Period = {

    create(convertStringToTimestamp(startDateString), convertStringToTimestamp(endDateString))
  }

  @PUT
  @Path("/{id: [0-9]+}/{startDate}/{endDate}")
  @Produces(Array(MediaType.APPLICATION_JSON + "; charset=UTF-8"))
  def updatePeriode(@PathParam("id") id: Int,
                    @PathParam("startDate") startDateString: String,
                    @PathParam("endDate") endDateString: String) = {
    update(id, convertStringToTimestamp(startDateString), convertStringToTimestamp(endDateString))
  }

  @DELETE
  @Path("/{id: [0-9]+}")
  def deletePeriod(@PathParam("id") id: Int) = {
    delete(id)
  }
}
