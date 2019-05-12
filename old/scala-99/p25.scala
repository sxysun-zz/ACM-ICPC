package scala99

import scala.util.Random

object p25 {
  def randomPermute[T](l:List[T]):List[T] = {
    def rr(t:Tuple2[List[T], List[T]]):List[T] = {
      if(t._1 == Nil) t._2
      else{
        val te = t._1.zipWithIndex.span(_._2 == (Random nextInt t._1.length))
        rr((te._1 map(_._1), 
            te._2.map(_._1) ::: t._2))
      }
    }
    rr((l, Nil))
  }
}