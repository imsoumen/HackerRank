"""
Array Manipulation
-----------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/crush
-----------------------------

Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each the array element between two given indices, inclusive. Once all operations have been performed, return the maximum value in the array.

Function Description:

Complete the function arrayManipulation in the editor below.

arrayManipulation has the following parameters:

int n - the number of elements in the array
int queries[q][3] - a two dimensional array of queries where each queries[i] contains three integers, a, b, and k.
Returns

int - the maximum value in the resultant array
Input Format

The first line contains two space-separated integers n and m, the size of the array and the number of operations.
Each of the next m lines contains three space-separated integers a, b and k, the left index, right index and summand.

Sample Input

5 3
1 2 100
2 5 100
3 4 100
Sample Output

200
Explanation

After the first update the list is 100 100 0 0 0.
After the second update list is 100 200 100 100 100.
After the third update list is 100 200 200 200 100.

The maximum value is 200.

Solution:
"""
(Python Solution)

#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the arrayManipulation function below.
def arrayManipulation(n, queries):
    arr = [0]*n
    for i in queries:
        arr[i[0] - 1] += i[2]
        if i[1] != len(arr):
            arr[i[1]] -= i[2]
    maxval = 0
    itt = 0
    print(arr)
    for q in arr:
        itt += q
        if itt > maxval:
            maxval = itt
    return maxval

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nm = input().split()

    n = int(nm[0])

    m = int(nm[1])

    queries = []

    for _ in range(m):
        queries.append(list(map(int, input().rstrip().split())))

    result = arrayManipulation(n, queries)

    fptr.write(str(result) + '\n')

    fptr.close()

-----------------------------------------------------------------------------------

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

    // Complete the arrayManipulation function below.
    def arrayManipulation(n: Int, queries: Array[Array[Int]]): Long = {
        
        val resultArr = Array.fill[Long](n)(0)
    
        queries.foreach{query => 
            val startIndex = query(0)
            val endIndex   = query(1)
            val summand    = query(2)
            resultArr(startIndex-1) += summand
            if(endIndex < n)
                resultArr(endIndex)  -= summand
        }
        var temp:Long = 0
        var maxval:Long  = 0
        resultArr.foreach{result =>
            temp += result
            if(temp > maxval)
                maxval = temp
        }
        return maxval

    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val nm = stdin.readLine.split(" ")

        val n = nm(0).trim.toInt

        val m = nm(1).trim.toInt

        val queries = Array.ofDim[Int](m, 3)

        for (i <- 0 until m) {
            queries(i) = stdin.readLine.split(" ").map(_.trim.toInt)
        }

        val result = arrayManipulation(n, queries)

        printWriter.println(result)

        printWriter.close()
    }
}
