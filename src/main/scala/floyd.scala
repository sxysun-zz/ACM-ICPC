package org.sxysun.algo.graph {
  object floyd {
    import org.sxysun.algo.AdjacentMatrix._
    def shortestPath(g: AdjacentMatrix): Array[Array[Int]] = {
      var pairDist: Array[Array[Int]] = Array.ofDim[Int](g.verticeNum, g.verticeNum)
      for(i <- 0 until g.verticeNum) {
        for(j <- 0 until g.verticeNum) {
          pairDist(i)(j) = g.adjMatrix(i)(j)
        }
      }
      (0 until g.verticeNum).foreach(k => {
        (0 until g.verticeNum).foreach(i => {
          (0 until g.verticeNum).foreach(j => {
            if (pairDist(i)(k) + pairDist(k)(j) < pairDist(i)(j))
              pairDist(i)(j) = pairDist(i)(k) + pairDist(k)(j)})})})
      pairDist
    }
    def test(): Unit = {
      var g = AdjacentMatrix(4)
      val > = Array
      val INF: Int = g.INF
      g.feed(Array(
        >(0, 5, INF, 10),
        >(INF, 0, 3, INF),
        >(INF, INF, 0, 1),
        >(INF, INF, INF, 0)
      ))
      val dist = shortestPath(g)
      g.printMatrix(dist)
    }
  }
}
