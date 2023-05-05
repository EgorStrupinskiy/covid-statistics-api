package client

import cats.effect.IO
import cats.implicits.catsSyntaxMonadError
import com.google.inject.Singleton
import model.CovidDataByDate
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.client.{Client, JavaNetClientBuilder}
import org.http4s.{Request, Uri}

import java.time.LocalDate

@Singleton
class CountryStatClient(baseUrl: String) {
  private val httpClient: Client[IO] = JavaNetClientBuilder[IO].create

  def getCasesByDatePeriod(country: String, startDate: LocalDate, endDate: LocalDate): IO[List[CovidDataByDate]] = {
    val uri = Uri.fromString(s"$baseUrl/country/$country/status/confirmed/live?from=$startDate&to=$endDate")
      .getOrElse(throw new IllegalArgumentException("Invalid URL"))

    httpClient.expect[List[CovidDataByDate]](Request[IO](uri = uri))
      .adaptError { case t =>
        t.printStackTrace()
        throw new InternalError()
      }
  }
}



