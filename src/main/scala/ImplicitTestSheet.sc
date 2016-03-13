import ImplictBags.holder.Foo

def method(implicit foo: Foo) = print(foo)

implicitly[List[Foo]]

method