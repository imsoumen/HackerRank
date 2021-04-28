/*
Queries with Fixed Length
------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/queries-with-fixed-length
------------------------------------------

Consider an n-integer sequence, A = {a0,a1,a2,....,an-1}. We perform a query on A by using an integer, d, to calculate the result of the following expression:
		min ( max a<j> )
		
In other words, if we let m<i> = max(ai,ai+1,ai+2,..,ai+d-1), then you need to calculate min(m0,m1,...,mn-d).

Given arr and q queries, return a list of answers to each query.

Example
arr = [2,3,4,5,6]
queries = [2,3]

The first query uses all of the subarrays of length 2: [2,3],[3,4],[4,5],[5,6]. The maxima of the subarrays are [3,4,5,6]. The minimum of these is 3.

The second query uses all of the subarrays of length 3: [2,3,4],[3,4,5],[4,5,6]. The maxima of the subarrays are [4,5,6]. The minimum of these is 4.

Return [3,4].

Function Description

Complete the solve function below.

solve has the following parameter(s):

int arr[n]: an array of integers
int queries[q]: the lengths of subarrays to query

Returns

int[q]: the answers to each query

Input Format

The first line consists of two space-separated integers, n and q.
The second line consists of n space-separated integers, the elements of arr.
Each of the q subsequent lines contains a single integer denoting the value of d for that query.

Sample Input 0

5 5
33 11 44 11 55
1
2
3
4
5

Sample Output 0

11
33
44
44
55

Explanation 0

For d=1, the answer is min(max(a0),max(a1),max(a2),max(a3),max(a4))=11.
For d=2, the answer is min(max(a0,a1),max(a1,a2),max(a2,a3),max(a3,a4))=33.
For d=3, the answer is min(max(a0,a1,a2),max(a1,a2,a3),max(a2,a3,a4))=44.
For d=4, the answer is min(max(a0,a1,a2,a3),max(a1,a2,a3,a4))=44.
For d=5, the answer is min(max(a0,a1,a2,a3,a4))=55.

Sample Input 1

5 5
1 2 3 4 5
1
2
3
4
5

Sample Output 1

1
2
3
4
5

Explanation 1

For each query, the "prefix" has the least maximum value among the consecutive subsequences of the same size.




*/

(Scala Solution)

import scala.collection.mutable
import scala.io.StdIn._

object Solution {

    def main(args: Array[String]) {
        

        def solution(d:Int, input:Array[Int]):Int = {
          val q = mutable.Queue[Int]()
          input.take(d).foreach(q.enqueue(_))
          var result = List(q.max)

          for (i <- d until input.length) {
            val t = q.dequeue()
            q.enqueue(input(i))
            if (input(i) >= result.head) {
              result = input(i)::result
            } else if (t < result.head) {
              result = result.head::result
            } else {
              result = q.max::result
            }
          }
          result.min
        }

        val Q = readLine().split(" ").last.toInt
        val input = readLine().split(" ").map(_.toInt)
        for (i <- 1 to Q) println(solution(readInt(), input))
    }
}