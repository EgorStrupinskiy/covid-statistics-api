package service

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import cats.syntax.parallel._
import client.CountryStatClient
import com.google.inject.Singleton
import io.circe.Json
import model.{CountryStatistics, CovidDataByDate}
import play.api.libs.json.JsPath.\
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json._
import play.api.mvc.Request

import java.time.LocalDate
import scala.concurrent.Future
import scala.xml.NodeSeq.Empty.\

@Singleton
class CountryStatService(client: CountryStatClient) {

  def getMaxAndMinCasesByCountryAndDateRange(request: Request[JsValue]): IO[Seq[CountryStatistics]] = {
    val countries = (request.body \ "countryList").as[List[String]]
    val startDate = LocalDate.parse((request.body \ "fromDate").as[String])
    val endDate = LocalDate.parse((request.body \ "toDate").as[String])
    val previousDate = startDate.minusDays(1)

    countries.parTraverse { country =>
      client.getCasesByDatePeriod(country, previousDate, endDate).map { dataList =>
        val (maxCases, minCases) = getMaxAndMinCases(dataList)
        CountryStatistics(country, maxCases, minCases)
      }
    }
  }

  private def getMaxAndMinCases(dataList: List[CovidDataByDate]): (Int, Int) = {
    val casesByDate = dataList.map(_.cases).sorted.sliding(2).collect {
      case Seq(prevCases, cases) => cases - prevCases
    }.toList
    (casesByDate.max, casesByDate.min)
  }
}
