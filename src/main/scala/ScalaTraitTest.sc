import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1

trait B {
  def bar = "default B bar"
}

trait BX extends B {
  override def bar = "bar bx"
}

trait BY extends B {
  override def bar = "bar by"
}

trait A1 {
  self: B =>

  def doIt: Unit = {
    println(bar)
  }

}

trait A2 extends B {
  def doIt = {
    println(bar)
  }
}

object Things1 extends A1 with B

object Things1X extends A1 with BX

object Things1Y extends A1 with BY

object Things2 extends A2

object Things21 extends A2 with BX

//Things1.doIt
//
//Things1X.doIt
//
//Things2.doIt
//Things2.doIt
