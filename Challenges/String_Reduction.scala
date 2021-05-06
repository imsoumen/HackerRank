/*
String Reduction
----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/string-reduction
----------------------------------

Given a string consisting of the letters a, b and c, we can perform the following operation:

Take any two adjacent distinct characters and replace them with the third character.
Find the shortest string obtainable through applying this operation repeatedly.

For example, given the string aba we can reduce it to a 1 character string by replacing ab with c and ca with b: aba -> ca -> b.

Function Description

Complete the stringReduction function in the editor below. It must return an integer that denotes the length of the shortest string obtainable.

stringReduction has the following parameter:
- s: a string

Input Format

The first line contains the number of test cases t.

Each of the next t lines contains a string s to process.

Output Format

For each test case, print the length of the resultant minimal string on a new line.

Sample Input

3  
cab  
bcab  
ccccc

Sample Output

2  
1  
5

Explanation

For the first case, there are two solutions: cab -> cc or cab -> bb.
For the second case, one optimal solution is: bcab -> aab -> ac -> b.
For the third case, no operations can be performed so the answer is 5.



*/

(Scala Solution)

object Solution {

    def main(args: Array[String]) {
        val sc = new java.util.Scanner (System.in)
        val n = sc.nextInt()
        for(i <- 1 to n) {
            println(Solution.resolve( sc.next() ))
        }
    }
    
    def resolve(input: String): Int = {
        val aCount = countChar(input, "a")
        val bCount = countChar(input, "b")
        val cCount = countChar(input, "c")
        if( input.length == aCount || input.length == bCount || input.length == cCount) {
            input.length
        } else if(isEven(aCount, bCount, cCount) || isOdd(aCount, bCount, cCount) ) {
            2
        } else {
            1
        }
    }
    
    def countChar(input: String, c: String) : Int = {
        val length = input.length
        val zipInput = input.replaceAll(c, "")
        length - zipInput.length
    }
    
    def isEven(a: Int, b: Int, c: Int): Boolean = {
        (a % 2) == 0 && (b % 2) == 0 && (c % 2) == 0
    }
    
    def isOdd(a: Int, b: Int, c: Int): Boolean = {
        (a % 2) != 0 && (b % 2) != 0 && (c % 2) != 0
    }
}