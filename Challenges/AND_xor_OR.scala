/*
AND xor OR
---------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/and-xor-or
---------------------------

Given an array A[] of N distinct elements. Let M1 and M2 be the smallest and the next smallest element in the interval [L,R] where 1 <= L <= R <= N.

Si = ((( M1 AND M2 ) XOR ( M1 OR M2 ) AND ( M1 XOR M2 )).

Your task is to find the maximum possible value of Si.

Input Format

First line contains integer N.
Second line contains N integers, representing elements of the array A[].

Output Format

Print the value of maximum possible value of Si.

Sample Input

5
9 6 3 5 2

Sample Output

15

Explanation

Consider the interval [1,2] the result will be maximum.

((( 9 AND 6 ) XOR ( 9 OR 6 ) AND ( 9 XOR 6 )) = 15

*/

(Scala Solution)

import java.io.PrintWriter

object Solution {
    
    def calc(a: Int, b: Int) = {
    (((a & b) ^ (a | b)) & (a ^ b))
  }
    /*
     * Complete the andXorOr function below.
     */
    def andXorOr(a: Array[Int]): Int = {
        /*
         * Write your code here.
         */
        val stack = scala.collection.mutable.Stack[Int]()
        if (a.nonEmpty) {
          var max = Int.MinValue
          a.map { x =>
            if (stack.isEmpty) {
              stack.push(x)
            } else if (x > stack(0)) {
              stack.push(x)
            } else {
              while(stack.nonEmpty && stack(0) > x) {
                val t = stack.pop()
                //println(s" X is ${x} , t is ${t} , ${calc(x,t)} ${max}")
                max = Math.max(calc(x,t), max)
                if (stack.nonEmpty) {
                  //println(s" X is ${t} , t is ${stack(0)},  ${calc(stack(0),t)} ${max}")
                  max = Math.max(calc(t, stack(0)), max)
                }
              }

              stack.push(x)
            }
          }

          while (stack.nonEmpty) {
            val t = stack.pop()
            if (stack.nonEmpty) {
              //println(s" X is ${t} , t is ${stack(0)} ${calc(stack(0),t)}")
              max = Math.max(calc(stack(0), t), max)
            }
          }
          return max
        } else {
          return 0
        }
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val aCount = stdin.readLine.trim.toInt

        val a = stdin.readLine.split(" ").map(_.trim.toInt)
        val result = andXorOr(a)

        printWriter.println(result)

        printWriter.close()
    }
}
