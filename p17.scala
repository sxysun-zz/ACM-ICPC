package scala99

object p17 {
  def split(n:Int, l:List[Any]):Tuple2[List[Any], List[Any]] = {
    val t = l.zipWithIndex.span(x => x._2 <= n)
    Tuple2(t._1.map(a => a._1), t._2.map(a => a._1))
  }
  
  /*
   * or 
   * 
   * def splitFunctional[A](n: Int, ls: List[A]): (List[A], List[A]) =
   * (ls.take(n), ls.drop(n))
   */
  
  def main(arg:Array[String]):Unit = {
    println(split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }
}