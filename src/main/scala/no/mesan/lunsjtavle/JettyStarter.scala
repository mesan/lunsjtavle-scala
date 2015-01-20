package no.mesan.lunsjtavle

import com.sun.jersey.spi.container.servlet.ServletContainer
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}

/**
 * Enkel embedded Jetty.
 */
object JettyStarter {
  def main(args: Array[String]): Unit = {
    println("Starter Jetty...")

    val server: Server = new Server(8080)

    // Jersey-ting
    val holder: ServletHolder = new ServletHolder(classOf[ServletContainer])
    holder.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
      "com.sun.jersey.api.core.PackagesResourceConfig")
    holder.setInitParameter("com.sun.jersey.config.property.packages",
      "no.mesan.lunsjtavle.rest")
    val context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS)
    context.addServlet(holder, "/*")

    server.start()
    server.join()
  }
}
