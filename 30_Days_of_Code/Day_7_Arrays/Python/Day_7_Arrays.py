/*
#####Objective#####
Today, we're learning about the Array data structure. Check out the Tutorial tab for learning materials and an instructional video!

#####Task#####
Given an array, A, of N integers, print A's elements in reverse order as a single line of space-separated numbers.

#####Input Format#####

The first line contains an integer, N (the size of our array).
The second line contains N space-separated integers describing array A's elements.

#####Constraints#####

1 <= N <= 1000
1 <= A(i) <=10000 ; where A(i) is the ith integer in the array.

#####Output Format#####

Print the elements of array A in reverse order as a single line of space-separated numbers.

#####Sample Input#####
4
1 4 3 2

#####Sample Output#####
2 3 4 1

*/

// PYTHON CODE

#!/bin/python3

import math
import os
import random
import re
import sys



if __name__ == '__main__':
    n = int(input().strip())

    arr = [int(arr_temp) for arr_temp in input().strip().split(' ')]

    for i in range(len(arr)):
        print(str(arr[-i-1]), end=" ")  


