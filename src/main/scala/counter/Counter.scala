package counter

import akka.actor.Actor

/**
  * Created by dylan on 2/9/16.
  */
class Counter extends Actor {
  def counter(n: Int): Receive = {
    case "incr" => context.become(counter( n + 1))
    case "get" => sender ! n
  }

  override def receive: Actor.Receive = counter(0)
}
