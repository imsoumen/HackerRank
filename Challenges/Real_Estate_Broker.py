"""
Real Estate Broker
----------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/real-estate-broker
----------------------------

You are a real estate broker in ancient Knossos. You have m unsold houses, and each house j has an area, x<j>, and a minimum price, y<j>. You also have n clients, and each client i wants a house with an area greater than a<i> and a price less than or equal to p<i>.

Each client can buy at most one house, and each house can have at most one owner. What is the maximum number of houses you can sell?

Input Format

The first line contains two space-separated integers describing the respective values of n (the number of clients) and m (the number of houses).
Each line i of the n subsequent lines contains two space-separated integers describing the respective values of a<i> and p<i> for client i.
Each line j of the m subsequent lines contains two space-separated integers describing the respective values of x<j> and y<j> for house j.

Output Format

Print a single integer denoting the maximum number of houses you can sell.

Sample Input 0

3 3
5 110
9 500
20 400
10 100
2 200
30 300

Sample Output 0

2

Explanation 0

Recall that each client i is only interested in some house j where x<j> > a<j> and y<j> <= p<i>. 


Client 0 will be interested in house 0 because it has more than a<0>=5 units of space and costs less than p<0>=110. Both of the other houses are outside of this client's price range.
Client 1 will be interested in houses 0 and 2, as both these houses have more than a<1> = 9 units of space and cost less than p<1>=500. They will not be interested in the remaining house because it's too small.
Client 2 will be interested in house 2 because it has more than a<2>=20 units of space and costs less than p<2>=400. They will not be interested in the other two houses because they are too small.
All three clients are interested in the same two houses, so you can sell at most two houses in the following scenarios:

Client 0 buys house 0 and client 1 buys house 2.
Client 1 buys house 0 and client 2 buys house 2.
Client 2 buys house 0 and client 3 buys house 2.
Thus, we print the maximum number of houses you can sell, 2, on a new line.

"""
(Scala Solution)
import scala.collection.mutable

case class Client(minArea: Int, maxPrice: Int)
case class House(area: Int, minPrice: Int)

case object HouseOrdering extends Ordering[House] {
    def compare(h1: House, h2: House): Int = {
        if (h1.area < h2.area) {
            -1
        } else if (h1.area == h2.area) {
            if (h1.minPrice < h2.minPrice) {
                -1
            } else if (h1.minPrice == h2.minPrice) {
                0
            } else {
                1
            }
        } else {
            1
        }
    }
}

object Solution {


    def main(args: Array[String]) {
        val input = scala.io.Source.stdin.getLines()
        val Array(n, m) = input.next().split(" ").map(_.toInt)
        val clients = Iterator.range(0, n).map(_ => {
            val Array(ai, pi) = input.next().split(" ").map(_.toInt)
            Client(ai, pi)
        })
        val remainingClients = new mutable.ArrayBuffer[Client]()
        clients.foreach(remainingClients += _)
        
        val houses = Iterator.range(0, m).map(_ => {
            val Array(xj, yj) = input.next().split(" ").map(_.toInt)
            House(xj, yj)
        }).toVector.sorted(HouseOrdering)
        
        val result = houses.foldLeft(0){
            case (acc, House(area, minPrice)) => 
                val buyers = remainingClients.iterator.filter{ 
                    case Client(minArea, maxPrice) => area > minArea && minPrice <= maxPrice
                }
                val client = if (buyers.hasNext) {
                  Some(buyers.minBy(_.maxPrice))
                } else {
                  None
                }
                client.map(c => { remainingClients -= c; acc + 1}).getOrElse(acc)
        }
        
        println(result)
    }
}


(Python Solution)

#!/bin/python3

import os
import sys

#
# Complete the realEstateBroker function below.
#
def realEstateBroker(clients, houses):
    #
    # Write your code here.
    #
    honspace = sorted(houses, reverse=1)
    conspace = sorted(clients, reverse=1)
    count = 0
    cl = len(clients) - 1;
    for i,c in enumerate(conspace):
        maxhp = -1
        maxhi = -1
        for j,h in enumerate(honspace):
            if c[0]<h[0] and h[1] <= c[1]:
                if maxhp < h[1]:
                    maxhp = h[1]
                    maxhi = j
        if maxhi > -1:
            count += 1
            del honspace[maxhi]
                
    return count
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nm = input().split()

    n = int(nm[0])

    m = int(nm[1])

    clients = []

    for _ in range(n):
        clients.append(list(map(int, input().rstrip().split())))

    houses = []

    for _ in range(m):
        houses.append(list(map(int, input().rstrip().split())))

    result = realEstateBroker(clients, houses)

    fptr.write(str(result) + '\n')

    fptr.close()
