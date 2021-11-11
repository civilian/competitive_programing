class Solution:
    def __init__(self):
        self.memo = [-1] * 47
        self.memo[1] = 1
        self.memo[2] = 2

    def climbStairsRecursive(self, n: int) -> int:
        if self.memo[n] != -1:
            return self.memo[n]
        ans = self.climbStairsRecursive(n - 1) + self.climbStairsRecursive(n - 2)
        self.memo[n] = ans
        return ans

    def climbStairs(self, n: int) -> int:
        return self.climbStairsRecursive(n)

obj = Solution()
# print(obj.climbStairs(1))
# print(obj.climbStairs(2))
# print(obj.climbStairs(3))
# print(obj.climbStairs(4))
# print(obj.climbStairs(5))
# print(obj.climbStairs(44))
print(obj.climbStairs(45))
print(obj.memo)