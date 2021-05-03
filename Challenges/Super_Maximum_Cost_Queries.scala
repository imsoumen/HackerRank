/*
Super Maximum Cost Queries
--------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/maximum-cost-queries
--------------------------------------

Victoria has a tree, T, consisting of N nodes numbered from 1 to N. Each edge from node Ui to Vi in tree T has an integer weight, Wi.

Let's define the cost, C, of a path from some node X to some other node Y as the maximum weight (W) for any edge in the unique path from node X to node Y.

Victoria wants your help processing Q queries on tree T, where each query contains L integers, R and , such that L<=R. For each query, she wants to print the number of different paths in T that have a cost, C, in the inclusive range [L,R].

It should be noted that path from some node X to some other node Y is considered same as path from node Y to X i.e {X,Y} is same as {Y,X}.

Input Format

The first line contains 2 space-separated integers, N (the number of nodes) and Q (the number of queries), respectively.
Each of the N-1 subsequent lines contain 3 space-separated integers, U, V, and W, respectively, describing a bidirectional road between nodes U and V which has weight W.
The Q subsequent lines each contain 2 space-separated integers denoting L and R.

Output Format

For each of the Q queries, print the number of paths in T having cost C in the inclusive range [L,R] on a new line.

Sample Input

5 5
1 2 3
1 4 2
2 5 6
3 4 1
1 1
1 2
2 3
2 5
1 6

Sample Output

1
3
5
5
10

Explanation

Q1: {3,4}
Q2: {1,3},{3,4},{1,4}
Q3: {1,4},{1,2},{2,4},{1,3},{2,3}
Q4: {1,4},{1,2},{2,4},{1,3},{2,3}
...etc.

*/


(Scala Solution)

object Solution {
    
        def choose2(x:Long):Long = {
          if (x < 2) 0 else x * (x - 1) / 2
        }

        def find(p:Int, id:Array[Int]):Int = {
          var x = p
          while (x != id(x)) x = id(x)
          x
        }

        def group(p:Int, q:Int, id:Array[Int], value:Array[Int]):Long = {
          val pRoot = find(p, id)
          val qRoot = find(q, id)
          if (pRoot != qRoot) {
            val m1 = value(pRoot)
            val m2 = value(qRoot)
            val m3 = m1 + m2
            id(qRoot) = pRoot
            value(pRoot) = m3
            choose2(m3) - choose2(m1) - choose2(m2)
          } else 0
        }

        def numLines(x:Int, high:Int, members:Array[(Long, Int)]):Long = {
          if (x < members.head._2) return 0
          if (x >= members.last._2) return members.last._1

          var l = 0
          var h = high
          while (h != l + 1) {
            val m = (l + h) / 2
            if (x < members(m)._2) h = m else l = m
          }
          members(l)._1
        }

    def main(args: Array[String]) {

        val t1 = readLine().split(" ").map(_.toInt)
        val N = t1(0)
        val Q = t1(1)
        val input = (for (i <- 1 until N) yield readLine().split(" ").map(_.toInt)).
          sortWith((x, y) => x(2) < y(2))
        val query = for (i <- 1 to Q) yield readLine().split(" ").map(_.toInt)


        val id = new Array[Int](N+1)
        for (i <- id.indices) id(i) = i
        val values = Array.fill(N+1)(1)


        val change = input.map(x => group(x(0), x(1), id, values))
        val weight = input.map(x => x(2))
        val index = (for (i <- 0 until weight.length-1 if weight(i) < weight(i+1)) yield i):+(weight.length-1)
        val t2 = change.scanLeft(0L)(_+_).tail.zip(weight)
        val members = index.map(t2(_)).toArray
        val high = members.length-1

        for (q <- query) {
          val x1 = numLines(q(1), high, members)
          val x2 = numLines(q(0)-1, high, members)
          println(x1 - x2)
        }
    }
}