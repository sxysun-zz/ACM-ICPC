package scala99

object p10 {
  def encode[T](l:List[T]):List[(Int, T)] = {
    p9.pack(l).map(ls => (ls.length, ls.head))
  }
}