package scala99

object p7 {
  def flatten(l:List[Any]):List[Any] = l flatMap {
    case tp:List[_] => flatten(tp)
    case e => List(e)
  }
}