package cluster

import akka.actor.Actor

/**
  * Created by dylan on 2/21/16.
  */
class Worker extends Actor {
  import Worker._

  def receive = {
    case msg: Work =>
      println(s"I received Work Message and My ActorRef: ${self}")
  }

}

object Worker {
  case class Work(message: String)
}