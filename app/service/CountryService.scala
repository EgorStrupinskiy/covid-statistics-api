package service

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import repository.CountryRepository

import scala.concurrent.Future

class CountryService(countryRepository: CountryRepository[IO]) {
  def getCountries: Future[String] = {
    countryRepository.getNames.unsafeToFuture()
  }
}

