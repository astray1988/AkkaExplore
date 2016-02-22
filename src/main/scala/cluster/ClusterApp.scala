package cluster

/**
  * Created by dylan on 2/21/16.
  */
object ClusterApp extends  App {

  Frontend.initiate()

  Backend.initiate(2552)
  Backend.initiate(2560)
  Backend.initiate(2561)

  Thread.sleep(1000)

  Frontend.getFrontend ! Add(2, 4)
}
