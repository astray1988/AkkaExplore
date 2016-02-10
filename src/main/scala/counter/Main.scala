package counter

import akka.actor.{Props, Actor}

/**
  * Created by dylan on 2/9/16.
  */
class Main extends Actor {
  val counter = context.actorOf(Props[Counter],"counter")
  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "get"

  override def receive: Receive = {
    case count: Int =>
      println(s"count was $count")
      context.stop(self)
  }
}
