"""
Find Merge Point of Two Lists
--------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/find-the-merge-point-of-two-joined-linked-lists
--------------------------------------

Given pointers to the head nodes of 2 linked lists that merge together at some point, find the node where the two lists merge. The merge point is where both lists point to the same node, i.e. they reference the same memory location. It is guaranteed that the two head nodes will be different, and neither will be NULL. If the lists share a common node, return that node's data value.

Note: After the merge point, both lists will share the same node pointers.

Example

In the diagram below, the two lists converge at Node x:

[List #1] a--->b--->c
                     \
                      x--->y--->z--->NULL
                     /
     [List #2] p--->q
	 
Function Description

Complete the findMergeNode function in the editor below.

findMergeNode has the following parameters:

SinglyLinkedListNode pointer head1: a reference to the head of the first list
SinglyLinkedListNode pointer head2: a reference to the head of the second list

Returns

int: the data value of the node where the lists merge

Input Format

Do not read any input from stdin/console.

The first line contains an integer t, the number of test cases.

Each of the test cases is in the following format:
The first line contains an integer, index, the node number where the merge will occur.
The next line contains an integer, list1count that is the number of nodes in the first list.
Each of the following list1count lines contains a data value for a node. The next line contains an integer, list2count that is the number of nodes in the second list.
Each of the following list2count lines contains a data value for a node.

Sample Input

The diagrams below are graphical representations of the lists that input nodes head1 and head2 are connected to.

Test Case 0

 1
  \
   2--->3--->NULL
  /
 1
 
Test Case 1

1--->2
      \
       3--->Null
      /
     1
     
Sample Output

2
3

Explanation

Test Case 0: As demonstrated in the diagram above, the merge node's data field contains the integer 2.
Test Case 1: As demonstrated in the diagram above, the merge node's data field contains the integer 3.

"""

(Python Solution)

def findMergeNode(head1, head2):
    currentA = head1;
    currentB = head2;

    while currentA != currentB:

        if currentA.next is None:
            currentA = head2
        else:
            currentA = currentA.next

        if currentB.next is None:
            currentB = head1
        else:
            currentB = currentB.next
            
    return currentB.data;