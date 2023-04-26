package entity

import io.circe.{Decoder, HCursor}

case class Country(country: String, slug: String, iso2: String)

object Country {
  implicit val decoder: Decoder[Country] = (c: HCursor) =>
    for {
      country <- c.get[String]("Country")
      slug <- c.get[String]("Slug")
      iso2 <- c.get[String]("ISO2")
    } yield Country(country, slug, iso2)
}
