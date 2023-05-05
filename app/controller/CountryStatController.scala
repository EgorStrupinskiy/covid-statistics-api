package controller

import cats.effect.unsafe.implicits.global
import client.CountryStatClient
import model.CountryStatistics
import play.api.Configuration
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json._
import play.api.mvc._
import service.CountryStatService

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class CountryStatController @Inject()(val controllerComponents: ControllerComponents, configuration: Configuration)(implicit ec: ExecutionContext) extends BaseController {

  private val baseUrl = configuration.get[String]("baseUrl")
  private val client = new CountryStatClient(baseUrl)
  private val covidService = new CountryStatService(client)
  private val writes = Writes.seq(Json.writes[CountryStatistics])

  def getCovidDataByDateRange: Action[JsValue] = Action.async(parse.json) { implicit request =>
    val result = for {
      cases <- covidService.getMaxAndMinCasesByCountryAndDateRange(request)
      jsonResult = Json.toJson(cases)(writes)
    } yield Ok(jsonResult)

    result.handleError { ex =>
      InternalServerError(Json.obj("message" -> ex.getMessage))
    }.unsafeToFuture()
  }
}
