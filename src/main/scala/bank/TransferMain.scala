package bank

import akka.actor.{ActorRef, Actor}
import akka.event.LoggingReceive

/**
  * Main Class: akka.Main
  * VM options: -Dakka.loglevel=DEBUG -Dakka.actor.debug.receive=on
  * Program arguments: bank.TransferMain
  */
class TransferMain extends Actor {
  val accountA = context.actorOf(BankAccount.props, "accountA")
  val accountB = context.actorOf(BankAccount.props, "accountB")

  // send a deposit message to A

  accountA ! BankAccount.Deposit(100)

  // if a "Done" msg is received back, call a transfer fn

  override def receive: Receive = LoggingReceive {
    case BankAccount.Done => transfer(170, accountA, accountB)
  }


  def transfer(amount: BigInt, accountA: ActorRef, accountB: ActorRef): Unit = {
    val transaction = context.actorOf(WireTransfer.props, "transfer")

    transaction ! WireTransfer.Transfer(accountA, accountB, amount)

    context.become(LoggingReceive{
      case WireTransfer.Done =>
        println("success")
        context.stop(self)
      case WireTransfer.Failed =>
        println("failed")
        context.stop(self)
    })

  }
}
