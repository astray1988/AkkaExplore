package ImplictBags

object holder {
  trait Foo
  object Foo {
    implicit val x = new Foo {
      override def toString = "Companion Foo"
    }
    implicit val list = List(1,2,3,4)
  }
}