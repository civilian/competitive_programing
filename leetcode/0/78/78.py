from typing import List
from itertools import combinations

class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        l = [[i for i in combinations(nums, r)] for r in range(len(nums))]
        print(l)

o = Solution()

l = [1, 2, 3]
o.subsets(l)
l = [0]
o.subsets(l)