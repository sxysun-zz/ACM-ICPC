package scala99

object p21 {
  def insertAt(e:Any, n:Int, l:List[Any]):List[Any] = {
    l.splitAt(n)._1:::e::l.splitAt(n)._2
  }
}