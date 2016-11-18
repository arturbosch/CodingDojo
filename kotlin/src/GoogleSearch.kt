import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author Artur Bosch
 */

fun main(args: Array<String>) {
	val url = "https://www.google.de/search?q=google"
	performSearch(url)
}

fun performSearch(url: String) {
	val response = url.httpGet()
	when (response) {
		is Response.Success -> println("${response.status}: ${response.content}")
		is Response.Error -> {
			println("${response.status}: ${response.exception}")
			response.exception.printStackTrace()
		}
	}
}

fun String.httpGet(): Response {
	val connection = URL(this).openConnection() as HttpURLConnection
	connection.setRequestProperty("User-Agent", "Mozilla/5.0")
	return try {
		val code = connection.responseCode
		val content = String(connection.inputStream.readBytes())
		if (code == 200) Response.Success(content) else
			Response.Error(code, IllegalStateException("Unexpected status code $code!"))
	} catch (ioe: IOException) {
		Response.Error(400, ioe)
	}
}

sealed class Response(val status: Int) {
	class Success(val content: String) : Response(200)
	class Error(status: Int, val exception: Exception) : Response(status)
}