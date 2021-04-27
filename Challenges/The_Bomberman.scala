/*
The Bomberman
----------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/bomber-man
----------------------------

Bomberman lives in a rectangular grid. Each cell in the grid either contains a bomb or nothing at all.

Each bomb can be planted in any cell of the grid but once planted, it will detonate after exactly 3 seconds. Once a bomb detonates, it's destroyed — along with anything in its four neighboring cells. This means that if a bomb detonates in cell i,j, any valid cells (i+-1,j) and (i,j+-1) are cleared. If there is a bomb in a neighboring cell, the neighboring bomb is destroyed without detonating, so there's no chain reaction.

Bomberman is immune to bombs, so he can move freely throughout the grid. Here's what he does:

Initially, Bomberman arbitrarily plants bombs in some of the cells, the initial state.
After one second, Bomberman does nothing.
After one more second, Bomberman plants bombs in all cells without bombs, thus filling the whole grid with bombs. No bombs detonate at this point.
After one more second, any bombs planted exactly three seconds ago will detonate. Here, Bomberman stands back and observes.
Bomberman then repeats steps 3 and 4 indefinitely.

Note that during every second Bomberman plants bombs, the bombs are planted simultaneously (i.e., at the exact same moment), and any bombs planted at the same time will detonate at the same time.

Given the initial configuration of the grid with the locations of Bomberman's first batch of planted bombs, determine the state of the grid after N seconds.

For example, if the initial grid looks like:

...
.O.
...

it looks the same after the first second. After the second second, Bomberman has placed all his charges:

OOO
OOO
OOO

At the third second, the bomb in the middle blows up, emptying all surrounding cells:

O.O
...
O.O

Function Description

Complete the bomberMan function in the editory below.

bomberMan has the following parameter(s):

int n: the number of seconds to simulate
string grid[r]: an array of strings that represents the grid

Returns

string[r]: n array of strings that represent the grid in its final state

Input Format

The first line contains three space-separated integers r, c, and n, The number of rows, columns and seconds to simulate.
Each of the next r lines contains a row of the matrix as a single string of c characters. The . character denotes an empty cell, and the O character (ascii 79) denotes a bomb.

Sample Input

STDIN           Function
-----           --------
6 7 3           r = 6, c = 7, n = 3
.......         grid =['.......', '...O...', '....O..',\
...O...                '.......', 'OO.....', 'OO.....']
....O..
.......
OO.....
OO.....

Sample Output

OOO.OOO
OO...OO
OOO...O
..OO.OO
...OOOO
...OOOO

Explanation

The initial state of the grid is:

.......
...O...
....O..
.......
OO.....
OO.....

Bomberman spends the first second doing nothing, so this is the state after 1 second:

.......
...O...
....O..
.......
OO.....
OO.....

Bomberman plants bombs in all the empty cells during his second second, so this is the state after 2 seconds:

OOOOOOO
OOOOOOO
OOOOOOO
OOOOOOO
OOOOOOO
OOOOOOO

In his third second, Bomberman sits back and watches all the bombs he planted 3 seconds ago detonate. This is the final state after 3 seconds:

OOO.OOO
OO...OO
OOO...O
..OO.OO
...OOOO
...OOOO

*/

(Scala Solution)

object Solution {

    def main(args: Array[String]) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
*/
		val sc = new java.util.Scanner(System.in)

		val height = sc.nextInt()
		val width = sc.nextInt()
		val n = sc.nextInt()

		val field = Array.ofDim[Boolean](height, width)

		for(i <- 0 until height) {
			val fieldLine = sc.next();
			for( j <- 0 until width) {
				field(i)(j) = fieldLine(j) == 'O'
			}
		}

		// three states: initial, full, inverted, full, initial, full, inverted...

		val finalState = (n - 1) % 4

		if(finalState == 1 || finalState == 3) // We do the full states
		{
			val emptyField = Array.ofDim[Boolean](height, width)
			val fullField = invertField(emptyField)
			printField(fullField)

		} else if(finalState == 0 && n > 4) // We do the initial state inverted twice
		{
			val doubleInvertedField = invertField(invertField(field));
			printField(doubleInvertedField)

		}  else if(finalState == 0) // We do the initial state
		{
			printField(field)

		}else { // We do the inverted state
			val invertedField = invertField(field);
			printField(invertedField)
		}
    }

    def invertField(field : Array[Array[Boolean]] ) = {
    	val target = Array.ofDim[Boolean](field.size, field(0).size);
    	for(i <- 0 until target.size) {
    		for(j <- 0 until target(i).size) {
    			if(hasTrueNeighbour(i, j, field))
    				target(i)(j) = false
    			else 
    				target(i)(j) = true
    		}
    	}
    	target;
    }

    def hasTrueNeighbour(y: Int, x : Int, field: Array[Array[Boolean]]) : Boolean = {
    	val response = if(y > 0 && field(y-1)(x))
    		true
    	else if(x > 0 && field(y)(x-1)) 
    		true
    	else if(y < field.size - 1 && field(y+1)(x))
    		true
    	else if(x < field(y).size - 1 && field(y)(x+1))
    		true
    	else if(field(y)(x))
    		true
    	else 
    		false

    	response
    }

    def printField(field : Array[Array[Boolean]] ) = {
    	for(i <- 0 until field.size) {
    		for(j <- 0 until field(i).size) {
    			if(field(i)(j)) print('O') else print('.')
    		}
    		println("");
    	}
    }
}