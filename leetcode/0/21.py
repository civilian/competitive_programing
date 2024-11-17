from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    @classmethod
    def desde_array(cls, array):
        ans = it = ListNode()
        for n in array:
            it.next = ListNode(n)
            it = it.next
        return ans.next

    def __repr__(self):
        it = self
        ans = []
        while it:
            ans.append(str(it.val))  # Agregar el valor del nodo actual
            it = it.next
        return " -> ".join(ans)  # Formatear usando '->' entre valores

class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        ans = iterando_ans = ListNode()
        while list1 or list2:
            if not list1:
                iterando_ans.next = list2
                break
            if not list2:
                iterando_ans.next = list1
                break
            if list1.val < list2.val:
                iterando_ans.next = ListNode(list1.val)
                iterando_ans = iterando_ans.next
                list1 = list1.next
            else:
                iterando_ans.next = ListNode(list2.val)
                iterando_ans = iterando_ans.next
                list2 = list2.next
        return ans.next

s = Solution()
l1 = []
l2 = []
print(s.mergeTwoLists(ListNode.desde_array(l1), ListNode.desde_array(l2)))
l1 = [1,2,4]
l2 = [1,3,4]
l1 = ListNode.desde_array(l1)
l2 = ListNode.desde_array(l2)
print(s.mergeTwoLists(l1, l2))
l1 = []
l2 = [0]
l1 = ListNode.desde_array(l1)
l2 = ListNode.desde_array(l2)
print(s.mergeTwoLists(l1, l2))
