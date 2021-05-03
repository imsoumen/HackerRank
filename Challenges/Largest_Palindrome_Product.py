"""
Project Euler #4: Largest palindrome product
-------------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/euler004
-------------------------------------------------

A palindromic number reads the same both ways. The smallest 6 digit palindrome made from the product of two 3-digit numbers is 101101 = 143x107.

Find the largest palindrome made from the product of two 3-digit numbers which is less than N.

Input Format

First line contains T that denotes the number of test cases. This is followed by T lines, each containing an integer, N.

Output Format

Print the required answer for each test case in a new line.

Sample Input 0

2
101110
800000

Sample Output 0

101101
793397

Explanation 0

101101 is product of 143 and 707.
793397 is product of 869 and 913.
 
 """
 
(Python Solution)

#!/bin/python3

import sys


palindromelist = []
for i in range(100, 1000):
    for j in range(100, 1000):
        a = i * j
        if str(a) == str(a)[::-1] and a not in palindromelist:
            palindromelist.append(a)
palindromelist.sort()
length = len(palindromelist)


if __name__ == '__main__':
    n = int(input())
    for _ in range(n):
        a = int(input())
        for i in range(length - 1, -1, -1):
            if palindromelist[i] < a:
                print(palindromelist[i])
                break
