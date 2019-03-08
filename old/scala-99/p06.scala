package scala99

object p6 {
  def isPalindrome[T](l:List[T]):Boolean = (List[T]() /: l) ((x,xs) => xs :: x) == l
}