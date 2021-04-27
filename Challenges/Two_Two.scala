/*
Two Two
------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/two-two
------------------------------------

Prof. Twotwo as the name suggests is very fond powers of 2. Moreover he also has special affinity to number 800. He is known for carrying quirky experiments on powers of 2.

One day he played a game in his class. He brought some number plates on each of which a digit from 0 to 9 is written. He made students stand in a row and gave a number plate to each of the student. Now turn by turn, he called for some students who are standing continuously in the row say from index i to index j (i<=j) and asked them to find their strength.

The strength of the group of students from i to j is defined as:

strength(i , j)
{
    if a[i] = 0
        return 0; //If first child has value 0 in the group, strength of group is zero
    value = 0;
    for k from i to j
        value = value*10 + a[k]
    return value;
} 
Prof called for all possible combinations of i and j and noted down the strength of each group. Now being interested in powers of 2, he wants to find out how many strengths are powers of two. Now its your responsibility to get the answer for prof.

Input Format

First line contains number of test cases T
Next T line contains the numbers of number plates the students were having when standing in the row in the form of a string A.

Constraints

1 ≤ T ≤ 100
1 ≤ len(A) ≤ 105
0 ≤ A[i] ≤ 9

Output Format

Output the total number of strengths of the form 2x such that 0 ≤ x ≤ 800.

Sample Input 0

5
2222222
24256
65536
023223
33579

Sample Output 0

7
4
1
4
0

Explanation 0

In following explanations group i-j is group of student from index i to index j (1-based indexing)

In first case only 2 is of form power of two. It is present seven times for groups 1-1,2-2,3-3,4-4,5-5,6-6,7-7

In second case 2,4 and 256 are of required form. 2 is strength of group 1-1 and 3-3, 4 is strength of group 2-2 and 256 is strength of group 3-5.

In third case 65536 is only number in required form. It is strength of group 1-5

In fourth case 2 and 32 are of forms power of 2. Group 1-2 has values 0,2 but its strength is 0, as first value is 0.

In fifth case, None of the group has strength of required form.



*/

(Scala Solution)

import java.io.PrintWriter
import collection.mutable

object Solution {

  class Node(val edges: mutable.HashMap[Char, Node], var endOfWord: Boolean)

  def buildTrie(words: mutable.ArrayBuffer[String]): Node = {
    val trie = new Node(mutable.HashMap(), false)
    for(word <- words) {
      var node = trie
      for(char <- word) {
        node.edges.get(char) match {
          case Some(nextNode) =>
            node = nextNode
          case None =>
            val nextNode = new Node(mutable.HashMap(), false)
            node.edges(char) = nextNode
            node = nextNode
        }
      }
      node.endOfWord = true
    }
    trie
  }

  def searchTrie(trie: Node, s: String, offset: Int): Int = {
    var node = trie
    var i = offset
    var done = false
    var count = 0
    while(!done && i < s.length) {
      node.edges.get(s(i)) match {
        case Some(nextNode) =>
          node = nextNode
          if(node.endOfWord)
            count += 1
        case None =>
          done = true
      }
      i += 1
    }
    count
  }

  var v = BigInt(1)
  val powersOfTwo = mutable.ArrayBuffer(v.toString)
  for(_ <- (1 to 800)) {
    v *= 2
    powersOfTwo += v.toString
  }
  val powersOfTwoTrie = buildTrie(powersOfTwo)

  def twoTwo(a: String): Int = {
    var count = 0
    for(i <- 0 until a.length) {
      count += searchTrie(powersOfTwoTrie, a, i)
    }
    count
  }


    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val t = stdin.readLine.trim.toInt

        for (tItr <- 1 to t) {
            val a = stdin.readLine

            val result = twoTwo(a)

            printWriter.println(result)
        }

        printWriter.close()
    }
}