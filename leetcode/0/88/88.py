from typing import List


class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        i = j = insertions = 0
        while i < m + n and j < n:
            if m == 0 or (i >= m + insertions and j < n) or nums1[i] > nums2[j]:
                nums1.insert(i, nums2[j])
                nums1.pop()
                insertions += 1
                i += 1
                j += 1
            elif i < m + n and j < n and nums1[i] <= nums2[j]:
                i += 1
        return nums1


obj = Solution()

n1 = [-1,0,0,3,3,3,0,0,0]
m = 6
n2 = [1,2,2]
n = 3
print(obj.merge(n1, m, n2, n))

n1 = [1,2,3,0,0,0]
m = 3
n2 = [2,5,6]
n = 3
print(obj.merge(n1, m, n2, n))
n1 = [1]
m = 1
n2 = []
n = 0
print(obj.merge(n1, m, n2, n))
n1 = [0]
m = 0
n2 = [1]
n = 1
print(obj.merge(n1, m, n2, n))
n1 = []
m = 0
n2 = []
n = 0
print(obj.merge(n1, m, n2, n))
n1 = []
m = 0
n2 = []
n = 0
print(obj.merge(n1, m, n2, n))
n1 = [1,0]
m = 1
n2 = [2]
n = 1
print(obj.merge(n1, m, n2, n))
