"""
Extra Long Factorials
-----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/extra-long-factorials
-----------------------------------

The factorial of the integer n, written n!, is defined as:

		n! = n x (n-1) x (n-2) x ....... x 3 x 2 x 1

Calculate and print the factorial of a given integer.

For example, if n=30, we calculate 30x29x.....x3x2x1 and get the result.

Function Description

Complete the extraLongFactorials function in the editor below. It should print the result and return.

extraLongFactorials has the following parameter(s):

n: an integer
Note: Factorials of n > 20 can't be stored even in a 64-bit long long variable. Big integers must be used for such calculations. Languages like Java, Python, Ruby etc. can handle big integers, but we need to write additional code in C/C++ to handle huge values.

We recommend solving this challenge using BigIntegers.

Input Format

Input consists of a single integer n

Constraints
1 <= n <= 100

Output Format

Print the factorial of n.

Sample Input
25

Sample Output
1551121004330985984000000

Explanation
25!=25x24x23x22x21.........3x2x1

"""

(Python Solution)

#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the extraLongFactorials function below.
def extraLongFactorials(n):
    fact=1
    for i in range(1,n+1):
        fact=fact*i
    print(fact)
if __name__ == '__main__':
    n = int(input())

    extraLongFactorials(n)


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

    // Complete the extraLongFactorials function below.
    def extraLongFactorials(n: Int) {
        #println((n to 1 by -1).foldLeft(BigInt(1))((acc, i) => acc * i))
        var fact = 1
        for ( i <- Range(1,n+1)){
            fact=fact*i
        }
        println(fact)     

    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val n = stdin.readLine.trim.toInt

        extraLongFactorials(n)
    }
}
