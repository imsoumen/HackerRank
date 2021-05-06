/*
Jack goes to Rapture
-------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/jack-goes-to-rapture
-------------------------------------

Jack has just moved to a new city called Rapture. He wants to use the public public transport system. The fare rules are as follows:

Each pair of connected stations has a fare assigned to it regardless of direction of travel.
If Jack travels from station A to station B, he only has to pay the difference between (the fare from A to B) and (the cumulative fare paid to reach station A), [fare(A,B) - total fare to reach station A]. If the difference is negative, travel is free of cost from A to B.
Jack is low on cash and needs your help to figure out the most cost efficient way to go from the first station to the last station. Given the number of stations g_nodes (numbered from 1 to g_nodes), and the fares (weights) between the g_edges pairs of stations that are connected, determine the lowest fare from station 1 to station g_nodes.

Example
g_nodes=4
g_from = [1,1,2,3]
g_to = [2,3,4,4]
g_weight = [20,5,30,40]

The graph looks like this:

			   20
		(1) --------- (2)
		 \			   \
		5 \				\ 30
		   \			 \
		   (3)------------(4)
					40
		
Travel from station 1 -> 2 -> 4 costs 20 for the first segment (1 -> 2) then the cost differential, an additional 30 - 20 = 10 for the remainder. The total cost is 30.

Travel from station 1 -> 3 -> 4 costs 5 for the first segment, then an additional 40-5 = 35 for the remainder, a total cost of 40.

The lower priced option costs 30.

Function Description

Complete the getCost function in the editor below.

getCost has the following parameters:

int g_nodes: the number of stations in the network
int g_from[g_edges]: end stations of a bidirectional connection
int g_to[g_edges]: g_from[i] is connected to g_to[i] at cost g_weight[i] 
int g_weight[g_edges]: the cost of travel between associated stations
Prints
- int or string: the cost of the lowest priced route from station 1 to station g_nodes or NO PATH EXISTS. No return value is expected.

Input Format

The first line contains two space-separated integers, g_nodes and g_edges, the number of stations and the number of connections between them.
Each of the next g_edges lines contains three space-separated integers, g_from, g_to and g_weight, the connected stations and the fare between them.

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
import scala.collection.parallel.immutable._
import scala.collection.parallel.mutable._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

object Result {

    /*
     * Complete the 'getCost' function below.
     *
     * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */

    class PQ(nn: Int) {
        val n = nn
        var pq = Array.fill(n+1)(Tuple2(0,0L)) 
        var l = 0 // next element
        var position = Array.fill(n+1)(-1)

        def put(v: Tuple2[Int,Long]) {
            val i = position(v._1)
            if (i < 0){
                pq(l) = v
                position(v._1) = l
                l = l+1
                swim(l-1)
            }
            else if(pq(i)._2 > v._2 ){
                pq(i) = v
                swim(i)
            }
            //println("pq: ",pq.mkString(" "))
                
        }

        def pull() : Option[Tuple2[Int,Long]] = {
            if (l<=0){
                return Option(null)
            }
            val temp = pq(0)
            position(temp._1) = -1
            pq(0) = pq(l-1)
            l = l-1
            position(pq(0)._1) = 0
            sink(0)
            return(Option(temp))
        }

        def swim(i:Int){
            if(i>0 & i<l){
                val parent:Int = (i-1)/2 
                if (pq(parent)._2>pq(i)._2) {
                    val temp = pq(parent)
                    pq(parent) = pq(i)
                    pq(i) = temp
                    position(pq(parent)._1) = parent
                    position(pq(i)._1) = i
                    swim(parent)
                }
            }
        }

        def sink(i:Int){
            val ch1 = 2*i+1
            val ch2 = 2*i+2
            if (ch2 < l){
                if ((pq(ch1)._2<pq(i)._2)&(pq(ch1)._2<=pq(ch2)._2)){
                    var temp = pq(ch1)
                    pq(ch1) = pq(i)
                    pq(i) = temp
                    position(pq(ch1)._1) = ch1
                    position(pq(i)._1) = i
                    sink(ch1)
                }
                else if((pq(ch2)._2<pq(i)._2)&(pq(ch2)._2<pq(ch1)._2)){
                    var temp = pq(ch2)
                    pq(ch2) = pq(i)
                    pq(i) = temp
                    position(pq(ch2)._1) = ch2
                    position(pq(i)._1) = i
                    sink(ch2)
                }
            }
            else if(ch1 < l){
                if (pq(ch1)._2 < pq(i)._2){
                    var temp = pq(ch1)
                    pq(ch1) = pq(i)
                    pq(i) = temp
                    position(pq(ch1)._1) = ch1
                    position(pq(i)._1) = i
                    sink(ch1)
                }
            }
        }
    }

    def getCost(gNodes: Int,gEdges: Int,gFrom: Array[Int], gTo: Array[Int], gWeight: Array[Int]) {

        var graph = Array.fill(gNodes+1)(new scala.collection.mutable.ListBuffer[Tuple2[Int,Int]])
        var i = 0
        for(i <- 0 until gEdges){
            graph(gFrom(i)) += Tuple2(gTo(i),gWeight(i))
            graph(gTo(i)) += Tuple2(gFrom(i),gWeight(i))
        }
        var temp = Tuple2(0,0L)

        var pq = new PQ(gNodes)
        for(i <- 1 to gNodes){ 
            pq.put(Tuple2(i,1+500000*10000000L))
        }
        pq.put(Tuple2(1,0L))
        var result = "NO PATH EXISTS"
        var visited = Array.fill(gNodes+1)(false)
        while(pq.l>0){
            var v = pq.pull().get
            visited(v._1) = true
            if (v._2==1+500000*10000000L){
                print("NO PATH EXISTS")
                return
            }
            else if(v._1==gNodes){
                result = v._2.toString
                print(result)
                return
            }
            for(temp <- graph(v._1)){
                if(!visited(temp._1)){
                    //println("pushing: ",Tuple2(temp._1,v._2+scala.math.max(temp._2-v._2,0)))
                    pq.put(Tuple2(temp._1,v._2+scala.math.max(temp._2-v._2,0)))
                }
            }

        }
    }

}

object Solution {
    def main(args: Array[String]) {
        val Array(gNodes, gEdges) = StdIn.readLine().replaceAll("\\s+$", "").split(" ").map(_.toInt)

        val gFrom = Array.ofDim[Int](gEdges)
        val gTo = Array.ofDim[Int](gEdges)
        val gWeight = Array.ofDim[Int](gEdges)

        for (i <- 0 until gEdges) {
            val gFromToWeight = StdIn.readLine().replaceAll("\\s+$", "").split(" ")

            gFrom(i) = gFromToWeight(0).toInt
            gTo(i) = gFromToWeight(1).toInt
            gWeight(i) = gFromToWeight(2).toInt
        }

        Result.getCost(gNodes, gEdges, gFrom, gTo, gWeight)
    }
}