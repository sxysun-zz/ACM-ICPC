package scala99

object p8 {
  def compress(l:List[Any]):List[Any] = (l :\ List[Any]()) { (h, r) =>
      if (r.isEmpty || r.head != h) h :: r
      else r
  }
}