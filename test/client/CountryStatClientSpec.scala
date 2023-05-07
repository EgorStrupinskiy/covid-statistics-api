package client

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import model.CovidDataByDate
import org.http4s._
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.client.Client
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpec

import java.time.LocalDate

class CountryStatClientSpec extends AsyncWordSpec with Matchers {

  "CountryStatClient" should {

    "return a list of CovidDataByDate when requested" in {
      val mockClient = mock[Client[IO]]
      val baseUrl = "https://api.covid19api.com"
      val client = new CountryStatClient(baseUrl)

      val startDate = LocalDate.of(2021, 1, 1)
      val endDate = LocalDate.of(2021, 1, 5)
      val expectedCovidData = List(
        CovidDataByDate("Belarus", "2021-01-01T00:00:00Z", 196223),
        CovidDataByDate("Belarus", "2021-01-02T00:00:00Z", 198125),
        CovidDataByDate("Belarus", "2021-01-03T00:00:00Z", 199962),
        CovidDataByDate("Belarus", "2021-01-04T00:00:00Z", 201831),
        CovidDataByDate("Belarus", "2021-01-05T00:00:00Z", 203104)
      )

      val expectedUri = Uri.unsafeFromString(s"$baseUrl/country/US/status/confirmed/live?from=$startDate&to=$endDate")
      val expectedRequest = Request[IO](uri = expectedUri)

      when(mockClient.expect[List[CovidDataByDate]](expectedRequest))
        .thenReturn(IO.pure(expectedCovidData))

      val result = client.getCasesByDatePeriod("Belarus", startDate, endDate)

      result.unsafeToFuture().map { covidData =>
        covidData shouldBe expectedCovidData
      }
    }
  }
}
