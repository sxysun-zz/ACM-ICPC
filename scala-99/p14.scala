package scala99

object p14 {
  def duplicate(l:List[Any]):List[Any] = (l :\ List[Any]()) {(xs, x) => xs::xs::x}
  /*
   * or like this
   * 
   * def duplicate(l:List[Any]):List[Any] = l.flatMap(x => List(x, x))
   * 
   */
}