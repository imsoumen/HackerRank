/*
Angry Professor
-----------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/angry-professor
-----------------------------

A Discrete Mathematics professor has a class of students. Frustrated with their lack of discipline, the professor decides to cancel class if fewer than some number of students are present when class starts. Arrival times go from on time (arrivalTime<=0) to arrived late (arrivalTime>0).

Given the arrival time of each student and a threshhold number of attendees, determine if the class is cancelled.

Example
n=5
k=3
a=[-2,-1,0,1,2]


The first 3 students arrived on. The last 2 were late. The threshold is 3 students, so class will go on. Return NO.

Note: Non-positive arrival times (a[i]<=0) indicate the student arrived early or on time; positive arrival times (a[i]>0) indicate the student arrived a[i] minutes late.

Function Description

Complete the angryProfessor function in the editor below. It must return YES if class is cancelled, or NO otherwise.

angryProfessor has the following parameter(s):

int k: the threshold number of students
int a[n]: the arrival times of the  students

Returns

string: either YES or NO

Input Format

The first line of input contains t, the number of test cases.

Each test case consists of two lines.

The first line has two space-separated integers, n and k, the number of students (size of a) and the cancellation threshold.
The second line contains n space-separated integers (a[1],a[2],a[3],...a[n]) that describe the arrival times for each student.

Sample Input

2
4 3
-1 -3 4 2
4 2
0 -1 2 1

Sample Output

YES
NO

Explanation

For the first test case, k=3. The professor wants at least 3 students in attendance, but only 2 have arrived on time (-3 and -1) so the class is cancelled.

For the second test case, k=2. The professor wants at least 2 students in attendance, and there are 2 who arrived on time (0 and -1). The class is not cancelled.

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

object Solution {

    // Complete the angryProfessor function below.
    def angryProfessor(k: Int, a: Array[Int]): String = {
        var res = "NO"
        val arr = a.sorted
        for ( i <- 0 until k){
            if ( arr(i) > 0 ) res = "YES"
            else res = "NO"
        }
        
        return res
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val t = stdin.readLine.trim.toInt

        for (tItr <- 1 to t) {
            val nk = stdin.readLine.split(" ")

            val n = nk(0).trim.toInt

            val k = nk(1).trim.toInt

            val a = stdin.readLine.split(" ").map(_.trim.toInt)
            val result = angryProfessor(k, a)

            printWriter.println(result)
        }

        printWriter.close()
    }
}
