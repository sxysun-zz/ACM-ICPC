package scala99

object p20 {
  def removeAt(n:Int, l:List[Any]):Tuple2[List[Any], Any] = {
    (l.splitAt(n)._1:::l.splitAt(n)._2.drop(1), l.splitAt(n)._2.take(1))
  }
}