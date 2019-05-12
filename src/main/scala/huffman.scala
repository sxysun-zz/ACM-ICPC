package org.sxysun.algo.greedy{
  sealed trait BST {
    val freq: Double = -1
    def traverse(collected: String, dict: collection.mutable.Map[String, String]): Unit = this match {
      case Node(_, l, r) =>
        l.traverse(collected + "0", dict)
        r.traverse(collected + "1", dict)
      case Leaf(_, w) => dict += (w -> collected)
    }
  }
  case class Node (override val freq: Double, left: BST, right: BST) extends BST {
    override def toString: String = "Node(" + left.toString + ", " + right.toString + ")"
  }
  case class Leaf (override val freq: Double, word: String) extends BST {
    override def toString: String = word
  }

  object huffman {
    def construct(freqs: Map[String, Double]): BST = {
      var pq = collection.mutable.PriorityQueue[BST]()(Ordering.by(x => x.freq))
      freqs.toSeq.map(x => pq+=Leaf(-x._2, x._1))
      def recurse(pq: collection.mutable.PriorityQueue[BST]): BST = pq.length match {
        case 1 => pq.dequeue
        case _ =>
          val i = pq.dequeue()
          val j = pq.dequeue()
          pq += Node(i.freq + j.freq, i, j)
          recurse(pq)
      }
      recurse(pq)
    }

    def test(): Unit = {
      val freqs = Map(
        "blank" -> 18.3,
        "e" -> 10.2,
        "t" -> 7.7,
        "a" -> 6.8,
        "o" -> 5.9,
        "i" -> 5.8,
        "n" -> 5.5,
        "s" -> 5.1,
        "h" -> 4.9,
        "r" -> 4.8,
        "d" -> 3.5,
        "l" -> 3.4,
        "c" -> 2.6,
        "u" -> 2.4,
        "m" -> 2.1,
        "w" -> 1.9,
        "f" -> 1.8,
        "g" -> 1.7,
        "y" -> 1.6,
        "p" -> 1.6,
        "b" -> 1.3,
        "v" -> 0.9,
        "k" -> 0.6,
        "j" -> 0.2,
        "x" -> 0.2,
        "q" -> 0.1,
        "z" -> 0.1
      )
      val encodingTree = construct(freqs)
      // println(encodingTree)
      var encoding = collection.mutable.Map[String, String]().empty
      encodingTree.traverse("", encoding)
      val expectedBits = (0.0 /: encoding)((b, a) => b + a._2.length * freqs(a._1))
      println(expectedBits)
    }
  }
}