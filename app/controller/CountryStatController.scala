package controller

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import play.api.libs.json._
import play.api.mvc._
import repository.CountryStatRepository
import service.CountryStatService

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class CountryStatController @Inject()(val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  private val repository = new CountryStatRepository[IO]()
  private val covidService = new CountryStatService(repository)

  def getCovidData: Action[JsValue] = Action.async(parse.json) { implicit request =>
    val countryList = (request.body \ "countryList").as[List[String]]
    val fromDate = (request.body \ "fromDate").as[String]
    val toDate = (request.body \ "toDate").as[String]

    val result = for {
      cases <- IO.fromFuture(IO(covidService.getMaxAndMinCasesByCountryAndDateRange(countryList, fromDate, toDate)))
      jsonResult = Json.toJson(cases.map { case (country, maxCases, minCases) =>
        Json.obj(
          "country" -> country,
          "maxCases" -> maxCases,
          "minCases" -> minCases
        )
      })

    } yield Ok(jsonResult)

    result.handleError { ex =>
      InternalServerError(Json.obj("message" -> ex.getMessage))

    }.unsafeToFuture()
  }
}
