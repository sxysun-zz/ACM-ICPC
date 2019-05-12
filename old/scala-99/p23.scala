package scala99

import scala.util.Random

object p23 {
  def randomSelect(n:Int, l:List[Any]):List[Any] = {
    def rin(n:Int, r:Random):List[Any] = {
      if(n == 0) l
      else 
        l.zipWithIndex.filter(a => a._2 == r.nextInt(l.length)).map(x => x._1):::rin(n-1, r)
    }
    rin(n, Random)
  }
}