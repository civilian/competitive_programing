from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __repr__(self):
        ans = str(self.val) + "," + str(self.next)
        return ans


class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        fast, slow = head, head
        for _ in range(n): fast = fast.next
        if not fast: return head.next
        while fast.next: fast, slow = fast.next, slow.next
        slow.next = slow.next.next
        return head

def toListNode(list_):
    if len(list_) == 0:
        return None
    else:
        ans = ListNode(list_[0], toListNode(list_[1:]))
        return ans

obj = Solution()

head = [1]
n = 1
l = toListNode(head)
print(obj.removeNthFromEnd(l, n))

l = toListNode([1,2,3,4,5])
n = 5
print(obj.removeNthFromEnd(l, n))
head = [1, 2]
n = 2
l = toListNode(head)
print(obj.removeNthFromEnd(l, n))
