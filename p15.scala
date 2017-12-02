package scala99

object p15 {
  def duplicateN(n:Int, l:List[Any]):List[Any] = {
    (l :\ List[Any]()) {(xs, x) => ((List[Any]()) /: (1 to n).map(a => xs)) ((a, as) => as::a):::x}
  }
  
  def main(arg:Array[String]):Unit = {
    println(duplicateN(3, List('a, 'b, 'c, 'c, 'd)))
  }
}