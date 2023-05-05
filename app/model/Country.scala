package model

import io.circe.{Decoder, Encoder, HCursor, Json}

case class Country(country: String, slug: String, iso2: String)

object Country {

  // Convert Country to JSON
  implicit val decoder: Decoder[Country] = (c: HCursor) =>
    for {
      country <- c.get[String]("Country")
      slug <- c.get[String]("Slug")
      iso2 <- c.get[String]("ISO2")
    } yield Country(country, slug, iso2)

  // Convert Country from JSON
  implicit val encoder: Encoder[Country] = (s: Country) =>
    Json.obj(
      ("Country", Json.fromString(s.country)),
      ("Slug", Json.fromString(s.slug)),
      ("ISO2", Json.fromString(s.iso2))
    )
}
