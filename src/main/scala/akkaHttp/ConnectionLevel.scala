//package akkaHttp
//
//import akka.actor.ActorSystem
//import akka.http.scaladsl.Http
//import akka.http.scaladsl.Http.OutgoingConnection
//import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
//import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
//import akka.http.scaladsl.unmarshalling.Unmarshal
//import akka.stream.scaladsl.{Sink, Source}
//import akka.stream.{scaladsl, ActorMaterializer}
//
//import scala.concurrent.Future
//
///**
//  * Created by dylan on 2/25/16.
//  */
//object ConnectionLevel extends App {
//
//  import JsonProtocol._
//  implicit  val system = ActorSystem()
//  implicit  val materializer = ActorMaterializer()
//
//  implicit val ec = system.dispatcher
//
//  val connectionFlow: scaladsl.Flow[HttpRequest, HttpResponse, Future[OutgoingConnection]] =
//    Http().outgoingConnection("api.ipify.org")
//
//  val responseFuture = Source.single(HttpRequest(uri = "/format=json"))
//    .via(connectionFlow)
//    .runWith(Sink.head)
//
//  responseFuture map { res =>
//    res.status match {
//      case Ok =>
//
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
//}
