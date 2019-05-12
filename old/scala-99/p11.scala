package scala99

object p11 {
  import scala99.p10.encode
  
  def encodeModified[T](l:List[T]):List[Any] = {
    encode(l).map(x => {if(x._1 == 1) x._2 else x})
  }
  
  //Either
  def encodeModified2[T](l:List[T]):List[Any] = {
    encode(l) map {x => if (x._1 == 1) Left(x._2) else Right(x)}
  }
}