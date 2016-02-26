//package akkaHttp
//
//import akka.actor.ActorSystem
//import akka.actor.Status.Success
//import akka.http.scaladsl.Http
//import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
//import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
//import akka.http.scaladsl.unmarshalling.Unmarshal
//import akka.stream.ActorMaterializer
//import akka.stream.scaladsl.{Sink, Source}
//
//import scala.concurrent.Future
//import scala.util.{Failure, Try}
//
///**
//  * Created by dylan on 2/25/16.
//  */
//object HostLevel extends App {
//  import JsonProtocol._
//
//  implicit val system = ActorSystem()
//  implicit val materializer = ActorMaterializer()
//
//  implicit val ec = system.dispatcher
//
//  //Flow
//
//  val poolClientFlow = Http().cachedHostConnectionPool[Int]("api.ipify.org")
//  val responseFuture: Future[(Try[HttpResponse], Int)] =
//    Source.single(HttpRequest(uri = "/?format=json") -> 4)
//  .via(poolClientFlow)
//  .runWith(Sink.head)
//
//  responseFuture map {
//    case (Success(response), _) =>
//      response.status match {
//      case Ok =>
//        Unmarshal(res.entity).to[IpInfo].map { info =>
//          println(s"The information for my ip is: $info")
//          shutdown()
//        }
//      case _ =>
//        Unmarshal(res.entity).to[String].map { body =>
//          println(s"The response status is ${res.status} and response body is $body")
//          shutdown()
//
//        }
//
//    }
//    case (Failure(err), i) =>
//      println(s"Error Happened $err")
//      shutdown()
//
//  }
//
//  def shutdown() = {
//    Http().shutdownAllConnectionPools().onComplete { _ =>
//      system.shutdown()
//      system.awaitTermination()
//
//    }
//  }
//
//
//}
