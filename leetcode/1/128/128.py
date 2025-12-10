class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        
        s = set(nums)
        max_ = 0
        for elo in s:
            if elo - 1 not in s:
                tmp = 1
                it = elo + 1
                while it in s:
                    tmp = tmp + 1
                    it = it + 1
                max_ = max(tmp, max_)
        return max_