name := """Covid-Api"""
organization := "com.innowise"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"
libraryDependencies += guice

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.5.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.0",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  "com.typesafe.akka" %% "akka-stream" % "2.8.0",
  "com.typesafe.akka" %% "akka-slf4j" % "2.8.0",
  "com.typesafe.akka" %% "akka-persistence" % "2.8.0",
  "com.typesafe.akka" %% "akka-serialization-jackson" % "2.8.0",
  "com.typesafe.play" %% "play-json" % "2.9.4",
  "com.h2database" % "h2" % "2.1.214",
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
  "com.typesafe" % "config" % "1.4.2",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  "org.http4s" %% "http4s-circe" % "1.0.0-M23",
  "org.http4s" %% "http4s-blaze-client" % "1.0.0-M23",
  "org.http4s" %% "http4s-blaze-server" % "1.0.0-M23",
  "org.http4s" %% "http4s-blaze-server" % "1.0.0-M23",
  "org.http4s" %% "http4s-dsl" % "1.0.0-M24" % Test,
  "org.typelevel" %% "cats-effect" % "3.4.8",
  "com.google.inject" % "guice" % "5.1.0",
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "io.circe" %% "circe-generic" % "0.14.5" % Test,
  "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % "test",
  "org.mockito" % "mockito-core" % "5.2.0" % "test",
  "org.scalamock" %% "scalamock" % "5.2.0" % Test,
  "org.mockito" %% "mockito-scala" % "1.17.12" % Test,
)



