package ActorTest

import ActorTest.Checker.{BlackUser, CheckUser, WhiteUser}
import ActorTest.Recorder.NewUser
import ActorTest.Storage.AddUser
import akka.actor.{Props, ActorSystem, Actor, ActorRef}
import akka.util.Timeout

/**
  * Created by dylan on 2/15/16.
  */
case class User(username: String, email: String)

object Recorder {
  sealed trait RecorderMsg
  // Recorder Messages
  case class NewUser(user: User) extends RecorderMsg

  def props(checker: ActorRef, storage: ActorRef) =
    Props(new Recorder(checker, storage))
}

object Checker {
  sealed trait CheckerMsg
  // checker messages
  case class CheckUser(user: User) extends CheckerMsg

  sealed trait CheckerResponse
  // checker responses
  case class BlackUser(user: User) extends CheckerMsg
  case class WhiteUser(user: User) extends CheckerMsg
}

object Storage {
  sealed trait StorageMsg

  case class AddUser(user: User) extends StorageMsg
}

class Storage extends Actor {
  var users = List.empty[User]

  override def receive: Receive = {
    case AddUser(user) =>
      println(s"Storage: $user added")
      users = user :: users
  }
}

class Checker extends Actor {
  val blackList = List(
    User("Adam", "adam@mail.com")
  )
  override def receive: Actor.Receive = {
    case CheckUser(user) if (blackList.contains(user)) =>
      println(s"Checker: $user in the blacklist")
    case CheckUser(user) =>
      println(s"Checker: $user not in the blacklist ")
      sender ! WhiteUser(user)
  }
}


class Recorder(checker: ActorRef, storage: ActorRef) extends Actor {
  import scala.concurrent.ExecutionContext.Implicits.global
  import akka.pattern.ask
  implicit val timeout= Timeout(5, java.util.concurrent.TimeUnit.SECONDS)


  override def receive: Actor.Receive = {
    case NewUser(user) => {
      checker ? CheckUser(user) map {
        case WhiteUser(user) =>
          storage ! AddUser(user)
        case BlackUser(user) =>
          println(s"Recorder: $user in the blacklist")
      }
    }
  }
}


object TalkToActor extends App {
  val system = ActorSystem("talk-to-actor")

  val checker = system.actorOf(Props[Checker], "checker")

  val storage = system.actorOf(Props[Storage], "storage")

  val recorder = system.actorOf(Recorder.props(checker, storage), "recorder")

  // send NewUser msg to recorder
  recorder ! Recorder.NewUser(User("Jon","jon@email.com"))

  Thread.sleep(100)

  system.terminate()
}
