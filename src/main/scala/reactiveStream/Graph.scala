package reactiveStream

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink

/**
  * Created by dylan on 2/11/16.
  */
object Graph {
  implicit val system = ActorSystem("Sys")
  implicit val materializer = ActorMaterializer()
  def main(args: Array[String]): Unit = {
    val out = Sink.foreach(println)

  }
}
