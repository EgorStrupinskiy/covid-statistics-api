package repository

import cats.effect.Sync
import entity.CovidDataByDate
import io.circe.parser.decode

import java.net.{HttpURLConnection, URL}
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.concurrent.ExecutionContext
import scala.io.Source

class CountryStatRepository[F[_] : Sync](implicit ec: ExecutionContext) {
  private val baseUrl = "https://api.covid19api.com"

  def getCasesByDate(country: String, startDate: LocalDate, endDate: LocalDate): F[List[CovidDataByDate]] = {
    Sync[F].delay {
      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      val url = new URL(s"$baseUrl/country/$country/status/confirmed/live?from=${startDate.format(formatter)}&to=${endDate.format(formatter)}")
      val connection = url.openConnection().asInstanceOf[HttpURLConnection]
      connection.setRequestMethod("GET")
      if (connection.getResponseCode == HttpURLConnection.HTTP_OK) {
        val inputStream = connection.getInputStream
        val jsonStr = Source.fromInputStream(inputStream).getLines().mkString("\n")
        decode[List[CovidDataByDate]](jsonStr) match {
          case Right(covidDataList) => covidDataList
          case Left(error) => throw new RuntimeException(s"Failed to decode JSON: $error")
        }
      } else {
        throw new RuntimeException(s"Failed to get cases by date. Status: ${connection.getResponseCode}")
      }
    }
  }
}

