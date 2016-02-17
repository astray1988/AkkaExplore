val list1 = (1 to 5).toList
val list2 = (1 to 5) map(x => x * 1)
val s = for (
  l1 <- list1;
  l2 <- list2
) yield {
  (l1,l2)
}
println(s)