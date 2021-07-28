# https://leetcode.com/problems/contains-duplicate/
class Solution:
    def __init__(self):
        self.has = set()

    def containsDuplicate(self, nums) -> bool:
        for n in nums:
            if n in self.has:
                self.has = set()
                return True
            else:
                self.has.add(n)
        self.has = set()
        return False


# obj = Solution()
# print(obj.containsDuplicate([1,2,3,1]))
# print(obj.containsDuplicate([1,2,3,1]))
# print(obj.containsDuplicate([i for i in range(10**5)]))
# print(obj.containsDuplicate([10**9, -10**9]))
# print(obj.containsDuplicate([1,2,3,4]))
# obj = Solution()
# print(obj.containsDuplicate([1,1,1,3,3,4,3,2,4,2]))
# print(obj.containsDuplicate([]))