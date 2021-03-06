"""
Merge Two sorted Linked Lists
----------------------------------
Link: https://www.hackerrank.com/contests/wissen-coding-challenge-2021/challenges/merge-two-sorted-linked-lists
----------------------------------

Given pointers to the heads of two sorted linked lists, merge them into a single, sorted linked list. Either head pointer may be null meaning that the corresponding list is empty.

Example
headA refers to 1 -> 3 -> 7 -> NULL
headB refers to 1 -> 2 -> NULL

The new list is 1 -> 2 -> 3 -> 7 -> NULL

Function Description:
Complete the mergeLists function in the editor below.

mergeLists has the following parameters:

SinglyLinkedListNode pointer headA: a reference to the head of a list
SinglyLinkedListNode pointer headB: a reference to the head of a list
Returns

SinglyLinkedListNode pointer: a reference to the head of the merged list
Input Format

The first line contains an integer t, the number of test cases.

The format for each test case is as follows:

The first line contains an integer n, the length of the first linked list.
The next n lines contain an integer each, the elements of the linked list.
The next line contains an integer m, the length of the second linked list.
The next n lines contain an integer each, the elements of the second linked list.

Sample Input:
1
3
1
2
3
2
3
4

Sample Output:
1 2 3 3 4 

Explanation:
The first linked list is: 1 -> 2 -> 3 -> NULL

The second linked list is: 3 -> 4 -> NULL

Hence, the merged linked list is: 1 -> 2 -> 3 -> 3 -> 4 -> NULL

"""

(Python Solution)

def mergeLists(head1, head2):
    if head1 == None:
        return head2
    if head2 == None:
        return head1
    if head1.data < head2.data:
        start = head1
        (this, other) = (head1, head2)
    else:
        start = head2
        (this, other) = (head2, head1)
    while other != None:
        if this.next != None and this.next.data < other.data:
            this = this.next
        else:
            (this.next, this) = (other, this.next)
            (this, other) = (other, this)
    return start