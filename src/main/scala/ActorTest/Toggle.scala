package ActorTest

import akka.actor.Actor

/**
  * Created by dylan on 2/9/16.
  */
object Toggle {

}

class Toggle extends Actor {
  def happy: Receive = {
    case "How are you?" =>
      sender ! "happy"
      context become sad
  }

  def sad: Receive = {
    case "How are you?" =>
      sender ! "sad"
      context become happy
  }
  override def receive: Receive = happy
}
