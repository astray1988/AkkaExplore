package router

import akka.actor.{Props, ActorSystem}
import akka.routing.RandomGroup
import router.Worker.Work

/**
  * Created by dylan on 2/16/16.
  */
object RandomGroupMain extends App {
  val system = ActorSystem("Random-Router")

  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")
  system.actorOf(Props[Worker], "w4")

  val paths = List("/user/w1", "/user/w2", "/user/w3", "/user/w4")

  val routerGroup = system.actorOf(RandomGroup(paths).props(), "random-router-group")

  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()

  Thread.sleep(100)
  system.terminate()

}
