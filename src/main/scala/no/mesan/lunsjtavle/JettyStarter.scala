package no.mesan.lunsjtavle

import no.mesan.lunsjtavle.rest._
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.servlet.ServletContainer

class RestApplication extends ResourceConfig {
  // the following don't compile without the number to disambiguate the calls, see https://issues.scala-lang.org/browse/SI-2991
//  register(classOf[HelloWorldRestService], 0) // lets Jersey handle lifecycle
  register(new HelloWorldRestService, 0) // handle our own lifecycle (at least instantiation)
  register(new UserRestService(StaticUserRepostitory), 0) // dependency on external class
  register(JacksonProvider, 0)
}

/**
 * Enkel embedded Jetty.
 */
object JettyStarter extends App {
  println("Starter Jetty...")

  val server = new Server(8080)

  // Jersey-ting
  val holder = new ServletHolder(new ServletContainer(new RestApplication()))
  val context = new ServletContextHandler(ServletContextHandler.SESSIONS)
  context.addServlet(holder, "/api/*")
  server.setHandler(context)

  server.start()
  server.join()
}
