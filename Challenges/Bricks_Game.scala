/*
Bricks Game
----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/play-game
----------------------------------

You and your friend decide to play a game using a stack consisting of N bricks. In this game, you can alternatively remove 1, 2 or 3 bricks from the top, and the numbers etched on the removed bricks are added to your score. You have to play so that you obtain the maximum possible score. It is given that your friend will also play optimally and you make the first move.

As an example, bricks are numbered arr=[1,2,3,4,5]. You can remove either [1]=1,[1,2]=3  or [1,2,3]=6. For your friend, your moves would leave the options of 1 to 3 elements from [2,3,4]=9 leaving 5 for you (total score = 6), [3,4,5]=12 or [4,5]=9. In this case, it will never be optimal for your friend to take fewer than the maximum available number of elements. Your maximum possible score is 6, achievable two ways: 1 first move and 5 the second, or [1,2,3] in your first move.

Function Description

Complete the bricksGame function in the editor below. It should return an integer that represents your maximum possible score.

bricksGame has the following parameter(s):

arr: an array of integers

Input Format

The first line will contain an integer t, the number of test cases.

Each of the next t pairs of lines are in the following format:
The first line contains an integer n, the number of bricks in arr.
The next line contains n space-separated integers $arr[i].

Output Format

For each test case, print a single line containing your maximum score.

Sample Input

2
5
999 1 1 1 0
5
0 1 1 1 999

Sample Output

1001
999

Explanation

In first test case, you will pick 999,1,1. If you play in any other way, you will not get a score of 1001.
In second case, best option will be to pick up the first brick (with 0 score) at first. Then your friend will choose the next three blocks, and you will get the last brick.

*/

(scala Solution)

import java.io.PrintWriter

object Solution {

    /*
     * Complete the bricksGame function below.
     */
    def bricksGame(arr: Array[Int]): Long = {
        /*
         * Write your code here.
         */
        val n = arr.length
        val sum = Array.ofDim[Long](n)
        sum(0) = arr(0)

        for (i <- 1 until n) {
          sum(i) = arr(i) + sum(i - 1)
        }

        if (n < 4) {
          return sum.last  
        } else {
            val dp = Array.ofDim[Long](n)
            dp(0) = sum(0)
            dp(1) = sum(1)
            dp(2) = sum(2)

            for (i <- 3 until n) {
              val pickOne = arr(i) + (sum(i - 1) - dp(i - 1))
              val pickTwo = arr(i) + arr(i - 1) + (sum(i - 2) - dp(i - 2))
              val pickThree = arr(i) + arr(i - 1) + arr(i - 2) + (sum(i - 3) - dp(i - 3))

              dp(i) = Math.max(pickOne, Math.max(pickTwo, pickThree))
            }
            return dp.last
        }
        
        
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val t = stdin.readLine.trim.toInt

        for (tItr <- 1 to t) {
            val arrCount = stdin.readLine.trim.toInt

            val arr = stdin.readLine.split(" ").map(_.trim.toInt)
            val result = bricksGame(arr.reverse)

            printWriter.println(result)
        }

        printWriter.close()
    }
}
