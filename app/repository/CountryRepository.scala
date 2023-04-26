package repository

import cats.effect.Sync
import org.http4s.Uri

import java.net.{HttpURLConnection, URL}
import scala.io.Source

class CountryRepository[F[_] : Sync]() {
  private val baseUrl = Uri.unsafeFromString("https://api.covid19api.com")

  def getNames: F[String] = {
    Sync[F].delay {
      val url = new URL(s"$baseUrl/countries")
      val connection = url.openConnection().asInstanceOf[HttpURLConnection]
      connection.setRequestMethod("GET")
      if (connection.getResponseCode == HttpURLConnection.HTTP_OK) {
        val inputStream = connection.getInputStream
        Source.fromInputStream(inputStream).getLines().mkString("\n")
      } else {
        throw new RuntimeException(s"Failed to get countries list. Status: ${connection.getResponseCode}")
      }
    }
  }
}