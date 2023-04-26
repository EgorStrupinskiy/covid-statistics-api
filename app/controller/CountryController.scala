package controller


import cats.effect.IO
import cats.effect.unsafe.implicits.global
import play.api.libs.json._
import play.api.mvc._
import repository.CountryRepository
import service.CountryService

import javax.inject._

@Singleton
class CountryController @Inject()(val controllerComponents: ControllerComponents)() extends BaseController {
  private val repository = new CountryRepository[IO]()
  private val countryService = {
    new CountryService(repository)
  }

  def getCountries: Action[JsValue] = Action.async(parse.json)(_ => {
    val result: IO[Result] = for {
      countries <- IO.fromFuture(IO(countryService.getCountries))
      jsonResult = Json.toJson(countries)
    } yield Ok(jsonResult)

    result.handleError { ex =>
      InternalServerError(Json.obj("message" -> ex.getMessage))
    }.unsafeToFuture()
  })
}


