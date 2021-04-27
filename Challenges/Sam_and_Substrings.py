"""
Sam and Substrings
--------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/sam-and-substrings
--------------------------------

Samantha and Sam are playing a numbers game. Given a number as a string, no leading zeros, determine the sum of all integer values of substrings of the string.

Given an integer as a string, sum all of its substrings cast as integers. As the number may become large, return the value modulo 10^9 +7.

Example
n = 42

Here n is a string that has 3 integer substrings: 4, 2, and 42. Their sum is 48, and 48 modulo (10^9 +7) = 48.

Function Description

Complete the substrings function in the editor below.

substrings has the following parameter(s):

string n: the string representation of an integer
Returns

int: the sum of the integer values of all substrings in n, modulo 10^9 + 7
Input Format

A single line containing an integer as a string, without leading zeros.

Sample Input 0

16
Sample Output 0

23
Explanation 0

The substrings of 16 are 16, 1 and 6 which sum to 23.

Sample Input 1

123
Sample Output 1

164
Explanation 1

The substrings of 123 are 1, 2, 3, 12, 23, 123 which sum to 164.

"""

(Python Solution)

#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the substrings function below.
def substrings(n):
    mod=10**9+7
    l=len(str(n))
    dp=[0]*l
    s=str(n)
    dp[0]=int(s[0])
    for i in range(1,l):
        dp[i]=dp[i-1]*10+i*int(s[i])+int(s[i])
        dp[i]%=mod
    return sum(dp)%mod
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = input()

    result = substrings(n)

    fptr.write(str(result) + '\n')

    fptr.close()
