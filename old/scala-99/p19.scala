package scala99

object p19 {
  def rotate(n:Int, l:List[Any]):List[Any] = {
    l.drop(n):::l.take(n)
  }
  
  def main(arg:Array[String]):Unit = {
    println(rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }
}