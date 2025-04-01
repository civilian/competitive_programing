from typing import List

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        complementos = dict()
        for i, a in enumerate(nums):
            b = target - a
            if a in complementos:
                return [complementos[a], i]
            else:
                complementos[b] = i



s = Solution()

a = [2,7,11,15]
t = 9
print(s.twoSum(a, t))
a = [3,2,4]
t = 6
print(s.twoSum(a, t))
a = [3,3]
t = 6
print(s.twoSum(a,t))
