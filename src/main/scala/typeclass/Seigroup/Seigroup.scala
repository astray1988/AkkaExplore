package typeclass

/**
  * Created by dylan on 2/28/16.
  */
trait Semigroup[A] {
  def append(a: A, b: A): A
}


object Seigroup {
  implicit val intSemigroup = new Semigroup[Int] {
    def append(a: Int, b: Int): Int = a + b
  }

  implicit val stringSemigroup = new Semigroup[String] {
    override def append(a: String, b: String): String = a + b
  }

  implicit val bigIntSemigroup = new Semigroup[BigInt] {
    override def append(a: BigInt, b: BigInt): BigInt = append(a, b)
  }

  def apply[A](implicit F: Semigroup[A]) = F

}

object SeigroupTest extends App {

  implicit val intSemigroup = new Semigroup[Int] {
    def append(a: Int, b: Int): Int = a + b
  }

  implicit val stringSemigroup = new Semigroup[String] {
    override def append(a: String, b: String): String = a + b
  }

  implicit val bigIntSemigroup = new Semigroup[BigInt] {
    override def append(a: BigInt, b: BigInt): BigInt = append(a, b)
  }



  println(Seigroup.apply[Int].append(1,2))
  println(Seigroup.apply[String].append("dsdfdsf","fdff"))

}


