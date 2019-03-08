package org.sxysun.algo.BST {
  sealed trait BST[V] {
    val value: V
    def map[N](f: V => N): Unit = this match {
      case Node(v, l, r) =>
        f(v)
        l.map(f)
        r.map(f)
      case Leaf(v) => f(v)
    }
  }
  case class Node[V](override val value: V, left: BST[V], right: BST[V]) extends BST[V] {
    override def toString: String = "Node(" + left.toString + ", " + right.toString + ")"
  }
  case class Leaf[V](override val value: V) extends BST[V] {
    override def toString: String = value.toString
  }
}