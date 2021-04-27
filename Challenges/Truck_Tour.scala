/*
Truck Tour
-----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/truck-tour
-----------------------------------

Suppose there is a circle. There are N petrol pumps on that circle. Petrol pumps are numbered 0 to (N-1) (both inclusive). You have two pieces of information corresponding to each of the petrol pump: (1) the amount of petrol that particular petrol pump will give, and (2) the distance from that petrol pump to the next petrol pump.

Initially, you have a tank of infinite capacity carrying no petrol. You can start the tour at any of the petrol pumps. Calculate the first point from where the truck will be able to complete the circle. Consider that the truck will stop at each of the petrol pumps. The truck will move one kilometer for each litre of the petrol.

Input Format

The first line will contain the value of N.
The next N lines will contain a pair of integers each, i.e. the amount of petrol that petrol pump will give and the distance between that petrol pump and the next petrol pump.

Output Format

An integer which will be the smallest index of the petrol pump from which we can start the tour.

Sample Input

3
1 5
10 3
3 4

Sample Output

1

Explanation

We can start the tour from the second petrol pump.


*/

(Scala Solution)

import scala.io._
class TruckTour(input : Array[Int]) {
  val result = {
    var total = 0
    var min = 0 
    var index = 0 
    for (i <- 0 until input.length) {
      total += input(i)
      if (total < min) {
        min = total
        index = i + 1
      }
    }
    index
  }
}
object Solution {
  def read() : Array[Int] = {
    val s = Source.fromInputStream(System.in)
    val lines = s.getLines()
    val size = lines.next().toInt
    val toReturn = new Array[Int](size)
    for (i <- 1 to size) {
      val line = lines.next().split(" ").map { x => x.toInt }
      toReturn(i - 1) = line(0) - line(1)
    }
    return toReturn
  }
    def main(args: Array[String]) {
        val user = new TruckTour(read())
        println(user.result)
    }
}