package no.mesan.lunsjtavle.rest

import javax.ws.rs.{GET, Path}

@Path("/api/helloworld")
class HelloWorldRestService {

  @GET
  def sayHello(): String = {
    "Hello World!"
  }
}
