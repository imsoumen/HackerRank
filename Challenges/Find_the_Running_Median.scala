/*
Find the Running Median
------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/find-the-running-median
------------------------------

The median of a set of integers is the midpoint value of the data set for which an equal number of integers are less than and greater than the value. To find the median, you must first sort your set of integers in non-decreasing order, then:

If your set contains an odd number of elements, the median is the middle element of the sorted sample. In the sorted set {1,2,3}, 2 is the median.
If your set contains an even number of elements, the median is the average of the two middle elements of the sorted sample. In the sorted set {1,2,3,4}, (2+3)/2=2.5 is the median.
Given an input stream of n integers, perform the following task for each ith integer:

Add the ith integer to a running list of integers.
Find the median of the updated list (i.e., for the first element through the ith element).
Print the updated median on a new line. The printed value must be a double-precision number scaled to 1 decimal place (i.e., 12.3 format).

Example
a=[7,3,5,2]

Sorted          Median
[7]             7.0
[3, 7]          5.0
[3, 5, 7]       5.0
[2, 3, 5, 7]    4.0

Each of the median values is stored in an array and the array is returned for the main function to print.

Note: Add formatting to the print statement.

Function Description
Complete the runningMedian function in the editor below.

runningMedian has the following parameters:
- int a[n]: an array of integers

Returns
- float[n]: the median of the array after each insertion, modify the print statement in main to get proper formatting.

Input Format

The first line contains a single integer, n, the number of integers in the data stream.
Each line i of the n subsequent lines contains an integer, a[i], to be inserted into the list.

Sample Input

STDIN   Function
-----   --------
6       a[] size n = 6
12      a = [12, 4, 5, 3, 8, 7]
4
5
3
8
7

Sample Output

12.0
8.0
5.0
4.5
5.0
6.0

Explanation

There are n=6 integers, so we must print the new median on a new line as each integer is added to the list:
1. list = {12} ,median =12.0
2. list = {4,12}, median = (4+12)/2=8.0
3. list = {4,5,12}, median = 5.0
4. list = {3,4,5,12}, median = (4+5)/2=4.5
5. list = {3,4,5,7,8,12}, median = (5+7)/2=6.0

*/

(Scala Solution)

import scala.io._
import scala.collection.mutable.PriorityQueue

object Solution {

  def main(args: Array[String]) {
    
    val sc = new java.util.Scanner (System.in);
    val n = sc.nextInt();
    val lowerQueue = new PriorityQueue[Int]
    val upperQueue = new PriorityQueue[Int].reverse
    for (i <- 1 to n) {
      val elem = sc.nextInt();
      if (lowerQueue.size > 0 && elem >= lowerQueue.head) {
        upperQueue.enqueue(elem)
      } else {
        lowerQueue.enqueue(elem)
      }
      // balance two queues
      if (upperQueue.size - lowerQueue.size == 2) {
        lowerQueue.enqueue(upperQueue.dequeue())
      } else if (lowerQueue.size - upperQueue.size ==2) {
        upperQueue.enqueue(lowerQueue.dequeue())
      }
      // print the median
      if (upperQueue.size == lowerQueue.size) {
        printf(s"%.1f\n", (upperQueue.head + lowerQueue.head).toDouble/2)
      } else if (upperQueue.size > lowerQueue.size) {
        printf(s"%.1f\n", upperQueue.head.toDouble)
      } else {
        printf(s"%.1f\n",lowerQueue.head.toDouble)
      }
    }
  }
}