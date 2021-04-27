/*
Roads and Libraries
---------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/torque-and-development
---------------------------------------

Determine the minimum cost to provide library access to all citizens of HackerLand. There are n cities numbered from 1 to n. Currently there are no libraries and the cities are not connected. Bidirectional roads may be built between any city pair listed in cities. A citizen has access to a library if:

Their city contains a library.
They can travel by road from their city to a city containing a library.

Example

The following figure is a sample map of HackerLand where the dotted lines denote possible roads:


c_road=2
c_lib=3
cities=[[1,7],[1,3],[1,2],[2,3],[5,6],[6,8]]


The cost of building any road is cc_road=2, and the cost to build a library in any city is c_lib=3. Build 5 roads at a cost of 5x2=10 and 2 libraries for a cost of 6. One of the available roads in the cycle 1 -> 2 -> 3 -> 1 is not necessary.

There are q queries, where each query consists of a map of HackerLand and value of c_lib and c_road. For each query, find the minimum cost to make libraries accessible to all the citizens.

Function Description

Complete the function roadsAndLibraries in the editor below.
roadsAndLibraries has the following parameters:

int n: integer, the number of cities
int c_lib: integer, the cost to build a library
int c_road: integer, the cost to repair a road
int cities[m][2]: each cities[i] contains two integers that represent cities that can be connected by a new road

Returns
- int: the minimal cost

Input Format

The first line contains a single integer q, that denotes the number of queries.

The subsequent lines describe each query in the following format:
- The first line contains four space-separated integers that describe the respective values of n, m, c_lib and c_road, the number of cities, number of roads, cost of a library and cost of a road.
- Each of the next m lines contains two space-separated integers, u[i] and v[i], that describe a bidirectional road that can be built to connect cities u[i] and v[i].

Sample Input

STDIN       Function
-----       --------
2           q = 2
3 3 2 1     n = 3, cities[] size m = 3, c_lib = 2, c_road = 1
1 2         cities = [[1, 2], [3, 1], [2, 3]]
3 1
2 3
6 6 2 5     n = 6, cities[] size m = 6, c_lib = 2, c_road = 5
1 3         cities = [[1, 3], [3, 4],...]
3 4
2 4
1 2
2 3
5 6

Sample Output

4
12

Explanation

Perform the following q=2 queries:

HackerLand contains n=3 cities and can be connected by m=3 bidirectional roads. The price of building a library is c<lib>=2 and the price for repairing a road is c<road>=1.

The cheapest way to make libraries accessible to all is to:

Build a library in city 1 at a cost of x=2.
Build the road between cities 1 and 2 at a cost of y=1.
Build the road between cities 2 and 3 at a cost of y=1.
This gives a total cost of 2+1+1=4. Note that the road between cities 3 and 1 does not need to be built because each is connected to city 2.

In this scenario it is optimal to build a library in each city because the cost to build a library is less than the cost to build a road.

There are 6 cities, so the total cost is 6x2=12.


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
import scala.collection.immutable._
import scala.collection.mutable._
import scala.collection.concurrent._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

object Solution {

    def roadsAndLibraries(n: Int, c_lib: Int, c_road: Int, cities: Array[Array[Int]]): Long =  {
            // Complete this function
        if(c_lib < c_road) c_lib.toLong * n
        else {
            val visited: scala.collection.mutable.Set[Int] = scala.collection.mutable.Set()
            val adj: scala.collection.mutable.Map[Int, scala.collection.Set[Int]] = scala.collection.mutable.Map()
            (1 to n) foreach { i =>
                adj(i) = scala.collection.Set[Int]()
            }
            for ( cityPair <- cities 
               ) {
                  val city1 = cityPair(0)
                  val city2 = cityPair(1)
                adj(city1) += city2
                adj(city2) += city1
            }
            //println(adj)
            def dfs(root: Int): Boolean = {
                if(!visited.contains(root)) {
                    visited += root
                    for(n <- adj(root)) {
                        dfs(n)
                    }
                    true
                } else {
                    false
                }
            }
            val ConnComp = (1 to n).filter(dfs)
            //println(s"visited: $visited")
            //println(ConnComp)
            c_lib.toLong * ConnComp.size + c_road.toLong * (n - ConnComp.size)
        }
            
    }

    def main(args: Array[String]) {
        val sc = new java.util.Scanner (System.in);
        var q = sc.nextInt();
        var a0 = 0;
        while(a0 < q){
            var n = sc.nextInt();
            var m = sc.nextInt();
            var c_lib = sc.nextInt();
            var c_road = sc.nextInt();
            var cities = Array.ofDim[Int](m,2);
            for(cities_i <- 0 to m-1) {
               for(cities_j <- 0 to 2-1){
                  cities(cities_i)(cities_j) = sc.nextInt();
               }
            }
            val result = roadsAndLibraries(n, c_lib, c_road, cities);
            println(result)
            a0+=1;
        }
    }
}
