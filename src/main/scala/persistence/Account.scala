package persistence

import akka.persistence.fsm.PersistentFsmActor.FSMState

/**
  * Created by dylan on 2/17/16.
  */
object Account {
  // Account State
  sealed trait State extends FSMState

//  case class Empty extends State {
//    override def identifier: String = "Empty"
//  }
//
//  case class Active extends State {
//    override def identifier: String = "Active"
//  }

  // Account Data

  sealed trait Data {
    val amount: Float
  }

  case object  ZeroBalance extends Data {
    override val amount: Float = 0.0f
  }

  case class Balance(override val amount: Float) extends Data

  // Domain Event (Persist events)

  sealed trait DomainEvent

  case class AcceptedTransaction(amount: Float,
                                `type`: TransactionType) extends DomainEvent

  case class RejectedTransaction(amount: Float,
                                `type`: TransactionType, reason: String) extends DomainEvent

  // Transaction Types
  sealed trait TransactionType

  case object Credit extends TransactionType

  case object Debit extends TransactionType

  // Commands

  case class Operation(amount: Float, `type`: TransactionType)

}
//
//class Account extends PersistentFsmActor[Account.State, Account.Data, Account.DomainEvent] {
//  override implicit def domainEventClassTag: ( Class[_] ) => ClassManifest[DomainEvent] = ClassTag[DomainEvent]
//
//  override def applyEvent(domainEvent: DomainEvent, currentData: Data): Data = {
//    domainEvent match {
//      case AcceptedTransaction(amount, credit) =>
//        val newAmount = currentData.amount + amount
//        println(s"Your new balance is ${newAmount} ")
//        Balance(currentData.amount + newAmount)
//      case AcceptedTransaction(amount, debit) =>
//        val newAmount = currentData.amount - amount
//        println(s"Your new balance is $newAmount")
//        if(newAmount > 0)
//          Balance(newAmount)
//        else
//          ZeroBalance
//      case RejectedTransaction(_, _, reason) =>
//        println(s"Rejcted Transaction with reason")
//        currentData
//    }
//  }
//
//  override def persistenceId: String = "account"
//}
