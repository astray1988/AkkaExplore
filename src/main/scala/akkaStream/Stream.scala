package akkaStream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Flow, Source}

/**
  * Created by dylan on 2/25/16.
  */
object Stream extends App {
  implicit val actorSystem = ActorSystem()

  implicit val flowMaterializer = ActorMaterializer()

  implicit val ex = actorSystem.dispatcher

  // Source
  val input = Source(1 to 100)

  // Flow
  val normalize  = Flow[Int].map(_ * 2)

  // Sink
  val output = Sink.foreach[Int](println)

  input.via(normalize).runWith(output)(flowMaterializer).andThen {
    case _ =>
      actorSystem.terminate()

  }


}
