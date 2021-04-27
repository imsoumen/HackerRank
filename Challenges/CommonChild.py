"""
Common Child
----------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/common-child
----------------------------

A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the other string. Letters cannot be rearranged. Given two strings of equal length, what's the longest string that can be constructed such that it is a child of both?

Example
s1 = ABCD
s2 = ABDC


These strings have two children with maximum length 3, ABC and ABD. They can be formed by eliminating either the D or C from both strings. Return 3.

Function Description:

Complete the commonChild function in the editor below.

commonChild has the following parameter(s):

string s1: a string
string s2: another string
Returns

int: the length of the longest string which is a common child of the input strings

Input Format:
There are two lines, each with a string, s1 and s2.

Sample Input:

HARRY
SALLY

Sample Output
2

Explanation

The longest string that can be formed by deleting zero or more characters from HARRY and SALLY is AY, whose length is 2.

Sample Input 1

AA
BB
Sample Output 1
0

Explanation 1

AA and AB have no characters in common and hence the output is 0.

Sample Input 2
SHINCHAN
NOHARAAA

Sample Output 2
3

Explanation 2
The longest string that can be formed between SHINCHAN and NOHARAAA while maintaining the order is NHA.

Sample Input 3

ABCDEF
FBDAMN

Sample Output 3
2

Explanation 3
BD is the longest child of the given strings.
"""
(Python Solution)

#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the commonChild function below.
def commonChild(s1, s2):
	m = len(s1)
	n = len(s2)
	#c = [[0]*n+1]*m+1
    c = [[0 for i in range(m+1)] for j in range(n+1)]
    for i in range(1,m+1):
        for j in range(1,n+1):
            if s1[i-1] == s2[j - 1]:
                c[i][j] = c[i - 1][j - 1] + 1
            else:
                c[i][j] = max(c[i][j - 1],c[i - 1][j])
    
    return c[m][n]

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s1 = input()

    s2 = input()

    result = commonChild(s1, s2)

    fptr.write(str(result) + '\n')

    fptr.close()


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

    // Complete the commonChild function below.
    def commonChild(s1: String, s2: String): Int = {
        val m = s1.length
        val n = s2.length
        val c = Array.ofDim[Int](m + 1, n + 1)
        for (i <- 1 to m; j <- 1 to n) {
          if (s1(i - 1) == s2(j - 1)) c(i)(j) = c(i - 1)(j - 1) + 1
          else c(i)(j) = c(i)(j - 1) max c(i - 1)(j)
        }
        return c(m)(n)

    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val s1 = stdin.readLine

        val s2 = stdin.readLine

        val result = commonChild(s1, s2)

        printWriter.println(result)

        printWriter.close()
    }
}
