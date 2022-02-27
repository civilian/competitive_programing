class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        cur = nums[0]
        for i in range(1, len(nums)):
            if cur == nums[i]:
                nums[i] = -1000
            else:
                cur = nums[i]

        cur_i = 1
        for i in range(1, len(nums)):
            if nums[i] != -1000:
                if i == cur_i:
                    cur_i += 1
                    continue
                nums[cur_i] = nums[i]
                nums[i] = -1000
                cur_i += 1

        return cur_i