package client

import cats.effect.IO
import cats.implicits.catsSyntaxMonadError
import com.google.inject.Singleton
import model.Country
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.client.{Client, JavaNetClientBuilder}
import org.http4s.{Request, Uri}

@Singleton
class CountryClient(baseUrl: String) {
  private val httpClient: Client[IO] = JavaNetClientBuilder[IO].create

  def getNames: IO[List[Country]] = {
    val uri = Uri.fromString(s"$baseUrl/countries")
      .getOrElse(throw new IllegalArgumentException("Invalid URL"))

    httpClient.expect[List[Country]](Request[IO](uri = uri))
      .adaptError { case t =>
        t.printStackTrace()
        throw new RuntimeException(s"Failed to get countries list")
      }
  }
}