/*
Tell the Average
--------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/tell-the-average
--------------------------------------------

James is very naive in Mathematics, He always makes new things out of a given list of integers. Today he is given a list L, so he creates a value S out of it.

S from a given list can be calculated as follows.

value_of_S(list L)
{
    while ((number of elements in L) > 1)
    {
        a = L[0]
        b = L[1]
        delete L[1]
        L[0] = a+b+ab
    }
    return L[0] % 1000000007
}
James has an ample amount of time, so he calculates the values of S for all the permutations of the given list L and finds their average value. Then he asks you to report that value.

Input Format
The first line contains an integer N, the number of integers in the list.
The second line contains N integral values, L[0],L[1],...,L[N-1] separated by single spaces.

Output Format
Print the floor of the average value.

Sample Input

2
2 3

Sample Output

11

Explanation
The S value corresponding to the two permutations of the given list is 11.

*/

(Scala Solution)

import scala.io.StdIn.{readInt,readLine}

object Solution {

    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
        val count = readInt()
        val arr = readLine.split(" ").map(_.toLong).toArray
        println(solve(arr))
    }
    
    def solve(arr: Array[Long]): Long = {
        var sum = arr(0)
        for (i <- 1 until arr.length) {
            sum = (sum + arr(i) + sum * arr(i)) % 1000000007
        }
        return sum
    }
}