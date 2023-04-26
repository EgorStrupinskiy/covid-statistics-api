package service

import cats.effect.unsafe.implicits.global
import cats.effect.{Async, IO}
import com.innowise.api.model.CovidDataByDate
import repository.CovidRepository

import java.time.LocalDate
import scala.concurrent.{ExecutionContext, Future}

class CovidService(repository: CovidRepository[IO])(implicit ec: ExecutionContext) {

  def getMaxAndMinCasesByCountryAndDateRange(countries: Seq[String], startDate: String, endDate: String): Future[Seq[(String, Int, Int)]] = {
    val futures = countries.map { country =>
      val start = LocalDate.parse(startDate)
      val end = LocalDate.parse(endDate)
      repository.getCasesByDate(country, start, end).map { dataList =>
        val maxCases = dataList.map(_.cases).max
        val minCases = dataList.map(_.cases).min
        (country, maxCases, minCases)
      }.unsafeToFuture()
    }
    Future.sequence(futures)
  }
}
