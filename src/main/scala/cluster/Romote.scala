package cluster

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

/**
  * Created by dylan on 2/21/16.
  */

object MembersService extends App {
  val config = ConfigFactory.load.getConfig("MembersService")

  val system = ActorSystem("MembersService", config)

  val worker = system.actorOf(Props[Worker], "remote-worker")

  println(s"Worker actor path is ${worker.path}")



  system.terminate()


}

object MemberServiceLookup extends App {
  val config = ConfigFactory.load.getConfig("MembersServiceLookup")

  val system = ActorSystem("MembersServiceLookup", config)

  val worker = system.actorSelection("akka.tcp://MembersService@127.0.0.1:2552/user/remote-worker")

  worker ! Worker.Work("Hi Remote Actor")
}
class Romote {

}
