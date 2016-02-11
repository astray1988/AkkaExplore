package coffeeShop

import akka.actor.{Actor, ActorRef}


object Customer {
  case object CaffeineWithdrawlWarning
}

class Customer(caffeineSource: ActorRef) extends Actor {
  import coffeeShop.Customer._
  import coffeeShop.Barista._

  override def receive: Receive = {
    case CaffeineWithdrawlWarning => caffeineSource ! EspressoRequest
    case Bill(cents) => println(s"I have to pay $cents cents, or else! ")
  }
}
