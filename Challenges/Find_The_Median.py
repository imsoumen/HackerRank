"""
Find The Median
----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/find-the-median
----------------------------------

The median of a list of numbers is essentially its middle element after sorting. The same number of elements occur after it as before. Given a list of numbers with an odd number of elements, find the median?

Example
arr = [5,3,1,2,4]

The sorted array arr' = [1,2,3,4,5]. The middle element and the median is 3.

Function Description

Complete the findMedian function in the editor below.

findMedian has the following parameter(s):

int arr[n]: an unsorted array of integers

Returns

int: the median of the array

Input Format

The first line contains the integer n, the size of arr.
The second line contains n space-separated integers arr[i]

Sample Input 0

7
0 1 2 4 6 5 3

Sample Output 0

3

Explanation 0

The sorted arr = [0,1,2,3,4,5,6]. It's middle element is at arr[3] = 3.

"""

(Python Solution)

#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'findMedian' function below.
#
# The function is expected to return an INTEGER.
# The function accepts INTEGER_ARRAY arr as parameter.
#

def findMedian(arr):
    # Write your code here
    arr.sort()
    if(len(arr)%2 == 1):
        return arr[int(len(arr)/2)]
    else:
        return (arr[math.floor(len(arr)/2)+math.ceil(len(arr)/2)])/2

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input().strip())

    arr = list(map(int, input().rstrip().split()))

    result = findMedian(arr)

    fptr.write(str(result) + '\n')

    fptr.close()

