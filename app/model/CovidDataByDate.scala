package model

import io.circe.{Decoder, Encoder, HCursor, Json}

case class CovidDataByDate(country: String, date: String, cases: Int)

object CovidDataByDate {

  // Convert CovidDataByDate to JSON
  implicit val encoder: Encoder[CovidDataByDate] = (covidData: CovidDataByDate) =>
    Json.obj(
      ("Country", Json.fromString(covidData.country)),
      ("Date", Json.fromString(covidData.date)),
      ("Cases", Json.fromInt(covidData.cases))
    )

  // Convert CovidDataByDate from JSON
  implicit val decoder: Decoder[CovidDataByDate] = (c: HCursor) =>
    for {
      country <- c.downField("Country").as[String]
      date <- c.downField("Date").as[String]
      cases <- c.downField("Cases").as[Int]
    } yield CovidDataByDate(country, date, cases)
}
