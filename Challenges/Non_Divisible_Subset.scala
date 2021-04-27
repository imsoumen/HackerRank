/*
Non Divisible Subset
-----------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/non-divisible-subset
-----------------------------------------

Given a set of distinct integers, print the size of a maximal subset of S where the sum of any 2 numbers in S' is not evenly divisible by k.

Example
S=[19,10,12,10,24,25,22] k=4

One of the arrays that can be created is S'[0]=[10,12,25]. Another is S'[1]=[19,22,24]. After testing all permutations, the maximum length solution array has 3 elements.

Function Description

Complete the nonDivisibleSubset function in the editor below.

nonDivisibleSubset has the following parameter(s):

int S[n]: an array of integers
int k: the divisor

Returns

int: the length of the longest subset of S meeting the criteria

Input Format

The first line contains 2 space-separated integers, n and k, the number of values in S and the non factor.
The second line contains n space-separated integers, each an S[i], the unique values of the set.

Sample Input

STDIN    Function
-----    --------
4 3      S[] size n = 4, k = 3
1 7 2 4  S = [1, 7, 2, 4]

Sample Output

3

Explanation

The sums of all permutations of two elements from S={1,7,2,4} are:

1 + 7 = 8
1 + 2 = 3
1 + 4 = 5
7 + 2 = 9
7 + 4 = 11
2 + 4 = 6

Only S'={1,7,4} will not ever sum to a multiple of k=3.

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
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

    def nonDivisibleSubset(k: Int, s: Array[Int]): Int = {
    // Write your code here
        var result:Int = 0;
        if (s(0) > 0) result += 1;
        for (i <- 1 to (k)/2) {
            if (i == k-i && s(k-i) > 0) 
                result += 1;
            else
                result += Math.max(s(i), s(k-i));
        }
        return result;
    }

}

object Solution {
    def main(args: Array[String]) {
		val sc = new java.util.Scanner (System.in);

        val n = sc.nextInt();

        val k = sc.nextInt();
		
		var temp:Int = 0;
        var s = new Array[Int](k);

        for (s_i <- 0 to n-1) {
            temp = sc.nextInt();
            s(temp%k) += 1;
        }

        val result = Result.nonDivisibleSubset(k, s)

        println(result)

    }
}


