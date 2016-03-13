package counter

import akka.actor.Actor

/**
  * Created by dylan on 2/9/16.
  */
class Counter extends Actor {
  import Counter._

  var count: Int = 0

  def counter(n: Int): Receive = {
    case "incr" => context.become(counter( n + 1))
    case "get" => sender ! n
  }

  override def receive: Actor.Receive = {
    case Increment =>  count += 1
    case Decrement => count -= 1
    case GetCount => sender ! count
  }
}

object Counter {
  case object Increment
  case object Decrement
  case object GetCount

}
