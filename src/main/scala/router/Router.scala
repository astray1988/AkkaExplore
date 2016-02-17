package router

import akka.actor.{Actor, Props, ActorRef}
import router.Worker.Work

/**
  * Created by dylan on 2/16/16.
  */
class RouterPoll extends Actor {
  var routees: List[ActorRef] = _

  override def preStart() = {
    routees = List.fill(5){
      context.actorOf(Props[Worker])
    }
  }

  override def receive: Receive = {
    case msg: Work =>
      println(s"I am a router, and i received a message... ")
      routees(util.Random.nextInt(routees.size)) forward msg

  }
}

class RouterGroup(routees: List[String]) extends Actor {
  override def receive: Actor.Receive = {
    case msg: Work =>
      println(s"I'm a router Group and I receive Work Messages...")
      context.actorSelection(routees(util.Random.nextInt(routees.size))) forward(msg)
  }
}
