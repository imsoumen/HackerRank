/*
Queen's Attack 2
---------------------------------
Link:https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/queens-attack-2/problem
---------------------------------
*/

(Scala Solution)

import java.io._
import java.math._
import java.security._
import java.text._
import java.util._
import java.util.concurrent._
import java.util.function._
import java.util.regex._
import java.util.stream._

object Solution {

    // Complete the queensAttack function below.
    def queensAttack(n: Int, k: Int, r_q: Int, c_q: Int, obstacles: Array[Array[Int]]): Int = {

        var result = 0
        for (dx <- -1 to 1;
             dy <- -1 to 1
            if (dx != 0 || dy != 0)) {
            result += calc(n, r_q, c_q, dx, dy, obstacles)
        }
        result
    }
    
    private def calc(n: Int, qx: Int, qy: Int, dx: Int, dy: Int, obstacles: Array[Array[Int]]) = {
        var result = n
        if (dx < 0) result = Math.min(result, qx - 1)
        if (dx > 0) result = Math.min(result, n - qx)
        if (dy < 0) result = Math.min(result, qy - 1)
        if (dy > 0) result = Math.min(result, n - qy)
        obstacles.map(o => {
            if ((o(0) - qx) * dy == (o(1) - qy) * dx) {
                if (dx != 0 && (o(0) - qx) / dx > 0) result = Math.min(result, (o(0) - qx) / dx - 1)
                if (dy != 0 && (o(1) - qy) / dy > 0) result = Math.min(result, (o(1) - qy) / dy - 1)
            }
        })
        result
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val nk = stdin.readLine.split(" ")

        val n = nk(0).trim.toInt

        val k = nk(1).trim.toInt

        val r_qC_q = stdin.readLine.split(" ")

        val r_q = r_qC_q(0).trim.toInt

        val c_q = r_qC_q(1).trim.toInt

        val obstacles = Array.ofDim[Int](k, 2)

        for (i <- 0 until k) {
            obstacles(i) = stdin.readLine.split(" ").map(_.trim.toInt)
        }

        val result = queensAttack(n, k, r_q, c_q, obstacles)

        printWriter.println(result)

        printWriter.close()
    }
}