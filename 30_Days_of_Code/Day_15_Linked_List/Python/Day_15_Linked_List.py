"""

Day 15: Linked List

Link: https://www.hackerrank.com/challenges/30-linked-list


"""

(Python Solution)

def insert(self,head,data): 
    #Complete this method
        if head is None:
            head = Node(data)
        else:
            temp=head
            while(temp.next!=None):
                temp=temp.next
            temp.next = Node(data)
        return head