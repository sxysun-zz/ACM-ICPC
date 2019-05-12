package scala99

object p16 {
  def drop(n:Int, l:List[Any]):List[Any] = {
    l.zipWithIndex.filter(x => x._2 != n).map(_._1)
  }
  
  def main(arg:Array[String]):Unit = {
    println(drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }
}