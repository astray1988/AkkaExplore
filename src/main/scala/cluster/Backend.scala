package cluster

import akka.actor.{Props, ActorSystem, RootActorPath, Actor}
import akka.actor.Actor.Receive
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.MemberUp
import com.typesafe.config.ConfigFactory

/**
  * Created by dylan on 2/21/16.
  */
class Backend extends Actor {
  val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberUp])

  }

  override def postStop() = {
    cluster.unsubscribe(self)
  }

  override def receive: Receive = {
    case Add(num1, num2) =>
      println(s"============== I'm a backend with path ${self} and I received add operation ===============")
    case MemberUp(member) =>
      if (member.hasRole("frontend")) {
        context.actorSelection(RootActorPath(member.address) / "user" / "frontend") ! BackendRegistration
      }
  }
}

object Backend {
  def initiate(port: Int) = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port").
      withFallback(ConfigFactory.load.getConfig("Backend"))

    val system = ActorSystem("ClusterSystem", config)

    val Backend = system.actorOf(Props[Backend], name = "Backend")

  }
}

case class Add(num1: Int, num2: Int)
case object BackendRegistration