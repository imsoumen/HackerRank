/*
Grading Students
------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/grading
------------------------------------------

HackerLand University has the following grading policy:

Every student receives a grade in the inclusive range from 0 to 100.
Any grade less than 40 is a failing grade.
Sam is a professor at the university and likes to round each student's grade according to these rules:

If the difference between the grade and the next multiple of 5 is less than 3, round grade up to the next multiple of 5.
If the value of grade is less than 38, no rounding occurs as the result will still be a failing grade.
Examples

 grade=84 round to 85 (85 - 84 is less than 3)
 grade=29 do not round (result is less than 40)
 grade=57 do not round (60 - 57 is 3 or higher)
Given the initial value of grade for each of Sam's n students, write code to automate the rounding process.

Function Description

Complete the function gradingStudents in the editor below.

gradingStudents has the following parameter(s):

int grades[n]: the grades before rounding

Returns

int[n]: the grades after rounding as appropriate

Input Format

The first line contains a single integer, n, the number of students.
Each line i of the n subsequent lines contains a single integer, grades[i].

Sample Input 0

4
73
67
38
33

Sample Output 0

75
67
40
33

Explanation 0

Student 1 received a 73, and the next multiple of 5 from 73 is 75. Since 75-73<3, the student's grade is rounded to 75.
Student 2 received a 67, and the next multiple of 5 from 67 is 70. Since 60-67!<3, the grade will not be modified and the student's final grade is 67.
Student 3 received a 38, and the next multiple of 5 from 38 is 40. Since 40-38<3, the student's grade will be rounded to 40.
Student 4 received a grade below 33, so the grade will not be modified and the student's final grade is 33.


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
     * Complete the 'gradingStudents' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY grades as parameter.
     */

    def gradingStudents(grades: Array[Int]): Array[Int] = {
    // Write your code here
        var finalGrades = Array.ofDim[Int](grades.length)
        var multipleOf = 5
        var nextGrade = 0
        
        for ( i <- 0 until grades.length ){
            if ( grades(i) >= 38 ){
                nextGrade = grades(i) + (multipleOf - grades(i) % multipleOf)
                if ( nextGrade-grades(i) >= 3 ) finalGrades(i) = grades(i)
                else finalGrades(i) = nextGrade
            } else finalGrades(i) = grades(i)
            
        }
        
        return finalGrades
    }

}

object Solution {
    def main(args: Array[String]) {
        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val gradesCount = StdIn.readLine.trim.toInt

        val grades = Array.ofDim[Int](gradesCount)

        for (i <- 0 until gradesCount) {
            val gradesItem = StdIn.readLine.trim.toInt
            grades(i) = gradesItem
        }

        val result = Result.gradingStudents(grades)

        printWriter.println(result.mkString("\n"))

        printWriter.close()
    }
}