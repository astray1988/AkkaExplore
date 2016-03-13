/**
  * Created by dylan on 3/5/16.
  */
abstract class SemiGroup[A] {
  def add(x: A, y: A): A
}

abstract class Monoid[A] extends SemiGroup[A] {
  def unit: A
}

object ImplicitTest extends App {
  implicit object StringMonoid extends Monoid[String] {
    override def unit: String = ""

    override def add(x: String, y: String): String = x concat(y)
  }

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
    if (xs.isEmpty) m.unit
    else m.add(xs.head, sum(xs.tail))
  println(sum(List("a", "d", "sdfsdf")))
}
