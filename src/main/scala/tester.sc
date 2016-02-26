val doubleEvents: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
  override def isDefinedAt(x: Int) = x % 2 == 0

  override def apply(v1: Int) = v1 * 2
}

val tripleOdds: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
  override def isDefinedAt(x: Int) = x % 2 != 0

  override def apply(v2: Int) = v2 * 3
}

val doubleNum: PartialFunction[Int, Int] = {
  case x if (x % 2) == 0 => x * 2
}

val tripleNum: PartialFunction[Int, Int] = {
  case x if (x % 2 != 0) => x * 3
}

val addFive = (x: Int) => x + 5

val minThree = (x: Int) => x - 3

val whatToDo = doubleEvents orElse(tripleOdds) andThen(addFive) andThen(minThree)

val v1 = whatToDo(5)

println(v1)
val j = 190
println("%d bottles of beer on the wall" format j - 100 )
class KoanIntWrapper(val original: Int) {
  def isOdd = original % 2 != 0
}

implicit def thisMethodNameIsIrrelevant(value: Int) = new KoanIntWrapper(value)

19.isOdd

import java.math.BigInteger
implicit def Int2BigIntegerConvert(value: Int): BigInteger = new BigInteger(value.toString)
def add(a: BigInteger, b: BigInteger) = a.add(b)

add(Int2BigIntegerConvert(3), Int2BigIntegerConvert(6)) == Int2BigIntegerConvert(9)

class Employee(val firstName: String,
               val middleName: Option[String],
               val lastName: String)

object Employee {
  //factory methods, extractors, apply
  //Extractor: Create tokens that represent your object
  def unapply(x: Employee) =
    Some(x.lastName, x.middleName, x.firstName)
}

val singri = new Employee("Singri", None, "Keerthi")

val result = singri match {
  case Employee("Singri", None, x) => "Yay, Singri %s! with no middle name!".format(x)
  case Employee("Singri", Some(x), _) => "Yay, Singri with a middle name of %s".format(x)
  case _ => "I don't care, going on break"
}

println(result)

class Car(val make: String, val model: String, val year: Short, val topSpeed: Short)
class Employee1(val firstName: String, val middleName: Option[String], val lastName: String)

object Tokenizer {
  def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)

  def unapply(x: Employee1) = Some(x.firstName, x.lastName)
}

val result1 = new Employee1("Kurt", None, "Vonnegut") match {
  case Tokenizer(c, d) => "c: %s, d: %s".format(c, d)
  case _ => "Not found"
}

print(result1)

object PigLatinizer {
  def apply(x: => String) = x.tail + x.head + "ay"
}

val result2 = PigLatinizer {
  val x = "pret"
  val z = "zel"
  x ++ z //concatenate the strings
}
//pretzel -> retzelpay
println(result2)

def repeatedParameterMethod(x: Int, y: String, z: Any*) = {
  "%d %ss can give you %s".format(x, y, z.mkString(", "))
}

repeatedParameterMethod(3, "egg",List("a delicious sandwich", "protein", "high cholesterol") )


class CalculatesAgeUsingProperty(var currentYear: Int, birthYear: Int) {
  // does age stay up to date if defined as a var instead of a val?
  val age = currentYear - birthYear
  // calculated at instantiation, returns property when called
}

val me = new CalculatesAgeUsingProperty(2010, 2003)

me.currentYear = 2011
println(me.age)

object Planets extends Enumeration {
  val Mercury = Value
  val Venus = Value
  val Earth = Value
  val Mars = Value
  val Jupiter = Value
  val Saturn = Value
  val Uranus = Value
  val Neptune = Value
  val Pluto = Value
}

Planets.Mercury.id
Planets.Earth.id

Planets.Mercury.toString
case class Orange

class MyContainer[A](a: A)(implicit manifest: scala.reflect.Manifest[A]) {
  private[this] var item = a

  def get = item

  def set(a: A) {
    item = a
  }

  def contents = manifest.runtimeClass.getSimpleName
}

val fruitBasket = new MyContainer(new Orange)


println(fruitBasket.contents)

val list = List.empty[A]

val list = scala.collection.mutable.ArrayBuffer.empty

val list1 = Seq.empty[A]
val list2 = Array.empty[A]
