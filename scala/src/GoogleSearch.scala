import java.io.IOException
import java.net.URL

import scala.io.Source

/**
  * @author Artur Bosch
  */
object GoogleSearch {

  abstract class Response

  case class Success(content: String) extends Response

  case class Error(error: Exception) extends Response

  def main(args: Array[String]): Unit = {
    val url = "https://www.google.de/search?q=google"
    val response = get(url)
    response match {
      case Success(content) => println(content)
      case Error(exception) => println(exception)
    }

  }

  @throws(classOf[IOException])
  def get(url: String): Response = {
    val connection = new URL(url).openConnection()
    connection.addRequestProperty("User-Agent", "Mozilla/5.0")
    connection.setConnectTimeout(5000)
    connection.setReadTimeout(5000)
    try {
      val stream = connection.getInputStream
      val content: String = Source.fromInputStream(stream).mkString
      if (stream != null) stream.close()
      Success(content)
    } catch {
      case e: Exception => Error(e)
    }
  }
}