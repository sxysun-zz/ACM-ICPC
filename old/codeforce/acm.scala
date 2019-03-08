object usaco {
	def main(args: Array[String]): Unit = {
			val sc = new java.util.Scanner (System.in);
    	val n = sc.nextInt
    	val k = sc.nextInt
    	val s = sc.nextInt
    	def get(l: Array[Int], n: Int): Array[Int] = n match {
    		case 0 => l
    		case _ => get(l:+sc.nextInt, n - 1)
    	}
    	def swap(a: Int, b: Int, ar: Array[Int]): Array[Int] = {
    		var arr: Array[Int] = ar.clone
    		val t = arr(a); arr(a) = arr(b); arr(b) = t;
    		arr
    	}
    	def dfs(sol: Array[Int], s: Int): Int = s match {
    		case 0 => (0 /: (for(i <- 0 to (k - 1)) yield sol(i)))(_+_)
    		case _ => {	var mini = 0xffffff
    			(for(
    				i <- 0 to (n - 2)
    				if(sol(i) > sol(i+1))
    			) yield dfs(swap(i, i+1, sol), s-1)) map (x => {
    				if(x <= mini) mini = x
    			})
    			mini
    		}
    	}
    	println(dfs(get(Array(), n), s))
	}
}