/*

Highest Value Palindrome
----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/richie-rich
----------------------------------

Palindromes are strings that read the same from the left or right, for example madam or 0110.

You will be given a string representation of a number and a maximum number of changes you can make. Alter the string, one digit at a time, to create the string representation of the largest number possible given the limit to the number of changes. The length of the string may not be altered, so you must consider 0's left of all higher digits in your tests. For example 0110 is valid, 0011 is not.

Given a string representing the starting number, and a maximum number of changes allowed, create the largest palindromic string of digits possible or the string '-1' if it is not possible to create a palindrome under the contstraints.

Example
s = 1231
k = 3

Make 3 replacements to get 9339.
s = 12321
k = 1

Make 1 replacement to get 12921.

Function Description:
Complete the highestValuePalindrome function in the editor below.

highestValuePalindrome has the following parameter(s):

string s: a string representation of an integer
int n: the length of the integer string
int k: the maximum number of changes allowed
Returns

string: a string representation of the highest value achievable or -1
Input Format

The first line contains two space-separated integers, n and k, the number of digits in the number and the maximum number of changes allowed.
The second line contains an n-digit string of numbers.

Output Format

Sample Input 0
STDIN   Function
-----   --------
4 1     n = 4, k = 1
3943    s = '3943'

Sample Output 0
3993

Sample Input 1
6 3
092282

Sample Output 1
992299

Sample Input 2
4 1
0011

Sample Output 2
-1

Explanation
Sample 0

There are two ways to make 3943 a palindrome by changing no more than k=1 digits:
1) 3943 -> 3443
2) 3943 -> 3993

3993 > 3443

*/

(Scala Solution)

object Solution {

    def main(args: Array[String]) {
      val sc = new java.util.Scanner (System.in);
      var n = sc.nextInt(); //number of letters
      var k = sc.nextInt(); //number of changes
      var num = sc.next().toCharArray;
      var numC = num.clone
      var change = k
      
     for (i <- 0 until n/2) {
        if(num(i) != num(n - i - 1)) {
          if(num(n - i - 1) == '9') {numC(i) = '9'}
          else if (num(i) == '9') {numC(n - i - 1) = '9'}
          else {numC(i) = 'x'}
          change -= 1
        }
      }
      
      for (i <- 0 until n/2) {
        if(numC(i) == 'x' && change > 0) {
          numC(i) = '9'; numC(n - i -1) = '9'; change -= 1
        } else if(numC(i) != '9' && change > 1) {
          numC(i) = '9'; numC(n - i -1) = '9'; change -= 2
        }
      }
      for (i <- 0 until n/2) {
        if(numC(i) == 'x') {
          if(num(i) > num(n - i -1)) {numC(n - i -1) = num(i)}
          numC(i) = numC(n - i -1)
        }
      }

      
      if (change < 0) {print("-1")}
      else if (change > 0 && n % 2 != 0) {numC(n/2) = '9'; print(numC.mkString)}
      else {print(numC.mkString)}
    }
}