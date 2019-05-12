package scala99

object p24 {
  import p23.randomSelect
  def lotto(count: Int, max: Int): List[Any] = 
    randomSelect(count, List.range(1, max + 1))
}