package router

import akka.actor.{ActorSystem, Props}
import router.Worker.Work

/**
  * Created by dylan on 2/16/16.
  */
object RouterMain extends App {
  val system = ActorSystem("router")

  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")
  system.actorOf(Props[Worker], "w4")
  system.actorOf(Props[Worker], "w5")

  val workers: List[String] = List(
    "/user/w1",
    "/user/w2",
    "/user/w3",
    "/user/w4",
    "/user/w5"
  )
  val routerGroup = system.actorOf(Props(classOf[RouterGroup], workers))

  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()



  Thread.sleep(1000)

  system.terminate()
}
