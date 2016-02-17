package router

import akka.actor.{Props, ActorSystem}
import akka.routing.FromConfig
import router.Worker.Work

/**
  * Created by dylan on 2/16/16.
  */
object RandomMain extends App {
  val system = ActorSystem("Random-Router")
  val routerPoll = system.actorOf(FromConfig.props(Props[Worker]), "random-router-pool")

  routerPoll ! Work()
  routerPoll ! Work()
  routerPoll ! Work()
  routerPoll ! Work()

  Thread.sleep(1000)
  system.terminate()

}
