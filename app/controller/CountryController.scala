package controller


import cats.effect.IO
import cats.effect.unsafe.implicits.global
import play.api.libs.json._
import play.api.mvc._
import client.CountryClient
import model.{Country, CountryStatistics}
import play.api.Configuration
import service.CountryService

import javax.inject._

@Singleton
class CountryController @Inject()(val controllerComponents: ControllerComponents, configuration: Configuration)() extends BaseController {

  private val baseUrl = configuration.get[String]("baseUrl")
  private val client = new CountryClient(baseUrl)
  private val countryService = new CountryService(client)
  private val writes = Writes.seq(Json.writes[Country])

  def getCountries: Action[JsValue] = Action.async(parse.json)(_ => {
    val result = for {
      countries <- IO.fromFuture(IO(countryService.getCountries))
      jsonResult = Json.toJson(countries)(writes)
    } yield Ok(jsonResult)

    result.handleError { ex =>
      InternalServerError(Json.obj("message" -> ex.getMessage))
    }.unsafeToFuture()
  })
}


