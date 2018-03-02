# Some Naive codes for ACM and SICP and LISP
Implemented in functional style horribly, I mainly write my code in racket/ scheme/ scala

### Notes for myself: Racket/Common Lisp

### Notes for myself: Scala
* :\ foldRight
* /: foldLeft
* ++ concat anything
* +: prepend element
* :+ postpend element
* <: derive from
* `Tuple2._1 Tuple2._2`
* Right/Left Either
* `span` seperates a list by using predicate
* `zip` and `zipWithIndex`
* 对于trivial 的匿名函数可以 _ 简化代码
* :::是取出List元素拼接，::是直接拼接List
* def myAssert2(pred: =>Boolean) = if(!pred) throw new AssertionError 可以省略函数空调用
* <% 弱上界 <: 上界 >: 下界
* +T: 协变`covariance：class Queue[+T] {}` 如果C<:A，则`Queue[C] <: Queue[A] `
* -T: 逆变`contravariance : class Queue[-T] {}` 如果C<:A,则`Queue[C] >: Queue[A] `
* 如果一个类型支持协变或逆变，则称这个类型为variance(翻译为可变的或变型)，否则称为invariant(不可变的)
* 类似于 java 的 `List<? extends Object> list = new ArrayList<String>();`
* $ 直接打印
* case class 用来做模式匹配(case类的参数列表默认是val的,会有和类名一样的工厂方法。不需要使用new来创建这个类对象), like the following:
``` scala
case class Var(name:String)  
val v1 = Var("peter")  
v1 match {  
   case Var(name) => name  
   case _ =>  
}  
```
* pattern guard 放在模式匹配 => 之前的`if`
* __Problem 26__ 的操作??
* 可以在不需要匹配的地方用 _ 代替参数 例如 `case (first, second, _) => {}` 但是变长参数需要用 _*
* Traits and classes can be marked sealed which means all subtypes must be declared in the same file. This assures that all subtypes are known. This is useful for pattern matching because we don’t need a “catch all” case.
``` scala
sealed abstract class Furniture
case class Couch() extends Furniture
case class Chair() extends Furniture

def findPlaceToSit(piece: Furniture): String = piece match {
  case a: Couch => "Lie on the couch"
  case b: Chair => "Sit on the chair"
}
```
* @ 用于模式匹配大致相当于 : 
``` scala
al l = List(1,2,3)
val s = l match {
  case list @ List(1, _*) => s"a start value is 1 list :$list"
  case list : List[_] => s"a start value not 1 list"
}
// 看书上说是为了取出模式匹配后对应的原来输入值，例如上面例子中的模式匹配是为了匹配1开始的列表，如果写成case x: List(1, _*)   是无法编译通过的，而写成@则可以编译通过并将值赋予list
```
* 变长参数:
``` scala 
def sum(args: Int*) = {
     | var result = 0
     | for (arg <- args) result += arg
     | result
     | }
```
* 转换参数序列: `val s = sum(1 to 5: _*)`
* for yield expression:
``` scala
def scalaFiles = 
  for {
    file <- filesHere
    if file.isFile
    if file.getName.endsWith(".scala")
  } yield file
  /*
  * yield 关键字的简短总结:
  * 针对每一次 for 循环的迭代, yield 会产生一个值，被循环记录下来 (内部实现上，像是一个缓冲区).
  * 当循环结束后, 会返回所有 yield 的值组成的集合.
  * 返回集合的类型与被遍历的集合类型是一致的.
  */
  ```
  * 1 to 10, 两边都是包含的
  * callback回调就是B和A互操
  * closure闭包就是调用外部的自由变量的函数 和Currying 相似
  * Lambda Calculus:
  > 
    α-conversion: changing bound variables (alpha);
    β-reduction: applying functions to their arguments (beta);
    η-conversion: which captures a notion of extensionality (eta).
* 
> Scala Option(选项)类型用来表示一个值是可选的（有值或无值)。Option[T] 是一个类型为 T 的可选值的容器： 如果值存在， Option[T] 就是一个 Some[T] ，如果不存在， Option[T] 就是对象 None 。
在Scala里Option[T]实际上是一个容器，就像数组或是List一样，你可以把他看成是一个可能有零到一个元素的List。
当你的Option里面有东西的时候，这个List的长度是1（也就是 Some），而当你的Option里没有东西的时候，它的长度是0（也就是 None）。
* `apply` & `unapply`适用于模式匹配 一般返回提取的值
