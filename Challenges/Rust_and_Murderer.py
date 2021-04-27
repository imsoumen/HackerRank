"""
Rust & Murderer
------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/rust-murderer
------------------------------------------

Detective Rust is investigating a homicide and he wants to chase down the murderer. The murderer knows he would definitely get caught if he takes the main roads for fleeing, so he uses the village roads (or side lanes) for running away from the crime scene.

Rust knows that the murderer will take village roads and he wants to chase him down. He is observing the city map, but it doesn't show the village roads (or side lanes) on it and shows only the main roads.

The map of the city is a graph consisting N nodes (labeled 1 to N) where a specific given node S represents the current position of Rust and the rest of the nodes denote other places in the city, and an edge between two nodes is a main road between two places in the city. It can be suitably assumed that an edge that doesn't exist/isn't shown on the map is a village road (side lane). That means, there is a village road between two nodes a and b iff(if and only if) there is no city road between them.

In this problem, distance is calculated as number of village roads (side lanes) between any two places in the city.

Rust wants to calculate the shortest distance from his position (Node S) to all the other places in the city if he travels only using the village roads (side lanes).

Note: The graph/map of the city is ensured to be a sparse graph.

Input Format

The first line contains T, denoting the number of test cases. T testcases follow.
First line of each test case has two integers N, denoting the number of cities in the map and M, denoting the number of roads in the map.
The next M lines each consist of two space-separated integers x and y denoting a main road between city x and city y. The last line has an integer S, denoting the current position of Rust.

Note

No nodes will have a road to itself.
There will not be multiple edges between any pair of nodes i.e. there is at most one undirected edge between them.
Graph is guaranteed to be sparse.
It is guranteed that there will be a path between any pair of nodes using the side lanes.

Output Format

For each of T test cases, print a single line consisting of N-1 space separated integers, denoting the shortest distances of the remaining N-1 places from Rust's position (that is all distances, except the source node to itself) using the village roads/side lanes in ascending order based on vertex number.

Sample Input 0

2
4 3
1 2
2 3
1 4
1
4 2
1 2
2 3
2

Sample Output 0

3 1 2
2 2 1

Explanation 0

The graph in the first testcase can be shown as:

       S (1) ---------- (4)
            \
             \
              \
               \
                (2) ---------- (3)

Here the source node is 1 (marked S).
The distance from 1 to 2 is 3. Path: 1 -> 3 -> 4 -> 2
The distance from 1 to 3 is 1. Path: 1 -> 3
The distance from 1 to 4 is 2. Path: 1 -> 3 -> 4

"""




#!/bin/python3

import os
import sys

T = int(input())

def bfs(graph,start,citiesLen):
    queue = [(start,0)]
    
    notvisited = set((x for x in range(1,citiesLen+1)))
    cities = ['0' for x in range(citiesLen+1)]
    i = 0
    c = 1
    while i < len(queue):
        (vertex,depth) = queue[i]
        notvisited.discard(vertex)
        visited = set()
        for next in notvisited:
            if(cities[next] == '0' and next not in graph[vertex]):
                cities[next] = str(depth + 1)
                visited.add(next)
                c+=1
                queue.append((next, depth + 1))
        notvisited = notvisited-visited
        i+=1 
        if(c == citiesLen+1):
            break
        
   
    return cities[1:start]+cities[start+1:]
    

for case in range(T):
    cities,roads = [int(x) for x in input().split()]
    graph = [0]
    for c in range(1,cities+1):
        graph.append(set())
        
    for r in range(roads):
        x,y = map(int,input().split())
       
        graph[x].add(y)
      
        graph[y].add(x)
    start = int(input())
    res = []
    print(' '.join(bfs(graph,start,cities)))