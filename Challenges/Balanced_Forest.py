"""
Balanced Forest
-------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/balanced-forest
-------------------------------------------

Greg has a tree of nodes containing integer data. He wants to insert a node with some non-zero integer value somewhere into the tree. His goal is to be able to cut two edges and have the values of each of the three new trees sum to the same amount. This is called a balanced forest. Being frugal, the data value he inserts should be minimal. Determine the minimal amount that a new node can have to allow creation of a balanced forest. If it's not possible to create a balanced forest, return -1.

For example, you are given node values c=[15,12,8,14,13] and edges=[[1,2],1,3],[1,4],[4,5]]. It is the following tree:

								(1,15)
								/  |  \
							   /   |   \
						  (2,12) (3,8) (4,14)
										 |
										 |
									   (5,13)
									   
The blue node is root, the first number in a node is node number and the second is its value. Cuts can be made between nodes 1 and 3 and nodes 1 and 4 to have three trees with sums 27, 27 and 8. Adding a new node w of c[w]=19 to the third tree completes the solution.

Function Description

Complete the balancedForest function in the editor below. It must return an integer representing the minimum value of c[w] that can be added to allow creation of a balanced forest, or -1 if it is not possible.

balancedForest has the following parameter(s):

c: an array of integers, the data values for each node
edges: an array of 2 element arrays, the node pairs per edge
Input Format

The first line contains a single integer, q, the number of queries.

Each of the following q sets of lines is as follows:

The first line contains an integer, n, the number of nodes in the tree.
The second line contains n space-separated integers describing the respective values of c[1],c[2],...,c[n], where each c[i] denotes the value at node i.
Each of the following n-1 lines contains two space-separated integers, x[j] and y[j], describing edge j connecting nodes x[j] and y[j].

Output Format

For each query, return the minimum value of the integer c[w]. If no such value exists, return -1 instead.

Sample Input

2
5
1 2 2 1 1
1 2
1 3
3 5
1 4
3
1 3 5
1 3
1 2

Sample Output

2
-1

Explanation

We perform the following two queries:

The tree initially looks like this:
						(1,1)
					   /  |  \
					  /   |   \
				  (2,2) (3,2) (4,1)
				          |
						  |
						(5,1)
						  
Greg can add a new node w=6 with c[w]=2 and create a new edge connecting nodes 4 and 6. Then he cuts the edge connecting nodes 1 and 4 and the edge connecting nodes 1 and 3. We now have a three-tree balanced forest where each tree has a sum of 3.
						
						(1,1)
					   /  |  \
					  /   |   \
				  (2,2) (3,2) (4,1)
				          |     |
						  |     |
						(5,1) (w=6,2)

In the second query, it's impossible to add a node in such a way that we can split the tree into a three-tree balanced forest so we return -1.

"""

(Python Solution))

import math
import copy
import sys
sys.setrecursionlimit(60000)


def DFS_Recursive(graph,currentNode,splitGraph,totalCoins,explored_global):
    explored_global[currentNode] = True
    if(currentNode not in graph):
        return
    totSplitValue = coins[currentNode-1]
    for node in graph[currentNode]:
        if(node not in explored_global):
            splitValue = DFS_Recursive(graph,node,splitGraph,totalCoins,explored_global)
            if(splitValue in splitGraph):
                splitGraph[splitValue].append([node,currentNode])
                splitGraph[totalCoins-splitValue].append([currentNode,node])
            else:
                splitGraph[splitValue] = [[node,currentNode]]
                splitGraph[totalCoins-splitValue] = [[currentNode,node]]
            totSplitValue = totSplitValue + splitValue
    return totSplitValue

def FindLeaf(graph,node):
    explored = {}
    toContinue = True
    while(toContinue):
        toContinue = False
        for n in graph[node]:
            if(n in explored):
                continue
            else:
                node = n
                explored[n] = True
                toContinue = True
                break
        
    return node
    
def FindOutput(graph,coins):
    totalCoins = sum(coins)
    idealSplit = math.ceil(totalCoins/3)
    outOfBoundSplit = math.ceil(totalCoins/2)
    
    leafNode = FindLeaf(graph,1)
    splitGraphFirst = {}
    explored_global = {}
    DFS_Recursive(graph,leafNode,splitGraphFirst,totalCoins,explored_global)
    splitGraphFirst[totalCoins] = True
    explored_global = {}
    smallestChosenSplit = totalCoins+1
    for currentSplit in splitGraphFirst:
        if(currentSplit >= idealSplit and currentSplit <= outOfBoundSplit):
            if(currentSplit>smallestChosenSplit):
                continue
            if(len(splitGraphFirst[currentSplit]) > 1):
                if(currentSplit < smallestChosenSplit):
                    smallestChosenSplit = currentSplit
            if 2*currentSplit not in splitGraphFirst:
                continue
            pendingGraph = copy.deepcopy(graph)
            fromEdge,toEdge = splitGraphFirst[currentSplit][0]
            pendingGraph[fromEdge].remove(toEdge)
            pendingGraph[toEdge].remove(fromEdge)
            
            leafNode = FindLeaf(pendingGraph,toEdge)
            splitGraphSecond = {}
            explored_global = {}
            newTotalCoins = totalCoins - currentSplit
            splitGraphSecond[newTotalCoins] = True
            DFS_Recursive(pendingGraph,leafNode,splitGraphSecond,newTotalCoins,explored_global)
            explored_global = {}
            if(currentSplit in splitGraphSecond):
                if(currentSplit < smallestChosenSplit):
                    smallestChosenSplit = currentSplit
    if(smallestChosenSplit == totalCoins+1):
        return -1
    else:
        return smallestChosenSplit
    


numberOfTrees = int(input())

for t in range(numberOfTrees):
    numberOfNodes = int(input())
    coins = [int(c) for c in input().strip().split()]
    graph = {}
    for n in range(numberOfNodes-1):
        firstNode,secondNode = [int(n) for n in input().strip().split()]
        if(firstNode in graph):
            graph[firstNode].append(secondNode)
        else:
            graph[firstNode] = [secondNode]
        
        if(secondNode in graph):
            graph[secondNode].append(firstNode)
        else:
            graph[secondNode] = [firstNode]
    
    chosenSplit = 0
    if(len(graph) > 0):
        chosenSplit = FindOutput(graph,coins)
    extraCoin = -1
    if(chosenSplit > 0):
        extraCoin = 3*chosenSplit - sum(coins)
        
    print(extraCoin)