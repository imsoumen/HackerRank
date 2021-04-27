/*
String Similarity
--------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/string-similarity
--------------------------

For two strings A and B, we define the similarity of the strings to be the length of the longest prefix common to both strings. For example, the similarity of strings "abc" and "abd" is 2, while the similarity of strings "aaa" and "aaab" is 3.

Calculate the sum of similarities of a string S with each of it's suffixes.

Input Format

The first line contains the number of test cases t.
Each of the next t lines contains a string to process, s.

Output Format

Output t lines, each containing the answer for the corresponding test case.

Sample Input

2
ababaa  
aa

Sample Output

11  
3

Explanation

For the first case, the suffixes of the string are "ababaa", "babaa", "abaa", "baa", "aa" and "a". The similarities of these strings with the string "ababaa" are 6,0,3,0,1, & 1 respectively. Thus, the answer is 6 + 0 + 3 + 0 + 1 + 1 = 11.

For the second case, the answer is 2 + 1 = 3.



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

object Solution {

    /*
     * Complete the 'stringSimilarity' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    def stringSimilarity(text: String): Long = {
    // Write your code here
        val arr = new Array[Long](text.length)
        val str_len = text.length
        arr(0) = str_len
        var x = 1
        var y = 0
        while (y <= str_len - 1 && x < str_len) {

        if (y == str_len - 1 || text(y + 1) != text(y - x + 1)) {
          arr(x) = y - x + 1
          if (x == y + 1){
            x += 1
            y += 1
          }else {
            var j = 1
            while (x + j + arr(j) - 1 < y && x + j <= y) {
              arr(x + j) = arr(j)
              j += 1
            }
            x = x + j
          }
        } else {
          y = y + 1
        }
        }
        return arr.sum
    }

    def main(args: Array[String]) {
        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val t = StdIn.readLine.trim.toInt

        for (tItr <- 1 to t) {
            val s = StdIn.readLine

            val result = stringSimilarity(s)

            printWriter.println(result)
        }

        printWriter.close()
    }
}
