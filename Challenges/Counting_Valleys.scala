/*
Counting Valleys
--------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/counting-valleys
--------------------------------

An avid hiker keeps meticulous records of their hikes. During the last hike that took exactly STEPS steps, for every step it was noted if it was an uphill, U, or a downhill, D step. Hikes always start and end at sea level, and each step up or down represents a 1 unit change in altitude. We define the following terms:

A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and ending with a step down to sea level.
A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and ending with a step up to sea level.
Given the sequence of up and down steps during a hike, find and print the number of valleys walked through.

Example

steps = 8 path = [DDUUUUDD]

The hiker first enters a valley 2 units deep. Then they climb out and up onto a mountain 2 units high. Finally, the hiker returns to sea level and ends the hike.

Function Description:
Complete the countingValleys function in the editor below.

countingValleys has the following parameter(s):

int steps: the number of steps on the hike
string path: a string describing the path
Returns

int: the number of valleys traversed
Input Format

The first line contains an integer STEPS, the number of steps in the hike.
The second line contains a single string path, of STEPS characters that describe the path.

Sample Input:
8
UDDDUDUU

Sample Output
1

Explanation
If we represent _ as sea level, a step up as /, and a step down as \, the hike can be drawn as:

_/\      _
   \    /
    \/\/
The hiker enters and leaves one valley.

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
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */

    def countingValleys(steps: Int, path: String): Int = {
    // Write your code here
        var level = 0
        var hillCount = 0
        var valleyCount = 0
        for ( i <- path.toList) {
        i match {
            case 'U' => { level += 1 }
            case 'D' => { level -= 1 }
        }
        if ( level == 0 && i == 'U' ) { valleyCount += 1 }
        else if ( level == 0 && i == 'D' ) { hillCount += 1 }
        }
        valleyCount
        //println(hillCount)
        
    }

}

object Solution {
    def main(args: Array[String]) {
        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val steps = StdIn.readLine.trim.toInt

        val path = StdIn.readLine

        val result = Result.countingValleys(steps, path)

        printWriter.println(result)

        printWriter.close()
    }
}
