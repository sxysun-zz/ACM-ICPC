package scala99

import scala.annotation.tailrec

object p22 {
  def range(m:Int, n:Int):List[Int] = {
    @tailrec
    def irange(m:Int, l:List[Int]):List[Int] = l match{
      case Nil => List(m)
      case _ => if(m<=n) irange(m+1, m::l) else l
    }
    irange(m, Nil)
  }
}