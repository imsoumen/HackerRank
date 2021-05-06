/*
Closest Number
--------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/closest-number
--------------------------------
You are given 3 numbers a, b and x. You need to output the multiple of x which is closest to a^b. If more than one answer exists , display the smallest one.

Input Format

The first line contains T, the number of testcases.
T lines follow, each line contains 3 space separated integers (a, b and x respectively)

Constraints

1 ≤ T ≤ 105
1 ≤ x ≤ 109
0 < ab ≤ 109
1 ≤ a ≤ 109
-109 ≤ b ≤ 109

Output Format

For each test case , output the multiple of x which is closest to ab

Sample Input 0

3
349 1 4
395 1 7
4 -2 2

Sample Output 0

348
392
0

Explanation 0

The closest multiple of 4 to 349 is 348.
The closest multiple of 7 to 395 is 392.
The closest multiple of 2 to 1/16 is 0.



*/

(Scala Solution)

object Result {

    /*
     * Complete the 'closestNumber' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER a
     *  2. INTEGER b
     *  3. INTEGER x
     */

    def closestNumber(a: Int, b: Int, x: Int): Int = {
    // Write your code here     
        if (a == 1) x
        else if (b < 0) 0
        else {
          val powered = Math.pow(a, b).toInt
          val rem = powered % x
          if (rem > x / 2) powered - rem + x
          else powered - rem
        }
        
    }

}

object Solution {
    def main(args: Array[String]) {
        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val t = StdIn.readLine.trim.toInt

        for (tItr <- 1 to t) {
            val firstMultipleInput = StdIn.readLine.replaceAll("\\s+$", "").split(" ")

            val a = firstMultipleInput(0).toInt

            val b = firstMultipleInput(1).toInt

            val x = firstMultipleInput(2).toInt

            val result = Result.closestNumber(a, b, x)

            printWriter.println(result)
        }

        printWriter.close()
    }
}
