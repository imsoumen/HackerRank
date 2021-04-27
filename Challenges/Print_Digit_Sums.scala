/*

Print Digit Sums
-----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/prime-digit-sums
-----------------------------------

Chloe is fascinated by prime numbers. She came across the number 283002 on a sign and, though the number is not prime, found some primes hiding in it by using the following rules:

Every three consecutive digits sum to a prime:
				283002   283002   283002   283002
				---		  --- 		---		  ---
Every four consecutive digits sum to a prime:
				283002   283002   283002
				----	  ---- 		----
Every five consecutive digits sum to a prime:
				283002   283002
				-----	  ----- 	
You must answer q queries, where each query consists of an integer, n. For each n, find and print the number of positive n-digit numbers, modulo 10^9+7, that satisfy all three of Chloe's rules (i.e., every three, four, and five consecutive digits sum to a prime).

Input Format

The first line contains an integer, q, denoting the number of queries.
Each of the  subsequent lines contains an integer denoting the value of n for a query.

Output Format

For each query, print the number of n-digit numbers satisfying Chloe's rules, modulo 10^9+7, on a new line.

Sample Input 0

1
6

Sample Output 0

95

Explanation 0

There are 95 six-digit numbers satisfying the property above, where the respective first and last ones are 101101 and 902005.
*/

(Scala Solution)

import scala.io._

object Solution {

  var lineit:Iterator[String] = Source.stdin.getLines.filterNot(_.isEmpty).flatMap(i => i.split(" "))
  def rs() = lineit.next
  def ri() = rs.toInt
  val primes = Set (2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47);
  var res3 = 0
  var res4 = 0
  var res5 = 0
  def good () = {
    var l = List.empty[Int];
    for (a <- 0 to 9) {
      for (b <- 0 to 9) {
        for (c <- 0 to 9) {
          if (primes.contains (a + b + c)) {
            if (a > 0) res3 += 1
            for (d <- 0 to 9) {
              if (primes.contains (a + b + c + d) && primes.contains (b + c + d)) {
                if (a > 0) res4 += 1
                for (e <- 0 to 9) {
                  if (primes.contains (a + b + c + d + e) && primes.contains (b + c + d + e) && primes.contains (c + d + e)) {
                    if (a > 0) res5 += 1
                    l = ((((a * 10 + b) * 10 + c) * 10 + d) * 10 + e) :: l;
                  }
                }
              }
            }
          }
        }
      }
    }
    l.reverse
  }
  def main(args: Array[String]) {
    val q = 1000000007;
    val g = good;
    val a = g.toArray
    val m = a.length
    val h = collection.mutable.HashMap.empty[Int, Int]
    for (i <- 0 until m) {
      h.update (a(i), i)
    }
    val edges = Array.ofDim[List[(Int,Int)]] (m)
    for (i <- 0 until m) {
      val j = a(i)
      edges(i) = (0 until 10).map (k => (k, h.get ((j / 10) + k * 10000))).collect {
        case (j, Some (x)) => (j, x)
      }.toList
    }
    val nt = ri
    var t = Array.tabulate (nt) (i => ri)
    val n = 5.max (t.max)
    val res = Array.ofDim[Long] (n + 1)
    var c = Array.fill (m)(1L);
    for (i <- 6 to n) {
      val d = Array.fill (m)(0L);
      for (k <- 0 until m) {
        val ck = c(k)
        if (ck > 0) {
          for ( (u, v) <- edges (k)) {
            d(v) += ck
            if (u > 0) {
              res (i) += ck
            }
          }
        }
      }
      c = d.map (l => if (l > 0) l % q else 0)
      res (i) %= q
    }
    //Console.err.println (c.toList)
    res(1) = 9
    res(2) = 90
    res(3) = res3
    res(4) = res4
    res(5) = res5
    println (t.map (i => res(i).toString).mkString ("\n"))
  }
}