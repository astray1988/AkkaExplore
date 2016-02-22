package cluster

import akka.actor._
import akka.actor.Actor.Receive
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.ConfigFile
import com.typesafe.config.ConfigFactory

import scala.util.Random

/**
  * Created by dylan on 2/21/16.
  */
class Frontend extends Actor {

  var backends = IndexedSeq.empty[ActorRef]

  override def receive: Receive = {
    case Add if backends.isEmpty =>
      println(s"Serviec unavailable, cluster doesn't have backend node.")
    case addOp: Add =>
      println(s"Frontend: I will forward add operation to backend node to handle it")
      backends(Random.nextInt(backends.size)) forward(addOp)
    case BackendRegistration if !(backends.contains(sender())) =>
      backends = backends :+ sender()
      context watch(sender())

    case Terminated(a) =>
      backends = backends.filterNot(_ == a)
  }
}

object Frontend {
  private var _frontend: ActorRef = _

  def initiate() = {
    val config = ConfigFactory.load().getConfig("Frontend")
    val system = ActorSystem("ClusterSystem", config)

    _frontend = system.actorOf(Props[Frontend], name = "frontend")
  }

  def getFrontend = _frontend

}