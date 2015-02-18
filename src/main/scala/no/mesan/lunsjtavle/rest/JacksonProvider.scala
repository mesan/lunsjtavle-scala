package no.mesan.lunsjtavle.rest

import javax.ws.rs.ext.{ContextResolver, Provider}

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

@Provider
object JacksonProvider extends ContextResolver[ObjectMapper] {

  val defaultObjectMapper = {
    val jsonObjectMapper = new ObjectMapper()
    jsonObjectMapper.registerModule(DefaultScalaModule)
    jsonObjectMapper
  }

  override def getContext(typ: Class[_]): ObjectMapper = {
    defaultObjectMapper
  }
}
