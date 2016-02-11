package coffeeShop

import akka.actor.{Props, ActorSystem}
import coffeeShop.Barista.ClosingTime
import coffeeShop.Customer.CaffeineWithdrawlWarning

/**
  * Created by dylan on 2/11/16.
  */
object CoffeeShopMain {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("sys")

    val barista = system.actorOf(Props[Barista], "Barista")
    val customer = system.actorOf(Props(classOf[Customer], barista), "Customer")

    customer ! CaffeineWithdrawlWarning

    barista ! ClosingTime
  }


}
