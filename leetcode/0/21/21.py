from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __repr__(self):
        ans = str(self.val) + "," + str(self.next)
        return ans


class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        ans = cur = ListNode(0)
        while list1 and list2:
            if list1.val < list2.val:
                cur.next = list1
                list1 = list1.next
            else:
                cur.next = list2
                list2 = list2.next

            cur = cur.next

        cur.next = list1 or list2

        return ans.next


def toListNode(list_):
    if len(list_) == 0:
        return None
    else:
        ans = ListNode(list_[0], toListNode(list_[1:]))
        return ans

obj = Solution()
l1 = toListNode([1,2,4])
l2 = toListNode([1,3,4])
print(obj.mergeTwoLists(l1, l2))
l1 = []
l2 = []
print(obj.mergeTwoLists(l1, l2))
l1 = []
l2 = [0]
print(obj.mergeTwoLists(l1, l2))