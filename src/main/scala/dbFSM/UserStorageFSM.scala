package dbFSM

import akka.actor.{Props, ActorSystem, FSM, Stash}

/**
  * Created by dylan on 2/17/16.
  */


case class User(name: String, email: String)

object UserStorageFSM {
  //FSM State
  sealed trait State
  case object Connected extends State
  case object Disconnected extends State

  // FSM Data
  sealed trait Data
  case object EmptyData extends Data



  // operations
  trait DBOperation
  object DBOperation {
    case object Create extends DBOperation
    case object Update extends DBOperation
    case object Read extends DBOperation
    case object Delete extends DBOperation
  }

  case object Connect
  case object DisConnect
  case class Operation(dbOperation: DBOperation, user: Option[User])

}

class UserStorageFSM extends FSM[UserStorageFSM.State, UserStorageFSM.Data] with Stash {
  /**
    * 1. define start with
    * 2. define states
    * 3. initialize
    */
  import UserStorageFSM._
  startWith(Disconnected, EmptyData)

  when(Disconnected) {
    case Event(Connect, _) =>
      println(s"UserStorage Connected to DB")
      unstashAll()
      goto(Connected) using(EmptyData)
    case Event(_, _) =>
      stash()
      stay() using(EmptyData)
  }

  when(Connected) {
    case Event(DisConnect, _) =>
      println(s"UserStorage disconnected from DB")
      goto(Disconnected) using EmptyData

    case Event(Operation(op, user), _) =>
      println(s"UserStorage receive ${op} operation to do in user: ${user}")
      stay() using(EmptyData)
  }

  initialize()

}

object FiniteStateMachineMain extends App {
  import UserStorageFSM._

  val system = ActorSystem("Hotswap-FSM")
  val userStorage = system.actorOf(Props[UserStorageFSM], "userStorage-FSM")

  userStorage ! Connect

  userStorage ! Operation(DBOperation.Create, Some(User("Admin", "admin@email.com")))

  userStorage ! DisConnect

  Thread.sleep(1000)

  system.terminate()
}