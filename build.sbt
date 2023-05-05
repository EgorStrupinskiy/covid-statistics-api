name := """Covid-Api"""
organization := "com.innowise"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"
libraryDependencies += guice
resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.5.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
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
  "ch.qos.logback" % "logback-classic" % "1.4.6",
  "com.google.inject" % "guice" % "5.1.0",
  "com.typesafe.play" %% "play-ws" % "2.8.18",
  "org.typelevel" %% "cats-core" % "2.9.0",
  "org.typelevel" %% "cats-effect" % "3.4.8",
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
  "com.lightbend.akka" %% "akka-stream-alpakka-slick" % "5.0.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "org.http4s" %% "http4s-blaze-client" % "1.0.0-M29",
  "io.circe" %% "circe-generic" % "0.14.5",
  "io.circe" %% "circe-parser" % "0.14.5",
  "com.softwaremill.sttp.client3" %% "core" % "3.8.15",
  "com.softwaremill.sttp.client3" %% "circe" % "3.8.15",
  "com.softwaremill.sttp.client3" %% "async-http-client-backend-fs2" % "3.8.15",
  "org.typelevel" %% "cats-effect-testing-scalatest" % "1.4.0" % Test,
  "org.typelevel" %% "cats-effect" % "3.4.8",
  //////////

)
// https://mvnrepository.com/artifact/org.http4k/http4k-core
libraryDependencies += "org.http4k" % "http4k-core" % "4.43.0.0"


