/*
Cut Tree
--------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/cuttree
--------------------------------

Given a tree T with n nodes, how many subtrees (T') of T have at most K edges connected to (T - T')?

Input Format

The first line contains two integers n and K followed by n-1 lines each containing two integers a & b denoting that there's an edge between a & b.

Constraints

1 <= K <= n <= 50
Every node is indicated by a distinct number from 1 to n.

Output Format

A single integer which denotes the number of possible subtrees.

Sample Input

3 1
2 1
2 3

Sample Output

6

Explanation

There are 2^3 possible sub-trees:

{} {1} {2} {3} {1, 2} {1, 3} {2, 3} {1, 2, 3}

But:
the sub-trees {2} and {1,3} are not valid. {2} isn't valid because it has 2 edges connecting to it's complement {1,3} whereas K = 1 in the sample test-case {1,3} isn't valid because, well, it's not a sub-tree. The nodes aren't connected.

*/

(Scala Solution)

import scala.collection.mutable._;

object Solution {
    var K = 0;
    var N = 0;
    var F: Array[Array[Long]] = null;
    var G: Array[HashSet[Int]] = null;
    
    def calculate(u:Int) {
        val child = G(u).toArray;
        val L = child.length;
        val dp = Array.ofDim[Long](L + 1, K + 1);
        dp(0)(0) = 1;
        
        for (i <- 1 to L) {
            val v = child(i - 1);
            for (k <- 0 to K) {
                if (k > 0) dp(i)(k) = dp(i - 1)(k - 1);
                
                for (l <- 0 to k) {
                    dp(i)(k) += dp(i - 1)(k - l) * F(v)(l);
                }
            }
        }
        for (k <- 0 to K) F(u)(k) = dp(L)(k);
    }
    
    def dfs(p:Int, u: Int) {
        G(u).remove(p);
        for (v <- G(u)) {
            dfs(u, v);
        }
        calculate(u);
    }
    
    def main(args: Array[String]) {
        val in = new java.util.Scanner(System.in);
        N = in.nextInt;
        K = in.nextInt;
        
        F = Array.ofDim[Long](N, K + 1);
        G = new Array[HashSet[Int]](N);
        
        for (i <- 0 until N) G(i) = new HashSet[Int]();
        for (i <- 1 until N) {
            val a = in.nextInt - 1;
            val b = in.nextInt - 1;
            G(a).add(b);
            G(b).add(a);
        }
        
        dfs(-1, 0);
        
        var res:Long = 0;
        for (i <- 0 to K) res += F(0)(i);
        for (i <- 1 until N; j <- 0 until K) res += F(i)(j);
        
        println(res + 1);
    }
}