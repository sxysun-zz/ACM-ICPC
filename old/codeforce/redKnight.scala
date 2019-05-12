package scalaAlgo

object redKnight {

  import scala.math.abs
  
    def printShortestPath(cst:List[Tuple3[Int, Int, String]], l: List[String], 
        i_start: Int, j_start: Int, i_end: Int, j_end: Int):Unit  =  {
       if((i_start + i_end)%2==1) println("Impossible")
       else if(i_start == i_end && j_start == j_end) {
         println(l.length)
         l map(x => print(x + " "))
       } else {
         val raw = cst.map(x => Tuple2(penalty(i_start+x._1, j_start+x._2, i_end, j_end), x._3))
           .sortWith(_._1 > _._1)
         val chosen = ("L" /: raw.filter(_._1 == raw.reverse.head._1)) ((x, xs) => {
           if(cst.zipWithIndex.filter(_._1._3 == xs._2).head._2 < 
               cst.zipWithIndex.filter(_._1._3 == x).head._2) xs._2
           else x
         });
         printShortestPath(cst, l:+chosen, i_start+cst.filter(_._3 == chosen).head._1, 
             j_start+cst.filter(_._3 == chosen).head._2, i_end, j_end)
       }
    }
    
    def penalty(i:Int, j:Int, ei:Int, ej:Int):Int  = {
         abs(i-ei) + abs(j-ej)
    }

    def main(args: Array[String]) {
        val sc = new java.util.Scanner (System.in);
        var n = sc.nextInt();
        var i_start = sc.nextInt();
        var j_start = sc.nextInt();
        var i_end = sc.nextInt();
        var j_end = sc.nextInt();
        printShortestPath(List(Tuple3(-2, -1, "UL"), Tuple3(-2, 1, "UR"), 
             Tuple3(0, 2, "R"), Tuple3(2, 1, "LR"), Tuple3(2, -1, "LL"), 
             Tuple3(0, -2, "L"))
             , List(), i_start, j_start, i_end, j_end);
    }
}

