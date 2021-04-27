/*
Bigger is Greater
-----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/bigger-is-greater
-----------------------------------

Lexicographical order is often known as alphabetical order when dealing with strings. A string is greater than another string if it comes later in a lexicographically sorted list.

Given a word, create a new word by swapping some or all of its characters. This new word must meet two criteria:

It must be greater than the original word
It must be the smallest word that meets the first condition

Example
w = abcd

The next largest word is abdc.

Complete the function biggerIsGreater below to create and return the new string meeting the criteria. If it is not possible, return no answer.

Function Description

Complete the biggerIsGreater function in the editor below.

biggerIsGreater has the following parameter(s):

string w: a word

Returns
- string: the smallest lexicographically higher string possible or no answer

Input Format

The first line of input contains T, the number of test cases.
Each of the next T lines contains w.

Sample Input 0

5
ab
bb
hefg
dhck
dkhc

Sample Output 0

ba
no answer
hegf
dhkc
hcdk

Explanation 0

Test case 1:
ba is the only string which can be made by rearranging ab. It is greater.
Test case 2:
It is not possible to rearrange bb and get a greater string.
Test case 3:
hegf is the next string greater than hefg.
Test case 4:
dhkc is the next string greater than dhck.
Test case 5:
hcdk is the next string greater than dkhc.

Sample Input 1

6
lmno
dcba
dcbb
abdc
abcd
fedcbabcd

Sample Output 1

lmon
no answer
no answer
acbd
abdc
fedcbabdc

*/


(Scala Solution)

import scala.io.StdIn._

object Solution {

    def main(args: Array[String]) {
        val num = readInt()
        for (_ <- 0 until num) {
            val str = readLine()
            var i = str.length - 2
            var m = str(str.length - 1)
            while (i >= 0 && str(i) >= m) {
                m = m.max(str(i))
                i -= 1
            }
            if (i < 0) {
                println("no answer")
            } else {
                var j = str.length - 1
                while (str(i) >= str(j)) {
                    j -= 1
                }
                val begin = str.substring(0, i)
                val next = str(j)
                val a = str.substring(i + 1).toArray
                a(j - i - 1) = str(i)
                val as = a.sorted
                println(begin + next + as.mkString(""))
            }
        }
    }
}


