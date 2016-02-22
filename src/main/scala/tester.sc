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

