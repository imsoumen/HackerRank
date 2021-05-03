/*
Draw the Triangle 1
---------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/draw-the-triangle-1
---------------------------------

P(R) represents a pattern drawn by Julia in R rows. The following pattern represents P(5):

* * * * * 
* * * * 
* * * 
* * 
*
Write a query to print the pattern P(20).

(Oracle Solution)

SELECT RPAD ('* ', (21-LEVEL)*2, '* ')
FROM DUAL
CONNECT BY LEVEL < 21;