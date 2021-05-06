/*
nCr Table
--------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/ncr-table
--------------------------------------

Jim is doing his discrete maths homework which requires him to repeatedly calculate nCr(n choose r) for different values of n. Knowing that this is time consuming, he goes to his sister June for help. June, being a computer science student knows how to convert this into a computer program and generate the answers quickly. She tells him, by storing the lower values of nCr(n choose r), one can calculate the higher values using a very simple formula.

If you are June, how will you calculate nCr values for different values of n?

Since nCr values will be large you have to calculate them modulo 10^9.

Input Format
The first line contains the number of test cases T.
T lines follow each containing an integer n.

Output Format
For each n output the list of nC0 to nCn each separated by a single space in a new line. If the number is large, print only the last 9 digits. i.e. modulo 109

Constraints
1<=T<=200
1<=n< 1000

Sample Input

3
2
4
5

Sample Output

1 2 1
1 4 6 4 1
1 5 10 10 5 1

Explanation

For 2 we can check 2C0 2C1 and 2C2 are 1, 2 and 1 respectively. The other inputs' answer follow similar pattern.
*/

(Scala Solution)

object Solution {
    val sc = new java.util.Scanner(System.in)
    val MOD: Int = 1000000000
    val table = Array.ofDim[Int](1001, 1001)

    def nchoosek(n: Int, k: Int): Int = {
        if (table(n)(k) > 0) {
            return table(n)(k)
        }
        var res: Int = 0
        if (k == 0 || k == n) {
            res = 1
        } else {
            res = nchoosek(n - 1, k - 1) + nchoosek(n - 1, k)
            res = res % MOD
        }
        table(n)(k) = res
        res
    }
        
    def sol(t: Int) {
        val n = sc.nextInt
        var i: Int = 0
        while (i <=n) {
            if (i != 0) {
                print(" ")
            }
            print(nchoosek(n, i))
            i = i + 1
        }
        println
    }
        
    def main(args: Array[String]) {
        val t = sc.nextInt
        (1 to t) foreach (sol)
    }
}