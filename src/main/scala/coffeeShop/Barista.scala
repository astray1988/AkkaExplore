package coffeeShop

import akka.actor.Actor

/**
  * example from http://danielwestheide.com/blog/2013/02/27/the-neophytes-guide-to-scala-part-14-the-actor-approach-to-concurrency.html
  */

object Barista {

  case class Bill(cents: Int)

  case object CappuccinoRequest

  case object EspressoRequest

  case object ClosingTime
}

class Barista extends Actor {

  import Barista._
  override def receive: Receive = {
    case CappuccinoRequest =>
      sender ! Bill(250)
      println("I have to prepare a cappuccino!")

    case EspressoRequest =>
      sender ! Bill(200)
      println("Let's prepare an espresso.")
    case ClosingTime => context.stop(self)

  }
}
