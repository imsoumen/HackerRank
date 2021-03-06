"""
Lego Blocks
------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/lego-blocks
------------------------------

You have an infinite number of 4 types of lego blocks of sizes given as (depth x height x width):

d	h	w
1	1	1
1	1	2
1	1	3
1	1	4
Using these blocks, you want to make a wall of height  and width . Features of the wall are:

- The wall should not have any holes in it.
- The wall you build should be one solid structure, so there should not be a straight vertical break across all rows of bricks.
- The bricks must be laid horizontally.

How many ways can the wall be built?

Function Description

Complete the legoBlocks function in the editor below.

legoBlocks has the following parameter(s):

int n: the height of the wall
int m: the width of the wall
Returns
- int: the number of valid wall formations modulo (10^9 + 7)

Input Format

The first line contains the number of test cases t.

Each of the next t lines contains two space-separated integers n and m.
"""
(Python Solution)

#!/bin/python3

import os
import sys

#
# Complete the legoBlocks function below.
#
def legoBlocks(n, m):
    #
    # Write your code here.
    #
    modulo = 10 ** 9 + 7
    height = n % modulo
    width = m % modulo
    f = []
    f.append(0)
    f.append(1)
    if width > 1:
        f.append(2)
    if width > 2:
        f.append(4)
    if width > 3:
        f.append(8)
    if width > 4:
        for i in range(5, width + 1):
            f.append((f[i - 1] + f[i - 2] + f[i - 3] + f[i - 4]) % modulo)

    g = []
    for i in range(len(f)):
        g.append(f[i] ** height % modulo)

    h = [0] * (width + 1)
    h[1] = 1

    for i in range(2, width + 1):
        h[i] = g[i]
        for j in range(1, i):
            h[i] = (h[i] - h[j] * g[i-j]) % modulo
            a=1

    return h[-1] % modulo
    
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    t = int(input())

    for t_itr in range(t):
        nm = input().split()

        n = int(nm[0])

        m = int(nm[1])

        result = legoBlocks(n, m)

        fptr.write(str(result) + '\n')

    fptr.close()

(Scala Solution)

import java.io.PrintWriter

object Solution {

    private def MOD: Long = 1000000007
    
    /*
     * Complete the legoBlocks function below.
     */
    def legoBlocks(n: Int, m: Int): Long = {
        /*
         * Write your code here.
         */

        val f = new Array[Long](m+1)
        f(0) = 1
        for (i <- 1 to m) {
            f(i) = f(i-1)
            if (i>=2) f(i) = (f(i) + f(i-2)) % MOD
            if (i>=3) f(i) = (f(i) + f(i-3)) % MOD
            if (i>=4) f(i) = (f(i) + f(i-4)) % MOD
        }
        
        val all = new Array[Long](m+1)
        all(0) = 1
        for (i <- 1 to m) {
            all(i) = pow(f(i), n)
        }

        val valid = new Array[Long](m+1)
        val invalid = new Array[Long](m+1)
        valid(0) = 1
        for (i <- 1 to m) {
            for (j <- 1 until i) {
                invalid(i) = (invalid(i) + valid(j) * all(i-j) % MOD) % MOD
            }
            valid(i) = (all(i) - invalid(i) + MOD) % MOD
        }
        
        valid(m)
    }
    
    private def pow(k: Long, n: Int): Long = {
        if (n == 0) return 1
        if (n == 1) return k % MOD
        val half = pow(k, n/2)
        var result = half * half % MOD
        if (n % 2 == 1) result = result * k % MOD
        result
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val t = stdin.readLine.trim.toInt

        for (tItr <- 1 to t) {
            val nm = stdin.readLine.split(" ")

            val n = nm(0).trim.toInt

            val m = nm(1).trim.toInt

            val result = legoBlocks(n, m)

            printWriter.println(result)
        }

        printWriter.close()
    }
}