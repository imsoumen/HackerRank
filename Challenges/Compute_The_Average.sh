"""
Compute The Average
------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/bash-tutorials---compute-the-average
------------------------------------

Given N integers, compute their average, rounded to three decimal places.

Input Format
The first line contains an integer, N.
Each of the following N lines contains a single integer.

Output Format
Display the average of the N integers, rounded off to three decimal places.

Sample Input

4
1
2
9
8

Sample Output

5.000

Explanation
The '4' in the first line indicates that there are four integers whose average is to be computed.

The average = (1 + 2 + 9 + 8)/4 = 20/4 = 5.000 (correct to three decimal places).

Please include the zeroes even if they are redundant (e.g. 0.000 instead of 0).

"""

(Bash Solution)

read N
sum=0
count=0
while [ $N -gt $count ]; do
    read number
    sum=$(($sum+$number))
    count=$(($count+1))
done

printf "%.3f" $(echo "$sum / $N" | bc -l)