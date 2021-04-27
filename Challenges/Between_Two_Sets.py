"""
Between Two Sets
-----------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/between-two-sets
-----------------------------

There will be two arrays of integers. Determine all integers that satisfy the following two conditions:

The elements of the first array are all factors of the integer being considered
The integer being considered is a factor of all elements of the second array
These numbers are referred to as being between the two arrays. Determine how many such numbers exist.

Example:
a = [2,6]
b = [24,36]

There are two numbers between the arrays: 6 and 12.
6%2=0, 6%6=0, 24%6=0 and 36%6=0 for the first value.
12%2=0, 12%6=0 and 24%12=0, 36%12=0 for the second value. Return 2.

Function Description:
Complete the getTotalX function in the editor below. It should return the number of integers that are betwen the sets.

getTotalX has the following parameter(s):

int a[n]: an array of integers
int b[m]: an array of integers

Returns:
int: the number of integers that are between the sets

Input Format:

The first line contains two space-separated integers, n and m, the number of elements in arrays a and b.
The second line contains  distinct space-separated integers a[i] where 0 <= i < n.
The third line contains  distinct space-separated integers b[j] where 0 <=j < m.

Sample Input

2 3
2 4
16 32 96
Sample Output
3

Explanation

2 and 4 divide evenly into 4, 8, 12 and 16.
4, 8 and 16 divide evenly into 16, 32, 96.

4, 8 and 16 are the only three numbers for which each element of a is a factor and each is a factor of all elements of b.
"""
(Python Solution)

def getTotalX(a, b):
    # Write your code here
    result = 0
    for i in range(1,101):
        flag = True
        for j in a:
            if i%j!=0:
                flag = False
                break
        if flag:
            for k in b:
                if k%i!=0:
                    flag = False
                    break
        if flag:
            result+=1
            
    return result

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    first_multiple_input = input().rstrip().split()

    n = int(first_multiple_input[0])

    m = int(first_multiple_input[1])

    arr = list(map(int, input().rstrip().split()))

    brr = list(map(int, input().rstrip().split()))

    total = getTotalX(arr, brr)

    fptr.write(str(total) + '\n')

    fptr.close()
