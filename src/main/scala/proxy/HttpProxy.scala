package proxy 

import com.twitter.finagle.Service
import org.jboss.netty.handler.codec.http._
import java.net.InetSocketAddress
import com.twitter.finagle.builder.{ClientBuilder, Server, ServerBuilder}
import com.twitter.finagle.http._
import com.twitter.util.TimeConversions._
import com.twitter.finagle.stats.OstrichStatsReceiver
import org.jboss.netty.handler.codec.http.{HttpResponse, HttpRequest, HttpResponseStatus}
import com.twitter.finagle.http.Version.Http11
import com.twitter.util._
import com.twitter.finagle.http.Status._

object App {

  class ProxyService extends Service[HttpRequest, HttpResponse] {
    val client: Service[HttpRequest, HttpResponse] = ClientBuilder()
      .codec(Http())
      .name("proxy-client")
      .hosts("example.com:80")
      .hostConnectionLimit(1000)
      .tcpConnectTimeout(2.seconds)
      .requestTimeout(60.seconds)
      .retries(1)
      .reportTo(new OstrichStatsReceiver())
      .build()

    def apply(request: HttpRequest) = {
      val req = RequestBuilder().url("http://example.com/").buildGet()
      val resp = Await.result(client(req))

      resp.getStatus match {
        case HttpResponseStatus.OK => Future.value(Response(Http11, Ok))
        case _                     => Future.value(Response(Http11, InternalServerError))
      }
    }
  }

  def main(args: Array[String]) {
    val proxyService: Service[HttpRequest, HttpResponse] = new ProxyService

    val server: Server = ServerBuilder()
      .codec(Http())
      .bindTo(new InetSocketAddress(8080))
      .name("proxy")
      .build(proxyService)
  }
}
