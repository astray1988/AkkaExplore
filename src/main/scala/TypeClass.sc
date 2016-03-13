sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json

final case class JsString(get: String) extends Json

final case class JsNumber(get: Double) extends Json

// define Json writer trait
trait JsonWriter[A] {
  def writer(value: A): Json
}

// type class instance

case class Person(name: String, email: String)

//object DefaultJsonWriter {
//  implicit val stringJsonWriter = new JsonWriter[String] {
//    override def writer(value: String): Json = JsString(value)
//  }
//
//  implicit val personJsonWriter = new JsonWriter[PersonT] {
//    override def writer(value: PersonT): Json =
//      JsObject("name" -> JsString(value.name), "email" -> JsString(value.email))
//  }
//}



