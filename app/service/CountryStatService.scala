package service

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import cats.syntax.parallel._
import repository.CountryStatRepository

import java.time.LocalDate
import scala.concurrent.Future

class CountryStatService(repository: CountryStatRepository[IO])() {

  def getMaxAndMinCasesByCountryAndDateRange(countries: Seq[String], startDate: String, endDate: String): Future[Seq[(String, Int, Int)]] = {
    val start = LocalDate.parse(startDate)
    val end = LocalDate.parse(endDate)

    countries.parTraverse { country =>
      repository.getCasesByDate(country, start, end).map { dataList =>
        val maxCases = dataList.map(_.cases).max
        val minCases = dataList.map(_.cases).min
        (country, maxCases, minCases)
      }
    }.unsafeToFuture()
  }
}
