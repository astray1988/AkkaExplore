import ActorTest.Toggle
import akka.actor.{Props, ActorSystem}

implicit val system = ActorSystem("TestSys")
val toggle = system.actorOf(Props[Toggle])
