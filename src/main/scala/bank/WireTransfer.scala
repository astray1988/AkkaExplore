package bank

import akka.actor.{Props, Actor, ActorRef}
import akka.event.LoggingReceive

/**
  * Created by dylan on 2/9/16.
  */
object WireTransfer {
  val props = Props[WireTransfer]

  case class Transfer(from: ActorRef, to: ActorRef, amount: BigInt)

  case object Done

  case object Failed

}

class WireTransfer extends Actor {
  import  WireTransfer._

  def awaitFrom(to: ActorRef, amount: BigInt, consumer: ActorRef): Receive = LoggingReceive {
    case BankAccount.Done =>
      to ! BankAccount.Deposit(amount)
      context.become(awaitTo(consumer)) //
    case BankAccount.Failed =>
      consumer ! Failed
  }

  def awaitTo(consumer: ActorRef): Receive = LoggingReceive {
    case BankAccount.Done =>
      consumer ! Done
      context.stop(self)
    case BankAccount.Failed =>
      consumer ! Failed
      context.stop(self)
  }

  override def receive: Receive = LoggingReceive {
    case Transfer(from, to, amount) =>
      from ! BankAccount.Withdraw(amount)
      context.become(awaitFrom(to, amount, sender))
  }
}
