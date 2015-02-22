package no.mesan.lunsjtavle.rest

import java.time.{ZoneId, LocalDate}
import java.util.Date
import javax.ws.rs.core.MediaType
import javax.ws.rs._

import no.mesan.lunsjtavle.db.{Period, PeriodRepository}

@Path("/period")
class PeriodRestService extends PeriodRepository {

  //  PUT	/api/period/{id}/{startDate}/{endDate}	Change period duration
  //    DELETE	/api/period/{id}	Delete specific period

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

    val startDate =
      java.sql.Timestamp.from(LocalDate.parse(startDateString).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant)
    val endDate =
      java.sql.Timestamp.from(LocalDate.parse(endDateString).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant)
    create(startDate, endDate)
  }
}
