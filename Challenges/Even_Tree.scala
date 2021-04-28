/*
Even Tree
---------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/even-tree
---------------------------------------

You are given a tree (a simple connected graph with no cycles).

Find the maximum number of edges you can remove from the tree to get a forest such that each connected component of the forest contains an even number of nodes.

As an example, the following tree with 4 nodes can be cut at most 1 time to create an even forest.

		(1)-------(2)
		  \
		   \
		    \
			(3)-------(4)
			
Function Description

Complete the evenForest function in the editor below. It should return an integer as described.

evenForest has the following parameter(s):

t_nodes: the number of nodes in the tree
t_edges: the number of undirected edges in the tree
t_from: start nodes for each edge
t_to: end nodes for each edge, (Match by index to t_from.)

Input Format

The first line of input contains two integers t_nodes and t_edges, the number of nodes and edges.
The next t_edges lines contain two integers t_from[i] and t_to[i] which specify nodes connected by an edge of the tree. The root of the tree is node 1.

Output Format

Print the number of removed edges.

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
//import scala.collection.parallel.immutable._
//import scala.collection.parallel.mutable._
import scala.concurrent._
import scala.io._
import scala.math._
import scala.sys._
import scala.util.matching._
import scala.reflect._

object Solution {

    // Complete the evenForest function below.
    def evenForest(tNodes: Int, tEdges: Int, tFrom: Array[Int], tTo: Array[Int]): Int = {
        val arr = Array.fill(tNodes + 1)(scala.List.empty[Int])
        val used = Array.fill(tNodes + 1)(false)

        tFrom.zip(tTo).foreach{ case (f,t) =>
          arr(f) = t :: arr(f)
          arr(t) = f :: arr(t)
        }

        def dfs(x: Int):(Int,Int) = {
          used(x) = true
          arr(x).filterNot(el => used(el)) match {
            case scala.List() => (0,0)
            case childs =>
               childs.foldLeft((0,0))({case ((forestsCount,nodesCount),ch) =>
                var (count,nodeCount) = dfs(ch)
                 nodeCount = nodeCount + 1
                if (nodeCount % 2 == 0) (forestsCount + 1 + count,nodesCount)
                else (forestsCount + count,nodesCount + nodeCount)
              })
          }
        }
        return dfs(1)._1

    }

    def main(args: Array[String]) {
        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val Array(tNodes, tEdges) = StdIn.readLine().replaceAll("\\s+$", "").split(" ").map(_.toInt)

        val tFrom = Array.ofDim[Int](tEdges)
        val tTo = Array.ofDim[Int](tEdges)

        for (i <- 0 until tEdges) {
            val tFromTo = StdIn.readLine().replaceAll("\\s+$", "").split(" ")

            tFrom(i) = tFromTo(0).toInt
            tTo(i) = tFromTo(1).toInt
        }

        val res = evenForest(tNodes, tEdges, tFrom, tTo)

        printWriter.println(res)

        printWriter.close()
    }
}
