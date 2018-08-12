package scala99

import scala.util.Random
import scala.math._

object ini {
  def iniIntList(len:Int, l:List[Int]):List[Int] = l match{
    case Nil => iniIntList(len, List(Random.nextInt).map(x=>abs(x%10)))
    case _ => {
      if(l.length!=len) iniIntList(len, l++List(Random.nextInt).map(x=>abs(x%10)))
      else l
    }
  }
}