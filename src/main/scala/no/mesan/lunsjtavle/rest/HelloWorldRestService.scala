package no.mesan.lunsjtavle.rest

import javax.ws.rs.core.{Context, UriInfo}
import javax.ws.rs.{GET, Path}

@Path("/helloworld")
class HelloWorldRestService {

  @Context
  var uriInfo: UriInfo = null

  @GET
  def sayHello(): String = {
    "Hello World!"
  }

  @GET
  @Path("oo")
  def sayHelloo = {
    s"Helloooo ${uriInfo.getAbsolutePath}"
  }
}
