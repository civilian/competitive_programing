

class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        start = 0
        ans = False

        if not len(s) == len(goal):
            return False
        while start < len(s) and not ans:
            try:
                start_in_goal = goal.index(s[0], start)
                index_j = start_in_goal
                inner_for = True
                for j in range(len(goal)):
                    if j >= len(s):
                        inner_for = False
                        break
                    elif s[j] == goal[index_j]:
                        index_j = (index_j + 1) % len(goal)
                    else:
                        inner_for = False
                        break
                if not inner_for:
                    start = start_in_goal + 1
                else:
                    ans = True
            except ValueError as e:
                return False

        return ans

obj = Solution()
s = "abcde"
goal = "cdeab"
print(obj.rotateString(s, goal))
s = "abcde"
goal = "abced"
print(obj.rotateString(s, goal))
s = "a"
goal = "a"
print(obj.rotateString(s, goal))
s = "a"
goal = "b"
print(obj.rotateString(s, goal))
s = "a"
goal = "aa"
print(obj.rotateString(s, goal))
s = ''.join([chr(i) for i in range(100)])
goal = ''.join([chr(i) for i in range(1, 100)]) + chr(0)
# print(s)
# print(goal)
print(obj.rotateString(s, goal))

s = "abac"
goal = "acab"
print(obj.rotateString(s, goal))
s = ''.join([chr(i) for i in range(100)])
goal = ''.join([chr(i) for i in range(1, 100)]) + chr(1)
# print(s)
# print(goal)
print(obj.rotateString(s, goal))

