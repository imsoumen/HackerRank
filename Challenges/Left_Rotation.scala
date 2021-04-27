/*
Left Rotation
-----------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/array-left-rotation
-----------------------------

A left rotation operation on an array of size n shifts each of the array's elements 1 unit to the left. Given an integer, d, rotate the array that many steps left and return the result.

Example:
d=2
arr=[1,2,3,4,5]

After 2 rotations, [3,4,5,1,2].

Function Description:
Complete the rotateLeft function in the editor below.

rotateLeft has the following parameters:

int d: the amount to rotate by
int arr[n]: the array to rotate

Returns:
int[n]: the rotated array

Input Format

The first line contains two space-separated integers that denote n, the number of integers, and d, the number of left rotations to perform.
The second line contains n space-separated integers that describe arr[].

Sample Input
5 4
1 2 3 4 5

Sample Output
5 1 2 3 4

Explanation:

To perform d=4 left rotations, the array undergoes the following sequence of changes:
[1,2,3,4,5] -> [2,3,4,5,1] -> [3,4,5,1,2] -> [4,5,1,2,3] -> [5,1,2,3,4]

*/

(Scala Solution)
def rotateLeft(d: Int, arr: Array[Int]): Array[Int] = {
    // Write your code here
        
        val temp = (for(i <- 0 to d - 1) yield arr(i))
        var m = 0
        for(i <- d to arr.length -1) {
            arr(m) = arr(i)
            m = m+1
        }
        for(i <- 0 to temp.length -1){
            arr(m) = temp(i)
            m = m+1
        }
        
        
       return arr 
    }


