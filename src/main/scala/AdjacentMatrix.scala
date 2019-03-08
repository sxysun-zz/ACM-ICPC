package org.sxysun.algo.AdjacentMatrix {
  case class AdjacentMatrix(verticeNum: Int){
    val INF: Int = 910046
    def printMatrix(s: Array[Array[Int]]): Unit =
      print(s.map(x => ("" /: x)((a, b) => a + (b match {
        case 910046 => "INF "
        case _ => b.toString + " "
      }))).mkString("\n"))
    var adjMatrix: Array[Array[Int]] = Array.ofDim[Int](verticeNum, verticeNum)
    def feed(m: Array[Array[Int]]): Unit = adjMatrix = m
    def initialize(): Unit =
      for(i <- 0 until verticeNum) {
        for (j <- 0 until verticeNum) {
          adjMatrix(i)(j) = INF
        }
      }
    def addEdge(node1: Int, node2: Int, len: Int): Unit =
      adjMatrix(node1)(node2) = len
  }
}
