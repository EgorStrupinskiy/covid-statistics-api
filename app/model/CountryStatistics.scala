package model

import io.circe.{Decoder, Encoder, HCursor, Json}

case class CountryStatistics(country: String, maxCases: Int, minCases: Int)

object CountryStatistics {

  // Convert CountryStatistics to JSON
  implicit val decoder: Decoder[CountryStatistics] = (c: HCursor) =>
    for {
      country <- c.downField("Country").as[String]
      maxCases <- c.downField("MaxCases").as[Int]
      minCases <- c.downField("MinCases").as[Int]
    } yield CountryStatistics(country, maxCases, minCases)

  // Convert CountryStatistics from JSON
  implicit val encoder: Encoder[CountryStatistics] = (s: CountryStatistics) =>
    Json.obj(
      ("Country", Json.fromString(s.country)),
      ("MaxCases", Json.fromInt(s.maxCases)),
      ("MinCases", Json.fromInt(s.minCases))
    )
}
