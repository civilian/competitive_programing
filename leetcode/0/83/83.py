from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __repr__(self):
        ans = str(self.val) + "," + str(self.next)
        return ans

class Solution:

    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        ans = curr = head
        while curr:
            if curr.next:
                if curr.next.val == curr.val:
                    curr.next = curr.next.next
                else:
                    curr = curr.next
            else:
                break
        return ans

def toListNode(list_):
    if len(list_) == 0:
        return None
    else:
        ans = ListNode(list_[0], toListNode(list_[1:]))
        return ans

obj = Solution()
l = toListNode([1,1,2])
print(obj.deleteDuplicates(l))
l = toListNode([])
print(obj.deleteDuplicates(l))
l = toListNode([1,1,1,1])
print(obj.deleteDuplicates(l))
l = toListNode([1 for i in range(301)])
print(obj.deleteDuplicates(l))
l = toListNode([i for i in range(301)])
print(obj.deleteDuplicates(l))