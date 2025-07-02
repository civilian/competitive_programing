class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        nums_in_index = {}
        for i, num in enumerate(nums):
            complement = target - num
            if complement in nums_in_index:
                return [nums_in_index[complement], i]
            else:
                nums_in_index[num] = i