package model

import io.circe.{Decoder, Encoder, HCursor, Json}

case class ApiRequest(countries: List[String], from: String, to: String)

object ApiRequest {

  // Convert ApiRequest from JSON
  implicit val decoder: Decoder[ApiRequest] = (c: HCursor) =>
    for {
      countries <- c.downField("Countries").as[List[String]]
      from <- c.downField("From").as[String]
      to <- c.downField("To").as[String]
    } yield ApiRequest(countries, from, to)

  // Convert ApiRequest to JSON
  implicit val encoder: Encoder[ApiRequest] = (request: ApiRequest) =>
    Json.obj(
      ("Countries", Json.arr(request.countries.map(Json.fromString): _*)),
      ("From", Json.fromString(request.from)),
      ("To", Json.fromString(request.to))
    )
}