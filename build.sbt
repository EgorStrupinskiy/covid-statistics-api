name := """Covid-Api"""
organization := "com.innowise"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.5.0",
  "com.typesafe.akka" %% "akka-stream" % "2.8.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.0",
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "10.5.0" % Test,
  "javax.inject" % "javax.inject" % "1",
  "org.scala-lang.modules" %% "scala-xml" % "2.1.0",
  "com.typesafe.play" %% "play-json" % "2.9.4",
  "com.typesafe.play" %% "play" % "2.9.0-M4",
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.2.0-M3",
  "com.typesafe.play" %% "play-ahc-ws" % "2.9.0-M4",
  "com.typesafe.akka" %% "akka-slf4j" % "2.8.0",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  "com.typesafe.akka" %% "akka-serialization-jackson" % "2.8.0",
  "ch.qos.logback" % "logback-classic" % "1.4.6")
libraryDependencies += "com.google.inject" % "guice" % "5.1.0"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-ws" % "2.8.18"
)
libraryDependencies += "org.typelevel" %% "cats-core" % "2.9.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.4.8"
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.9.0",
  "org.http4s" %% "http4s-blaze-server" % "1.0.0-M29",
  "org.http4s" %% "http4s-circe" % "1.0.0-M29",
  "org.http4s" %% "http4s-dsl" % "1.0.0-M29",
  "ch.qos.logback" % "logback-classic" % "1.4.6",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  "com.typesafe.akka" %% "akka-stream" % "2.8.0",
  "org.postgresql" % "postgresql" % "42.5.4",
  "org.flywaydb" % "flyway-core" % "9.16.0",
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
  "org.slf4j" % "slf4j-api" % "2.0.5",
  "ch.qos.logback" % "logback-classic" % "1.4.6",
  "com.typesafe.akka" %% "akka-http" % "10.5.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.0",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  "com.typesafe.akka" %% "akka-stream" % "2.8.0",
  "com.typesafe.akka" %% "akka-slf4j" % "2.8.0",
  "com.typesafe.akka" %% "akka-persistence" % "2.8.0",
  "com.h2database" % "h2" % "2.1.214",
  "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "com.softwaremill.macwire" %% "macros" % "2.5.8",
  "com.softwaremill.macwire" %% "util" % "2.5.8" % "compile",
  "com.softwaremill.macwire" %% "proxy" % "2.5.8" % "compile",
  "com.lightbend.akka" %% "akka-stream-alpakka-slick" % "5.0.0"
)
// https://mvnrepository.com/artifact/org.http4s/http4s-blaze-client
libraryDependencies += "org.http4s" %% "http4s-blaze-client" % "1.0.0-M29"
// https://mvnrepository.com/artifact/io.circe/circe-generic
libraryDependencies += "io.circe" %% "circe-generic" % "0.14.1"
// https://mvnrepository.com/artifact/io.circe/circe-parser
libraryDependencies += "io.circe" %% "circe-parser" % "0.14.5"



// https://mvnrepository.com/artifact/com.softwaremill.sttp.client3/core
libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.8.15"
// https://mvnrepository.com/artifact/com.softwaremill.sttp.client3/circe
libraryDependencies += "com.softwaremill.sttp.client3" %% "circe" % "3.8.15"
// https://mvnrepository.com/artifact/com.softwaremill.sttp.client3/async-http-client-backend-fs2
libraryDependencies += "com.softwaremill.sttp.client3" %% "async-http-client-backend-fs2" % "3.8.15"

