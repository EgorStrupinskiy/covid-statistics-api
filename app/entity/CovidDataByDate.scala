package entity

import io.circe.{Decoder, HCursor}

case class CovidDataByDate(country: String, date: String, cases: Int)

object CovidDataByDate {
  implicit val decoder: Decoder[CovidDataByDate] = (c: HCursor) =>
    for {
      country <- c.get[String]("Country")
      date <- c.get[String]("Date")
      cases <- c.get[Int]("Cases")
    } yield CovidDataByDate(country, date, cases)
}