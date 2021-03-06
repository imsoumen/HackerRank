/*
Bike Racers
----------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/bike-racers
----------------------------------------

There are N bikers present in a city (shaped as a grid) having M bikes. All the bikers want to participate in the HackerRace competition, but unfortunately only K bikers can be accommodated in the race. Jack is organizing the HackerRace and wants to start the race as soon as possible. He can instruct any biker to move towards any bike in the city. In order to minimize the time to start the race, Jack instructs the bikers in such a way that the first K bikes are acquired in the minimum time.

Every biker moves with a unit speed and one bike can be acquired by only one biker. A biker can proceed in any direction. Consider distance between bikes and bikers as Euclidean distance.

Jack would like to know the square of required time to start the race as soon as possible.

Input Format

The first line contains three integers, N, M, and K, separated by a single space.
The following N lines will contain N pairs of integers denoting the co-ordinates of N bikers. Each pair of integers is separated by a single space. The next M lines will similarly denote the co-ordinates of the M bikes.

Output Format

A single line containing the square of required time.

Sample Input

3 3 2
0 1
0 2
0 3
100 1
200 2 
300 3
Sample Output

40000

Explanation
There's need for two bikers for the race. The first biker (0,1) will be able to reach the first bike (100,1) in 100 time units. The second biker (0,2) will be able to reach the second bike (200,2) in 200 time units. This is the most optimal solution and will take 200 time units. So output will be 200^2 = 40000.
*/


(Scala Solution)

object Solution extends App{
    import scala.io.StdIn.readLine
        
    val nmk = readLine.split(" ").map(_.toInt)
    val (n, m, k) = (nmk(0), nmk(1), nmk(2))   
        
    var bikers = new Array[(Long, Long)](n)
    for(i <- 0 until n){
        val xy = readLine.split(" ").map(_.toLong) 
        bikers(i) = (xy(0), xy(1))
    }
    
    var bikes = new Array[(Long, Long)](m)
    for(i <- 0 until m){
        val xy = readLine.split(" ").map(_.toLong) 
        bikes(i) = (xy(0), xy(1)) 
    }
    
    def EucliDistSq(xy1: (Long, Long), xy2: (Long, Long)) = {
        val (a, b) = (xy1._1-xy2._1, xy1._2-xy2._2)
        a*a + b*b
    }
    var distMatrix = Array.ofDim[Long](n, m)
    for(i <- 0 until n; j <- 0 until m) distMatrix(i)(j) = EucliDistSq(bikers(i), bikes(j))
    
    var bikeTaken = new Array[Boolean](m) // index = bike, value = taken/free status
    var bikeOfBiker = Array.fill[Int](n)(-1) // index = biker, value = bike
    var bikerInBike = Array.fill[Int](m)(-1) // index = bike, value = biker
        
    def dfsBikeAllotment(time: Long, i: Int): Boolean = { // ith Biker, jth Bike
        for(j <- 0 until m if distMatrix(i)(j) <= time && !bikeTaken(j)){
            bikeTaken(j) = true
            if(bikerInBike(j) < 0 || dfsBikeAllotment(time, bikerInBike(j))){
                bikerInBike(j) = i
                bikeOfBiker(i) = j
                return true
            }
        }
        return false
    }

    def Reachable(time: Long, k: Int): Boolean = {  //given the time can this many bikers reach to unique bikes
        bikeOfBiker.transform(x => -1)
        bikerInBike.transform(x => -1)
        var reached = 0
        
        for(i <- 0 until n if bikeOfBiker(i) < 0){ // if free biker
            bikeTaken.transform(x => false)
            if(dfsBikeAllotment(time, i)) reached += 1
            if(reached >= k) return true
        }
        return false
    }
    
    var l = 0L
    var r = Long.MaxValue
    while (l <= r) {
        val mid = l + (r-l)/2
        if (Reachable(mid, k)) r = mid-1 else l = mid+1
    }
    println(l);
}