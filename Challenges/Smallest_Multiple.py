"""
Project Euler #5 : Smallest Multiple
---------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/euler005
---------------------------------------------

2520 is the smallest number that can be divided by each of the numbers from 1 to 20 without any remainder.
What is the smallest positive number that is evenly divisible(divisible with no remainder) by all of the numbers from 1 to N?

Input Format

First line contains T that denotes the number of test cases. This is followed by T lines, each containing an integer, N.

Output Format

Print the required answer for each test case.

Sample Input 0

2
3
10

Sample Output 0

6
2520

Explanation 0

You can check 6 is divisible by each of {1,2,3}, giving quotient of {6,3,2} respectively.
You can check 2520 is divisible by each of {1,2,3,4,5,6,7,8,9,10} giving quotient of {2520,1260,840,630,504,420,420,360,315,280,252} respectively.

"""
(Python Solution)

import sys,math


t = int(input().strip())
for a0 in range(t):
    n = int(input().strip())
    ans = 1
    for i in range(1, n + 1):  
        ans = int((ans * i)/math.gcd(ans, i))          
    print(ans)
	
	
(Scala Solution)

object Solution {
    
    def gcd(a: Int, b: Int): Int =
        if (b == 0) a else gcd(b, a % b)
    
    def main(args: Array[String]) {
        val sc = new java.util.Scanner (System.in);
        var t = sc.nextInt();
        var a0 = 0;
        while(a0 < t){
            var n = sc.nextInt();
            var ans = 1;
            a0+=1;
            for ( i <- Range(1,n+1) ) {
                ans = ((ans * i)/gcd(ans,i)).toInt
            }
        println(ans)
        }
    }
}