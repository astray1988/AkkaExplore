package bank

import akka.actor.{Props, Actor}
import akka.event.LoggingReceive

/**
  * Created by dylan on 2/9/16.
  */
object BankAccount {
  val props = Props[BankAccount]

  case class Deposit(amount: BigInt) {
    require(amount > 0)
  }

  case class Withdraw(amount: BigInt) {
    require(amount > 0)
  }

  case object Done

  case object Failed

}

class BankAccount extends Actor {
  import BankAccount._
  var balance = BigInt(0)

  override def receive: Receive = LoggingReceive {
    case Deposit(amount) =>
      balance += amount
      sender ! Done
    case Withdraw(amount) if amount <= balance =>
      balance -= amount
      sender ! Done
    case _ => sender ! Failed
  }
}
