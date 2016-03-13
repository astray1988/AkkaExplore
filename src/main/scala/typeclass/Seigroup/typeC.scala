package typeclass.Seigroup

/**
  * Created by dylan on 3/11/16.
  */
class typeC {

}

sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json

final case class JsString(get: String) extends Json

final case class JsNumber(get: Double) extends Json

// define Json writer trait
trait JsonWriter[A] {
  def writer(value: A): Json
}

case class Person(name: String, email: String)


object DefaultJsonWriter {
  implicit val stringJsonWriter = new JsonWriter[String] {
    override def writer(value: String): Json = JsString(value)
  }

  implicit val personJsonWriter = new JsonWriter[Person] {
    override def writer(value: Person): Json =
      JsObject("name" -> JsString(value.name), "email" -> JsString(value.email))
  }
}

object Json {
  def toJson[A](value: A)(implicit writer: JsonWriter[A] ): Json = writer.writer(value)

}