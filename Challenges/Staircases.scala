/*
Staircase
-----------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/staircase
-----------------------------

Staircase detail

This is a staircase of size n=4:

   #
  ##
 ###
####

Its base and height are both equal to n. It is drawn using # symbols and spaces. The last line is not preceded by any spaces.

Write a program that prints a staircase of size n.

Function Description

Complete the staircase function in the editor below.

staircase has the following parameter(s):

int n: an integer

Print

Print a staircase as described above.

Input Format

A single integer, n, denoting the size of the staircase.

Output Format

Print a staircase of size n using # symbols and spaces.

Note: The last line must have 0 spaces in it.

Sample Input

6 
Sample Output

     #
    ##
   ###
  ####
 #####
######

Explanation

The staircase is right-aligned, composed of # symbols and spaces, and has a height and width of 6.


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
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

object Result {

    /*
     * Complete the 'staircase' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

    def staircase(n: Int) {
    // Write your code here
        for (i <- 1 to n) {
          for (j <- 1 to n - i) print(" ")
          for (j <- 1 to i) print("#")
          print("\n")
        }
    }
}

object Solution {
    def main(args: Array[String]) {
        val n = StdIn.readLine.trim.toInt

        Result.staircase(n)
    }
}
