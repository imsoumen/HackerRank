/*
Kundu and Tree
----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/kundu-and-tree
----------------------------------

Kundu is true tree lover. Tree is a connected graph having N vertices and N-1 edges. Today when he got a tree, he colored each edge with one of either red(r) or black(b) color. He is interested in knowing how many triplets(a,b,c) of vertices are there , such that, there is atleast one edge having red color on all the three paths i.e. from vertex a to b, vertex b to c and vertex c to a . Note that (a,b,c), (b,a,c) and all such permutations will be considered as the same triplet.

If the answer is greater than 10^9 + 7, print the answer modulo (%) 10^9 + 7.

Input Format
The first line contains an integer N, i.e., the number of vertices in tree.
The next N-1 lines represent edges: 2 space separated integers denoting an edge followed by a color of the edge. A color of an edge is denoted by a small letter of English alphabet, and it can be either red(r) or black(b).

Output Format
Print a single number i.e. the number of triplets.

Constraints
1 ≤ N ≤ 105
A node is numbered between 1 to N.

Sample Input

5
1 2 b
2 3 r
3 4 r
4 5 b

Sample Output
4

Explanation

Given tree is something like this.

		1 -----(b)----- 2 -----(r)----- 3 -----(r)----- 4 -----(b)----- 5

(2,3,4) is one such triplet because on all paths i.e 2 to 3, 3 to 4 and 2 to 4 there is atleast one edge having red color.
(2,3,5), (1,3,4) and (1,3,5) are other such triplets.
Note that (1,2,3) is NOT a triplet, because the path from 1 to 2 does not have an edge with red color.
*/

(Scala Solution)

import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._

object Solution {
    
    def find(p:Int, id:Array[Int]):Int = {
        var x = p
        while (x != id(x)) x = id(x)
        x
    }

    def group(p:Int, q:Int, id:Array[Int], value:Array[Int]):Unit = {
        val pRoot = find(p, id)
        val qRoot = find(q, id)
        if (pRoot != qRoot) {
            id(qRoot) = pRoot
            value(pRoot) += value(qRoot)
        }
    }

    def members(p:Int, id:Array[Int], value:Array[Int]):Int = {
        value(find(p, id))
    }

    def choose3(x:BigInt):BigInt = {
        if (x < 3) 0 else (x * (x - 1) * (x - 2) / 6) % 1000000007
    }

    def choose2(x:BigInt, max:BigInt):BigInt = {
        if (x < 2) 0 else (x * (x - 1) * (max - x) / 2) % 1000000007
    }
    
    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
        val stdin = scala.io.StdIn
        
        val N = stdin.readInt()
        val id = new Array[Int](N+1)
        for (i <- id.indices) id(i) = i
        val values = Array.fill(N+1)(1)

        for (i <- 1 until N) {
          val t = stdin.readLine().split(" ")
          if (t(2) == "b") group(t(0).toInt, t(1).toInt, id, values)
        }

        val t = values.indices.filter(x => x == id(x)).map(values(_))
        var result = (choose3(N) % 1000000007) -  (t.map(choose2(_, N)).sum% 1000000007) -
          (t.map(choose3(_)).sum % 1000000007)
        while (result < 0) result += 1000000007
        println(result % 1000000007)
    }
}