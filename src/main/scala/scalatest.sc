class WithParameterRequirement(val myValue: Int) {
  require(myValue != 0)

  def this(someValue: String) {
    this(someValue.size)
  }
}

val myInstance = new WithParameterRequirement("Do you really like my hair?")
println(myInstance.myValue )
println("sdfsdf sfsdfs".size)

//intercept[IllegalArgumentException] {
//  new WithParameterRequirement("")
//}

abstract class SemiGroup[A] {
  def add(x: A, y: A): A
}
abstract class Monoid[A] extends SemiGroup[A] {
  def unit: A
}

implicit object StringMonoid extends Monoid[String] {
  override def unit: String = ""

  override def add(x: String, y: String): String = x concat(y)
}

implicit object IntMonoid extends Monoid[Int] {
  override def unit: Int = 0

  override def add(x: Int, y: Int): Int = x + y
}

def sum[A](xs: List[A])(implicit m: Monoid[A]): A = {
  if (xs.isEmpty) m.unit
  else m.add(xs.head, sum(xs.tail))
}

println(sum(List.empty[Int]))

println(sum(List(1,2,3,4,5)))
