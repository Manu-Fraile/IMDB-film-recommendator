package RetrieveMoviesFromAPI

import org.apache.http.HttpEntity
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.apache.http.util.EntityUtils
import java.io.FileWriter

object RandomMovies {
  def getRandomMovies2k(page_number: String): Unit ={
    val url = "api-url" + page_number
    val httpGet = new HttpGet(url)

    // set the desired header values
    httpGet.setHeader("key1", "value1")
    httpGet.setHeader("key2", "value2")

    // execute the request
    val client: CloseableHttpClient = HttpClientBuilder.create().build()
    val response: CloseableHttpResponse = client.execute(httpGet)
    val myentity: HttpEntity = response.getEntity
    val data: String = EntityUtils.toString(myentity, "UTF-8")

    val fw = new FileWriter("data.dat", true)
    try {
      fw.write("\n")
      fw.write(data)
    }
    finally fw.close()
  }
}
