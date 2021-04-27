"""
Repeated String
-----------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/repeated-string
-----------------------------

There is a string, s, of lowercase English letters that is repeated infinitely many times. Given an integer, n, find and print the number of letter a's in the first n letters of the infinite string.

Example
s = 'abcac'
n = 10

The substring we consider is abcacabcac, the first 10 characters of the infinite string. There are 4 occurrences of a in the substring.

Function Description:

Complete the repeatedString function in the editor below.

repeatedString has the following parameter(s):

s: a string to repeat
n: the number of characters to consider
Returns

int: the frequency of a in the substring

Input Format

The first line contains a single string, .
The second line contains an integer, .

Sample Input 0

aba
10
Sample Output 0

7
Explanation 0
The first n=10 letters of the infinite string are abaabaabaa. Because there are 7 a's, we return 7.

"""
Solution:

(Python Solution)
#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the repeatedString function below.
def repeatedString(s, n):
    repeatCnt = int(n / len(s))
    extraCnt = int(n % len(s))
    NoOfLettersCnt = s.count("a")
    NoOfLettersRem = s[0:extraCnt].count("a")
    return (NoOfLettersCnt * repeatCnt) + NoOfLettersRem

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    n = int(input())

    result = repeatedString(s, n)

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

    // Complete the repeatedString function below.
    def repeatedString(s: String, n: Long): Long = {
        val repeatCnt = n / s.length
        val extraCnt = n % s.length
        val NoOfLettersCnt = s.count(_ == 'a')
        val NoOfLettersRem = s.slice(0, extraCnt.toInt).count(_ == 'a')
        return (NoOfLettersCnt * repeatCnt) + NoOfLettersRem

    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val s = stdin.readLine

        val n = stdin.readLine.trim.toLong

        val result = repeatedString(s, n)

        printWriter.println(result)

        printWriter.close()
    }
}
