package persistence

import akka.actor.{ActorLogging, ActorSystem, Props}
import akka.persistence.{RecoveryCompleted, PersistentActor, SnapshotOffer}

/**
  * Created by dylan on 2/17/16.
  */
object Counter {
  sealed trait Operation {
    val count: Int
  }
  // operation
  case class Increment(override val count: Int) extends Operation
  case class Decrement(override val count: Int) extends Operation
  // event
  case class Cmd(op: Operation)
  case class Evt(op: Operation)

  case class State(count: Int)

}

class Counter extends PersistentActor with ActorLogging {

  import Counter._
  println("Persistent Starting......................")

  // Persistent Identifier
  override def persistenceId: String = "counter"

  var state: State = State(count = 0)

  def updateState(evt: Evt): Unit = evt match {
    case Evt(Increment(count)) =>
      state = State(count = state.count + count)

    case Evt(Decrement(count)) =>
      state = State(count = state.count - count)
  }
  // Persistent receive on recovery mood
  override def receiveRecover: Receive = {
    case evt: Evt =>
      println(s"Counter receive $evt on recovering mood")
      updateState(evt)
    case SnapshotOffer(_, snapshot: State) =>
      println(s"Counter receive snapshot with data: $snapshot on recovering mood")
      state = snapshot
    case RecoveryCompleted =>
      println(s"Recovery Complete and Now I'll switch to receiving mode. ")
  }
  // Persistent receive on normal mood
  override def receiveCommand: Receive = {
    case cmd @ Cmd(op) =>
      println(s"Counter receive $cmd")
      persist(Evt(op)) { evt =>
        updateState(evt)

      }
    case "print" =>
      println(s"The Current state of counter is $state")

  }

//  override def recovery = Recover.none

}

object PersistnetMain extends App {
  import Counter._
  val system = ActorSystem("persistent-system")
  val counter = system.actorOf(Props[Counter])

  counter ! Cmd(Increment(3))
  counter ! Cmd(Increment(5))
  counter ! Cmd(Increment(3))
  counter ! Cmd(Increment(6))
  counter ! "print"

  Thread.sleep(1000)
  system.terminate()
}
