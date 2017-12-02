# Scala-99
My codes for [scala99](http://aperiodic.net/phil/scala/s-99/)
naively written with somewhat functional style

### Notes for myself

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
* 可以在不需要匹配的地方用 _ 代替参数 例如 `case (first, second, _) => {}`
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
* 
