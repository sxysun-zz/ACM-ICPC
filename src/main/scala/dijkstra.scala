package org.sxysun.algo.graph {
  object dijkstra {
    import org.sxysun.algo.AdjacentMatrix._
    def shortestPath(from: Int, g: AdjacentMatrix): Array[Int] = {
      var dist: Array[Int] = Array.fill(g.verticeNum)(g.INF)
      var s = collection.mutable.Set[Int]()
      (0 until g.verticeNum).foreach(x => s.add(x))
      dist(from) = 0
      while(s.nonEmpty) {
        val interm = s.minBy(dist(_))
        s.remove(interm)
        for(x <- 0 until g.verticeNum) {
          if(s.contains(x) && // node is not in shortest path
            g.adjMatrix(interm)(x) != 0 && // there is a path from interm to x
            dist(x) > dist(interm) + g.adjMatrix(interm)(x)
          ) dist(x) = dist(interm) + g.adjMatrix(interm)(x)
        }
      }
      dist
    }
    def test(): Unit = {
      val < = Array
      val g = AdjacentMatrix(9)
      g.feed(
        <(
          <(0, 4, 0, 0, 0, 0, 0, 8, 0),
          <(4, 0, 8, 0, 0, 0, 0, 11, 0),
          <(0, 8, 0, 7, 0, 4, 0, 0, 2),
          <(0, 0, 7, 0, 9, 14, 0, 0, 0),
          <(0, 0, 0, 9, 0, 10, 0, 0, 0),
          <(0, 0, 4, 14, 10, 0, 2, 0, 0),
          <(0, 0, 0, 0, 0, 2, 0, 1, 6),
          <(8, 11, 0, 0, 0, 0, 1, 0, 7),
          <(0, 0, 2, 0, 0, 0, 6, 7, 0)
        )
      )
      val dist = shortestPath(0, g)
      for (elem <- dist) {
        if(elem == g.INF) print("INF ")
        else print(elem + " ")
      }
    }
  }
}