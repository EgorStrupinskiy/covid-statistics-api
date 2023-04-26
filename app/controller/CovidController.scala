package controller

import javax.inject._
import scala.concurrent.ExecutionContext
import play.api.libs.json._
import play.api.mvc._
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import repository.CovidRepository
import service.CovidService
import com.innowise.api.model.CovidDataByDate

@Singleton
class CovidController @Inject()(val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  private val repository = new CovidRepository[IO]()
  private val covidService = new CovidService(repository)

  def getCovidData(): Action[JsValue] = Action.async(parse.json) { implicit request =>
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
