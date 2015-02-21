package no.mesan.lunsjtavle

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}
import org.glassfish.jersey.server.ServerProperties
import org.glassfish.jersey.servlet.ServletContainer

/**
 * Enkel embedded Jetty.
 */
object JettyStarter extends App {
  println("Starter Jetty...")

  val server = new Server(8080)

  // Jersey-ting
  val holder = new ServletHolder(new ServletContainer())
  holder.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "no.mesan.lunsjtavle.rest")
  holder.setInitParameter(ServerProperties.PROVIDER_CLASSNAMES, "no.mesan.lunsjtavle.rest.JacksonProvider")
  holder.setInitParameter(ServerProperties.TRACING, "ALL")
  holder.setInitOrder(1)

  val context = new ServletContextHandler(ServletContextHandler.SESSIONS)
  context.addServlet(holder, "/api/*")
  server.setHandler(context)

  server.start()
  server.join()
}
