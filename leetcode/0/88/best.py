from typing import List

class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        '''
        for i in range(n):
            nums1[m + i] = nums2[i]
        nums1.sort()
        '''
        ''' 
        nums1copy = nums1[:m]
        p = p1 = p2 = 0
        while p1 < m and p2 < n:
            if nums1copy[p1] < nums2[p2]:
                nums1[p] = nums1copy[p1]
                p += 1
                p1 += 1
            else:
                nums1[p] = nums2[p2]
                p += 1
                p2 += 1
        if p1 < m:
            nums1[p:] = nums1copy[p1:]
        elif p2 < n:
            nums1[p:] = nums2[p2:]
        '''

        p1 = m - 1
        p2 = n - 1

        for p in range(n + m - 1, -1, -1):
            if p2 < 0:
                break
            if p1 >= 0 and nums1[p1] > nums2[p2]:
                nums1[p] = nums1[p1]
                p1 -= 1
            else:
                nums1[p] = nums2[p2]
                p2 -= 1