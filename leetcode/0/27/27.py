from typing import List

class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        cur_i = 0
        for i in range(0, len(nums)):
            if nums[i] != val:
                if i == cur_i:
                    cur_i += 1
                    continue
                nums[cur_i] = nums[i]
                nums[i] = -1000
                cur_i += 1

        return cur_i


sol = Solution()

l = [3,2,2,3]
val = 3
print(sol.removeElement(l, val))

l = [0,1,2,2,3,0,4,2]
val = 2

print(sol.removeElement(l, val))