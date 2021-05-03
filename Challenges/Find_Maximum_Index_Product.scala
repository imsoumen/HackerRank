/*

Find Maximum Index Product
------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/find-maximum-index-product
------------------------------------------

You are given a list of N numbers a1,a2,a3,...,an. For each element at position  i(1<-i<=N), we define Left(i) and Right(i) as:
Left(i) = closest index j such that j < i and . If no such j exists then Left(i) = 0.
Right(i) = closest index k such that k > i and . If no such k exists then Right(i) = 0.

We define IndexProduct(i) = Left(i) * Right(i). You need to find out the maximum IndexProduct(i) among all i.

Input Format

The first line contains an integer N, the number of integers. The next line contains the N integers describing the list a[1..N].

Output Format

Output the maximum IndexProduct among all indices from 1 to N.

Sample Input

5
5 4 3 4 5

Sample Output

8

Explanation

We can compute the following:

IndexProduct(1) = 0
IndexProduct(2) = 1x5=5
IndexProduct(3) = 2x4=8
IndexProduct(4) = 1x5=5
IndexProduct(5) = 0

The largest of these is 8, so it is the answer.

*/

(Scala Solution)
import scala.io.StdIn._

object Solution {

  def maxIndexProduct(nums: Array[Int]): Long = {

    def buildLeftIndex(nums: Array[Int]): Array[Int] = {
      val size = nums.size
      val result = Array.fill(size)(-1) // to deal with 1-based index
      for (i <- 1 until size) {
        var lIndex = i - 1
        while (lIndex >= 0 && nums(lIndex) <= nums(i)) {
          lIndex = result(lIndex)
        }
        result(i) = lIndex
      }
      result.map(_ + 1)
    }

    val left = buildLeftIndex(nums)
    val right = buildLeftIndex(nums.reverse).map({ x =>
      if (x == 0) 0 else nums.size - x + 1
    }).reverse

    left.zip(right).map(x => 1L * x._1 * x._2).max
  }

  def main(args: Array[String]) {
    val _ = readLine
    val nums = readLine.split(" ").map(_.toInt)
    println(maxIndexProduct(nums))
  }
}