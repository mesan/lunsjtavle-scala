name := "lunsjtavle-scala"

version := "1.0"

scalaVersion := "2.11.5"

resolvers += "mvnrepository" at "http://mvnrepository.com/artifact/"

val jacksonVersion = "2.4.1"
val jerseyVersion = "2.7"
val jettyVersion: String = "9.3.0.M1"

libraryDependencies ++= List(
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion,
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.jaxrs" % "jackson-jaxrs-json-provider" % jacksonVersion,
  "com.fasterxml.jackson.jaxrs" % "jackson-jaxrs-base" % jacksonVersion,
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % jacksonVersion,
  "org.glassfish.jersey.core" % "jersey-server" % jerseyVersion,
  "org.glassfish.jersey.containers" % "jersey-container-servlet-core" % jerseyVersion,
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "mysql" % "mysql-connector-java" % "5.1.12"
)