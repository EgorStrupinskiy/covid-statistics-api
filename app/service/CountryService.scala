package service

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import client.CountryClient
import com.google.inject.Singleton
import model.Country

import scala.concurrent.Future

@Singleton
class CountryService(countryRepository: CountryClient) {
  def getCountries: Future[Seq[Country]] = {
    countryRepository.getNames.unsafeToFuture()
  }
}

