class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        ans = []
        remains = {}
        for i in range(len(nums)):
            if nums[i] in remains.keys():
                ans.append(remains[nums[i]])
                ans.append(i)
                remains.pop(nums[i])
            else:
                remains[target - nums[i]] = i
        return ans


obj = Solution()
