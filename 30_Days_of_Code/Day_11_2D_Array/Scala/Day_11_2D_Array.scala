/*
2D Array - DS
---------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/2d-array
---------------------------------

Given a 6X6 2D Array, arr:

1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0

An hourglass in A is a subset of values with indices falling in this pattern in arr's graphical representation:

a b c
  d
e f g

There are 16 hourglasses in arr. An hourglass sum is the sum of an hourglass' values. Calculate the hourglass sum for every hourglass in arr, then print the maximum hourglass sum. The array will always be 6X6.

Example

arr=
-9 -9 -9  1 1 1 
 0 -9  0  4 3 2
-9 -9 -9  1 2 3
 0  0  8  6 6 0
 0  0  0 -2 0 0
 0  0  1  2 4 0
 
The 16 hourglass sums are:

-63, -34, -9, 12, 
-10,   0, 28, 23, 
-27, -11, -2, 10, 
  9,  17, 25, 18
  
The highest hourglass sum is 28 from the hourglass beginning at row 1, column 2:

0 4 3
  1
8 6 6

Note: If you have already solved the Java domain's Java 2D Array challenge, you may wish to skip this challenge.

Function Description

Complete the function hourglassSum in the editor below.

hourglassSum has the following parameter(s):

int arr[6][6]: an array of integers

Returns

int: the maximum hourglass sum

Input Format

Each of the 6 lines of inputs arr[i] contains 6 space-separated integers arr[i][j].

Output Format

Print the largest (maximum) hourglass sum found in arr.

Sample Input

1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 2 4 4 0
0 0 0 2 0 0
0 0 1 2 4 0

Sample Output

19

Explanation

arr contains the following hourglasses:

1 1 1  1 1 0  1 0 0  0 0 0
  1      0      0      0
1 1 1  1 1 0  1 0 0  0 0 0

0 1 0  1 0 0  0 0 0  0 0 0
  1      1      0      0
0 0 2  0 2 4  2 4 4  4 4 0

1 1 1  1 1 0  1 0 0  0 0 0
  0      2      4      4
0 0 0  0 0 2  0 2 0  2 0 0

0 0 2  0 2 4  2 4 4  4 4 0
  0      0      2      0 
0 0 1  0 1 2  1 2 4  2 4 0

The hourglass with the maximum sum (19) is:

2 4 4
  2
1 2 4
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

    // Complete the hourglassSum function below.
    def hourglassSum(arr: Array[Array[Int]]): Int = {
        
        var max = -63
        
        for(i <- 1 to 4) {
            for (j <- 1 to 4) {
               val sum = arr(i)(j) + arr(i - 1)(j) + arr(i + 1)(j)  + arr(i - 1)(j - 1) + arr(i - 1)(j + 1) + arr(i + 1)(j - 1) + arr(i + 1)(j + 1)
                if(sum > max) {
                   max = sum
               }
            }
        }
        return max

    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val arr = Array.ofDim[Int](6, 6)

        for (i <- 0 until 6) {
            arr(i) = stdin.readLine.split(" ").map(_.trim.toInt)
        }

        val result = hourglassSum(arr)

        printWriter.println(result)

        printWriter.close()
    }
}
