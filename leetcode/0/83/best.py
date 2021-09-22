# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        tmp = curr = head
        while curr:
            if curr.next:
                if curr.next.val == curr.val:
                    curr.next = curr.next.next
                else:
                    curr = curr.next
            else:
                break

        return tmp