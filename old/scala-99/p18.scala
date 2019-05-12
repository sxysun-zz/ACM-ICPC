package scala99

object p18 {
  def slice(m:Int, n:Int, l:List[Any]):List[Any] = {
    l.zipWithIndex.filter(x => (x._2 >= m && x._2 < n)).map(a1 => a1._1)
  }
  
  def main(arg:Array[String]):Unit = {
    println(slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }
}