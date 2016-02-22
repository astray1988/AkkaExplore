import java.util.Date

val array = Array(87,44, 5, 4, 200, 100, 10, 39)

val oddAndSmallPartial: PartialFunction[Int, String] = {
  case x: Int if(x % 2 != 0 && x < 100) => "Odd and less than 100"
}

val evenAndSmallPartial: PartialFunction[Int, String] = {
  case x: Int if x != 0 && x % 2 == 0 && x < 100 => "Even and less than 100"
}

val negativePartial: PartialFunction[Int, String] = {
  case x: Int if x < 0 => "Negative Number"
}

val largePartial: PartialFunction[Int, String] = {
  case x: Int if x > 99 => "Large Number"
}

val zeroPartial: PartialFunction[Int, String] = {
  case x: Int if x == 0 => "Zero"
}

val result = array groupBy(
  oddAndSmallPartial orElse
  evenAndSmallPartial orElse
  negativePartial orElse
  largePartial orElse
  zeroPartial
  )

val even = result("Even and less than 100")
println(even)

val result2 = array forall(_ < 100)

val list = List(5,4,3,2,1)
val r1 = list.foldRight(0)((acc, e) => acc - e)
val r2 = (list :\ 0){
  (acc, e) => acc - e
}
val r3 = (list :\ 0)(_ - _)
val r4 = list.foldRight(0)(_ - _)

val r5 = list.reduceLeft(_ + _)
val r6 = list.reduceLeft((acc, e) => acc + e)

val stringList = List("Do", "Re", "Me", "Fa", "So", "La", "Te", "Do")
stringList.reduceRight {
  _ + _
}

val MAX_SIZE = 1000000
val reduceLeftStartTime = new Date()
(1 to MAX_SIZE) reduceLeft (_ + _)
val reduceLeftEndTime = new Date()

val reduceRightStartTime = new java.util.Date
(1 to MAX_SIZE) reduceRight (_ + _)
val reduceRightEndTime = new java.util.Date

val totalReduceLeftTime = reduceLeftEndTime.getTime - reduceLeftStartTime.getTime
val totalReduceRightTime = reduceRightEndTime.getTime - reduceRightStartTime.getTime

println(totalReduceLeftTime)
println(totalReduceRightTime)


val list2 = List(List(1), List(4))
val list3 = list2.transpose

val list4 = List(1, 2, 3, 4, 5)

list4.mkString(",")

list4.mkString(">", ",", "<")

val stringBuilder = new StringBuilder()
val list6 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)

stringBuilder.append("I want all numbers 6-12: ")
list.filter(it => it > 5 && it < 13).addString(stringBuilder, ",")
println(stringBuilder.mkString)

val lst = List(1, 2, 3)
var history = List[String]()

def addHistory(s: String): Unit = {
  history = history :+ s
}

//val l1 = lst.map { x =>
//  addHistory("Doubling %s".format(x))
//  x * 2
//}
//
//val l2 = l1.map { x => addHistory("Adding 1 to %s".format(x)); x + 1}

println(history)

val view = list.view.map {
  x => addHistory("Doubling %s".format(x)); x * 2
}.map {
  x => addHistory("Adding 1 to %s".format(x)); x + 1}.force

println(view)

history(1)