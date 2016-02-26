package akkaHttp

import spray.json.DefaultJsonProtocol

/**
  * Created by dylan on 2/25/16.
  */
case class IpInfo(ip: String)

object JsonProtocol extends DefaultJsonProtocol {
  implicit val format = jsonFormat1(IpInfo.apply)
}

