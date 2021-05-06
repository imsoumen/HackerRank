/*
Direct Connections
-------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/direct-connections
-------------------------------

Enter-View (EV) is a linear, street-like country. By linear, we mean all the cities of the country are placed on a single straight line - the x-axis. Thus every city's position can be defined by a single coordinate, xi, the distance from the left borderline of the country. You can treat all cities as single points.

Unfortunately, the dictator of telecommunication of EV (Mr. S. Treat Jr.) doesn't know anything about the modern telecom technologies, except for peer-to-peer connections. Even worse, his thoughts on peer-to-peer connections are extremely faulty: he believes that, if Pi people are living in city i, there must be at least Pi cables from city i to every other city of EV - this way he can guarantee no congestion will ever occur!

Mr. Treat hires you to find out how much cable they need to implement this telecommunication system, given the coordination of the cities and their respective population.

Note that The connections between the cities can be shared. Look at the example for the detailed explanation.

Input Format

A number T is given in the first line and then comes T blocks, each representing a scenario.

Each scenario consists of three lines. The first line indicates the number of cities (N). The second line indicates the coordinates of the N cities. The third line contains the population of each of the cities. The cities needn't be in increasing order in the input.

Output Format

For each scenario of the input, write the length of cable needed in a single line modulo 10^9+7.

Sample Input

2  
3  
1 3 6  
10 20 30  
5  
5 55 555 55555 555555  
3333 333 333 33 35

Sample Output

280  
463055586

Explanation

For the first test case, having 3 cities requires 3 sets of cable connections. Between city 1 and 2, which has a population of 10 and 20, respectively, Mr. Treat believes at least 10 cables should come out of city 1 for this connection, and at least 20 cables should come out of city 2 for this connection. Thus, the connection between city 1 and city 2 will require 20 cables, each crossing a distance of 3-1=2km. Applying this absurd logic to connection 2,3 and 1,3, we have

[1,2] => 20 connections x 2km = 40km of cable

[2,3] => 30 connections x 3km = 90km of cable

[1,3] => 30 connections x 5km = 150km of cable

For a total of 280 , Output is 280 km of cable

*/
(Scala Solution)

import collection.mutable.HashMap
import scala.io.StdIn._

class BITree(val n: Int) {
  val tree = HashMap[Int, Long]()

  def update(i: Int, v: Int) {
    if (i == 0) tree(0) = tree.getOrElse(0, 0L) + v
    else {
      var idx = i
      while (idx <= n) {
        tree(idx) = tree.getOrElse(idx, 0L) + v
        idx += idx & -idx
      }
    }
  }

  def apply(i: Int) = {
    var idx = i
    var sum = tree.getOrElse(0, 0L)
    while (idx > 0) {
      sum += tree.getOrElse(idx, 0L)
      idx -= idx & -idx
    }
    sum
  }
}

object Solution {
  def main(args: Array[String]): Unit = {
    val t = readInt()
    val m = 1000000007
    val maxCoord = 1000000000
    for (_ <- 0 until t) {
      val n = readInt()
      val nCities = new BITree(maxCoord)
      val sumPositions = new BITree(maxCoord)
      val coords = readLine().split(" ").map(_.toInt)
      val pops = readLine().split(" ").map(_.toInt)
      val sorted = pops.zip(coords).sortBy(_._1)
      var res = 0L
      for ((p, i) <- sorted) {
        val nLeft = nCities(i)
        val nRight = nCities(maxCoord) - nLeft
        val sLeft = sumPositions(i)
        val sRight = sumPositions(maxCoord) - sLeft
        res = (res + (p * (nLeft * i - sLeft) % m) + (p * (sRight - nRight * i) % m)) % m
        nCities(i) = 1
        sumPositions(i) = i
      }
      println(res)
    }
  }
}