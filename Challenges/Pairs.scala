/*
Pairs
------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/pairs
------------------------------------------

Given an array of integers and a target value, determine the number of pairs of array elements that have a difference equal to the target value.

Example
k = 1
arr = [1,2,3,4]

There are three values that differ by k =1: 2-1=1, 3-2=1, and 4-3=1. Return 3.

Function Description

Complete the pairs function below.

pairs has the following parameter(s):

int k: an integer, the target difference
int arr[n]: an array of integers
Returns

int: the number of pairs that satisfy the criterion
Input Format

The first line contains two space-separated integers n and k, the size of arr and the target value.
The second line contains n space-separated integers of the array arr.

Sample Input

STDIN       Function
-----       --------
5 2         arr[] size n = 5, k =2
1 5 3 4 2   arr = [1, 5, 3, 4, 2]

Sample Output

3

Explanation

There are 3 pairs of integers in the set with a difference of 2: [5,3], [4,2] and [3,1]. .


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
import collection.mutable._

object Solution {

    // Complete the pairs function below.
     def pairs(k: Int, arr: Array[Int]): Int = {
        
        var set = arr.toSet;
        
        var count = 0
        for (i <- set){
            if ( set.contains(i+k))
                count += 1
        }
        return count
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val nk = stdin.readLine.split(" ")

        val n = nk(0).trim.toInt

        val k = nk(1).trim.toInt

        val arr = stdin.readLine.split(" ").map(_.trim.toInt)
        val result = pairs(k, arr)

        printWriter.println(result)

        printWriter.close()
    }
}
