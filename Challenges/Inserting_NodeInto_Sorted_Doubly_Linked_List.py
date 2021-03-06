"""

Inserting a Node Into a Sorted Doubly Linked List
--------------------------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/insert-a-node-into-a-sorted-doubly-linked-list
--------------------------------------------------

Given a reference to the head of a doubly-linked list and an integer, data, create a new DoublyLinkedListNode object having data value data and insert it at the proper location to maintain the sort.

Example:
head refers to the list 1 <-> 2 <-> 4 <-> NULL
data = 3

Return a reference to the new list: 1 <-> 2 <-> 3 <-> 4 <-> NULL.

Function Description:
Complete the sortedInsert function in the editor below.

sortedInsert has two parameters:

DoublyLinkedListNode pointer head: a reference to the head of a doubly-linked list

int data: An integer denoting the value of the data field for the DoublyLinkedListNode you must insert into the list.

Returns

DoublyLinkedListNode pointer: a reference to the head of the list
Note: Recall that an empty list (i.e., where head = NULL) and a list with one element are sorted lists.

Input Format

The first line contains an integer t, the number of test cases.

Each of the test case is in the following format:

The first line contains an integer n, the number of elements in the linked list.
Each of the next n lines contains an integer, the data for each node of the linked list.
The last line contains an integer, data, which needs to be inserted into the sorted doubly-linked list.
Constraints

Sample Input:

STDIN   Function
-----   --------
1       t = 1
4       n = 4
1       node data values = 1, 3, 4, 10
3
4
10
5       data = 5

Sample Output:

1 3 4 5 10

Explanation

The initial doubly linked list is: 1 <-> 3 <-> 4 <-> 10 <-> NULL.

The doubly linked list after insertion is: 1 <-> 3 <-> 4 <-> 5 <-> 10 <-> NULL.
"""
(Python Solution)
"""
 Insert a node into a sorted doubly linked list
 head could be None as well for empty list
 Node is defined as
 
 class Node(object):
 
   def __init__(self, data=None, next_node=None, prev_node = None):
       self.data = data
       self.next = next_node
       self.prev = prev_node

 return the head node of the updated list 
"""
def SortedInsert(head, data):
    new_node = DoublyLinkedListNode(data)
    if not head:
        return new_node
    
    current = head
    last = head
    while current and current.data < data:
        last = current
        current = current.next
        
    if current:
        if current == head:
            new_node.next = current
            current.prev = new_node
            head = new_node
        else:
            current.prev.next = new_node
            new_node.prev = current.prev
            new_node.next = current
            current.prev = new_node
    else:
        last.next = new_node
        new_node.prev = last
        
    return head
  

(Scala Solution)
// Complete the sortedInsert function below.

    /*
     * For your reference:
     *
     * DoublyLinkedListNode {
     *     data: Int
     *     next: DoublyLinkedListNode
     *     prev: DoublyLinkedListNode
     * }
     *
     */
    def sortedInsert(llist: DoublyLinkedListNode, data: Int): DoublyLinkedListNode = {
        val node = new DoublyLinkedListNode(data)
        if(llist == null) {
            node
        } else {
            if (data < llist.data) {
                node.next = llist
                llist.prev = node
                node
            } else {
                var nodeT = llist
                while(data > nodeT.data && nodeT.next != null) {
                    nodeT = nodeT.next
                }
                if (data > nodeT.data && nodeT.next == null) {
                    nodeT.next = node
                    node.prev = nodeT
                } else {
                    node.next = nodeT
                node.prev = nodeT.prev
                nodeT.prev = node
                node.prev.next = node
                }

                llist
            }
        }

    }