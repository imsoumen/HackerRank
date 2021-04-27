/*

Equal Stacks
--------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/equal-stacks
--------------------------------------

You have three stacks of cylinders where each cylinder has the same diameter, but they may vary in height. You can change the height of a stack by removing and discarding its topmost cylinder any number of times.

Find the maximum possible height of the stacks such that all of the stacks are exactly the same height. This means you must remove zero or more cylinders from the top of zero or more of the three stacks until they are all the same height, then return the height.

Example

h1 = [1,2,1,1]
h2 = [1,1,2]
h3 = [1,1]

There are 4,3 and 2 cylinders in the three stacks, with their heights in the three arrays. Remove the top 2 cylinders from h1 (heights = [1, 2]) and from h2 (heights = [1, 1]) so that the three stacks all are 2 units tall. Return 2 as the answer.

Note: An empty stack is still a stack.

Function Description

Complete the equalStacks function in the editor below.

equalStacks has the following parameters:

int h1[n1]: the first array of heights
int h2[n2]: the second array of heights
int h3[n3]: the third array of heights

Returns

int: the height of the stacks when they are equalized

Input Format

The first line contains three space-separated integers, n1, n2, and n3, the numbers of cylinders in stacks 1, 2, and 3. The subsequent lines describe the respective heights of each cylinder in a stack from top to bottom:

The second line contains n1 space-separated integers, the cylinder heights in stack 1. The first element is the top cylinder of the stack.
The third line contains n2 space-separated integers, the cylinder heights in stack 2. The first element is the top cylinder of the stack.
The fourth line contains n3 space-separated integers, the cylinder heights in stack 3. The first element is the top cylinder of the stack.

Sample Input

STDIN       Function
-----       --------
5 3 4       h1[] size n1 = 5, h2[] size n2 = 3, h3[] size n3 = 4  
3 2 1 1 1   h1 = [3, 2, 1, 1, 1]
4 3 2       h2 = [4, 3, 2]
1 1 4 1     h3 = [1, 1, 4, 1]

Sample Output

5

Explanation

Initially, the stacks look like this:

	3	4	1
	2	3	1
	1
	1	2	4
	1		1

To equalize thier heights, remove the first cylinder from stacks 1 and 2, and then remove the top two cylinders from stack 3 (shown below).
			1
  3		4	1
	2	3	4
	1	2	1
	1
	1
The stack heights are reduced as follows:
	8-3=5
	9-4=5
	7-1-1=5

All three stacks now have height=5, the value to return.

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
     * Complete the 'equalStacks' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY h1
     *  2. INTEGER_ARRAY h2
     *  3. INTEGER_ARRAY h3
     */

    def equalStacks(h1: Array[Int], h2: Array[Int], h3: Array[Int]): Int = {
    // Write your code here
        val stack1 = h1.reverse
        val stack2 = h2.reverse
        val stack3 = h3.reverse
        
        val stack1CumulativeSums = stack1.scanLeft(0)(_ + _).toSet
        val stack2CumulativeSums = stack2.scanLeft(0)(_ + _).toSet
        val stack3CumulativeSums = stack3.scanLeft(0)(_ + _).toSet
        
        val res = (stack1CumulativeSums & stack2CumulativeSums & stack3CumulativeSums).max
        
        return res
    }

}

object Solution {
    def main(args: Array[String]) {
        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val firstMultipleInput = StdIn.readLine.replaceAll("\\s+$", "").split(" ")

        val n1 = firstMultipleInput(0).toInt

        val n2 = firstMultipleInput(1).toInt

        val n3 = firstMultipleInput(2).toInt

        val h1 = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

        val h2 = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

        val h3 = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

        val result = Result.equalStacks(h1, h2, h3)

        printWriter.println(result)

        printWriter.close()
    }
}
