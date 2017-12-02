package scala99

import scala.io._
import java.lang.Exception

object p1_5{
  def main(arg: Array[String]):Unit = {
    //Read User Input
    val len = StdIn.readInt
    val li = ini.iniIntList(len, Nil)
    println(li)
    //P1
    println(lastElement(li))
    //P2
    println(lastButOneElement(li))
    //P3
    println(getElementAt(3, li))
    //P4
    println(length(li))
    //P5
    println(reverseList(li))
  }
  
  //Problem 1
  def lastElement[T](l:List[T]):T  = l match {
    case Nil => throw new RuntimeException("Input List not given")
    case a::Nil => a
    case _::rem => lastElement(rem)
  }
  
  //Problem 2
  def lastButOneElement[T](l:List[T]):T = l match{
    case Nil => throw new RuntimeException("Input List is not given")
    case a::_::Nil => a
    case _::rem => lastButOneElement(rem)
  }
  
  //Problem 3
  def getElementAt[T](n:Int, l:List[T]):T = {
    if(n > length(l)) throw new RuntimeException("Index out of bound")
    def findElement[T](l:List[T], c:Int):T = l match {
      case Nil => throw new RuntimeException("element not found")
      case x::rem => {
        if(c == n) x
        else findElement(rem, c + 1)
      }
    }
    findElement(l, 0)
  }
  
  //Problem 4
  def length[A](l:List[A]):Int = {
    def rl(ls:List[A], n:Int):Int = ls match{
      case Nil => n
      case _::rem => rl(rem, n+1)
    }
    rl(l, 0)
  }
  
  //Problem 5 
  def reverseList[T](l:List[T]):List[T] = {
    (List[T]() /: l) ((x,xs) => xs :: x)
  }
}