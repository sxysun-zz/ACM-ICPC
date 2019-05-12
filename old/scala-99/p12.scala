package scala99

object p12 {
  def decode[T](l:List[(Int, T)]):List[T] = l flatMap(x => 1 to x._1 map(a => x._2))
}