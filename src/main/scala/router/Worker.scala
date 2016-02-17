package router

import akka.actor.Actor


object Worker {
  case class Work()

}


class Worker extends Actor {
  import Worker._

  override def receive: Receive = {
    case msg: Work =>
      println(s"I received Work Message and my ActorRef: ${self}, sender is: ${sender()}")
  }
}
