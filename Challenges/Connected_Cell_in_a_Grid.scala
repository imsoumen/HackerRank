/*
Connected Cell in a Grid
----------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/connected-cell-in-a-grid
----------------------------------------

Consider a matrix where each cell contains either a 0 or a 1. Any cell containing a 1 is called a filled cell. Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally. In the following grid, all cells marked X are connected to the cell marked Y.

XXX
XYX  
XXX    

If one or more filled cells are also connected, they form a region. Note that each cell in a region is connected to zero or more cells in the region but is not necessarily directly connected to all the other cells in the region.

Given an n x m matrix, find and print the number of cells in the largest region in the matrix. Note that there may be more than one region in the matrix.

For example, there are two regions in the following 3 x 3 matrix. The larger region at the top left contains 1 cells. The smaller one at the bottom right contains 1.

110
100
001

Function Description

Complete the connectedCell function in the editor below.

connectedCell has the following parameter(s):
- int matrix[n][m]: matrix[i] represents the ith row of the matrix

Returns
- int: the area of the largest region

Input Format

The first line contains an integer n, the number of rows in the matrix.
The second line contains an integer m, the number of columns in the matrix.
Each of the next n lines contains  space-separated integers matrix[i][j].

Sample Input

STDIN       Function
-----       --------
4           n = 4
4           m = 4
1 1 0 0     grid = [[1, 1, 1, 0], [0, 1, 1, 0], [0, 0, 1, 0], [1, 0, 0, 0]]
0 1 1 0
0 0 1 0
1 0 0 0

Sample Output

5

Explanation

The diagram below depicts two regions of the matrix. Connected regions are filled with X or Y. Zeros are replaced with dots for clarity.

X X . .
. X X .
. . X .
Y . . .

The larger region has 5 cells, marked X.



*/

(Scala Solution)

import scala.annotation.tailrec
import java.util.Scanner
import scala.math._

object Solution {
    def solve(matrix: Array[Array[Int]]): Int = {
        val m = matrix.length
        val n = matrix(0).length

        def dfs(x: Int, y: Int): Int = {
            if (x < 0 || x >= m) return 0
            if (y < 0 || y >= n) return 0
            if (matrix(x)(y) != 1) return 0

            matrix(x)(y) = -1
            return dfs(x - 1, y - 1) + dfs(x - 1, y) + dfs(x + 1, y + 1) +
                   dfs(x, y - 1) + 1 + dfs(x, y + 1) +
                   dfs(x + 1, y - 1) + dfs(x + 1, y) + dfs(x + 1, y + 1)
        }

        var ans = 0
        for (x <- 0 until m; y <- 0 until n) {
            ans = max(ans, dfs(x, y))
        }
        ans
    }

    def main(args: Array[String]) {
        val sc = new Scanner(System.in)
        val m = sc.nextInt
        val n = sc.nextInt

        val matrix = Array.ofDim[Int](m, n)
        for (x <- 0 until m; y <- 0 until n)
            matrix(x)(y) = sc.nextInt

        println(solve(matrix))
    }
}