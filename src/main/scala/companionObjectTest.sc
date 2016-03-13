import scala.util.Try
class Car(val make: String) {
  override def toString = s"Car manufacturer is $make"
}
object Car {
  def apply(make: String) = new Car(make)
}
val myCar = Car("Honda")

implicit def stringToInt(s: String) =
  s match {
    case "two" => "2"
  }

val sum = for {
  int1 <- Try(Integer.parseInt("one")).recover { case e => 0 }
  int2 <- Try(Integer.parseInt("2"))
} yield {
  int1 + int2
}

println(sum)
