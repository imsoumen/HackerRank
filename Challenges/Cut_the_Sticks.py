"""
Cut the Sticks
---------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/cut-the-sticks
---------------------------------

You are given a number of sticks of varying lengths. You will iteratively cut the sticks into smaller sticks, discarding the shortest pieces until there are none left. At each iteration you will determine the length of the shortest stick remaining, cut that length from each of the longer sticks and then discard all the pieces of that shortest length. When all the remaining sticks are the same length, they cannot be shortened so discard them.

Given the lengths of  sticks, print the number of sticks that are left before each iteration until there are none left.

Example:
arr=[1,2,3]

The shortest stick length is , so cut that length from the longer two and discard the pieces of length . Now the lengths are . Again, the shortest stick is of length , so cut that amount from the longer stick and discard those pieces. There is only one stick left, , so discard that stick. The number of sticks at each iteration are .

Function Description:

Complete the cutTheSticks function in the editor below. It should return an array of integers representing the number of sticks before each cut operation is performed.

cutTheSticks has the following parameter(s):

int arr[n]: the lengths of each stick

Returns:

int[]: the number of sticks after each iteration

Input Format:

The first line contains a single integer , the size of .
The next line contains  space-separated integers, each an , where each value represents the length of the  stick.

Sample Input 0

STDIN           Function
-----           --------
6               arr[] size n = 6
5 4 4 2 2 8     arr = [5, 4, 4, 2, 2, 8]

Sample Output 0

6
4
2
1

Explanation 0

sticks-length        length-of-cut   sticks-cut
5 4 4 2 2 8             2               6
3 2 2 _ _ 6             2               4
1 _ _ _ _ 4             1               2
_ _ _ _ _ 3             3               1
_ _ _ _ _ _           DONE            DONE

"""
(Python Solution)

#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the cutTheSticks function below.
def cutTheSticks(arr,n):                                                                         

    count=0
    ls=[]
    while(arr!=[]):
        mi=min(arr)
        count=len(arr)
        arr=list(map(lambda x:x-mi,arr))
        arr=[x for x in arr if x>0]
        ls.append(count)
    return ls

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')
 
    n = int(input().strip())
    arr = [int(arr_temp) for arr_temp in input().strip().split(' ')]

    result = cutTheSticks(arr,n)

    fptr.write('\n'.join(map(str, result)))
    fptr.write('\n')

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
import collections.

object Solution {

    // Complete the cutTheSticks function below.
    def cutTheSticks(arr: Array[Int]): Array[Int] = {
                
        var count = 0
        var resultArr: Array[Int] = Array()
        
        while ( arr.length >= 1 ){
            var minVal = arr.min()
            count = arr.length()
            var arr1 = arr.map(x => x-minVal)
            resultArr = resultArr :+ count
        }   
        
        return resultArr

    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val n = stdin.readLine.trim.toInt

        val arr = stdin.readLine.split(" ").map(_.trim.toInt)
        val result = cutTheSticks(arr)

        printWriter.println(result.mkString("\n"))

        printWriter.close()
    }
}
