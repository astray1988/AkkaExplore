trait Person {
  def name: String
  def age:Int
}

object Person {
  def unapply(person: Person): Option[(String, Int)] =
    Some((person.name, person.age))

  def apply(name: String, age: Int) = Some((name, age))
}

val somePerson = Person("Jon", 12)

//somePerson match {
//  case Some((n, a)) => a
//  case None =>
//}


trait vehicle {
  def model: String
  def year: Int
}

class SUV(val model: String, val year: Int) extends vehicle

class Truck(val model: String, val year: Int, val weight: Int) extends vehicle

object Truck {
  def unapply(truck: Truck): Option[(String, Int, Int)] = Option(truck.model, truck.year, truck.weight)
}

object SUV {
  def unapply(car: SUV): Option[(String, Int)] = Option(car.model, car.year)
}

//val someCar = new SUV("Honda-CRV", 2009)
val someCar = new Truck("Honda-CRV", 2009, 3)

someCar match {
  case SUV(m, _) => println(s"$m")
  case Truck(_, _, weight) => println(s"$weight")
  case _ => println(s"None car exist")
}



