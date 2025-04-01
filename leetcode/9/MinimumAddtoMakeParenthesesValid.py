class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        stack = [s[0]]
        for char_ in s[1:]:
            if not stack:
                stack.append(char_)
                continue
            if char_ == '(':
                stack.append(char_)
                continue
            if char_ == ')':
                if stack[-1] == ')':
                    stack.append(char_)
                    continue
                if stack[-1] == '(':
                    stack.pop()
                    continue

        return len(stack)





s = Solution()
p = "("
print(s.minAddToMakeValid(p))

p = "())"
print(s.minAddToMakeValid(p)) #1

p = "((("
print(s.minAddToMakeValid(p)) #3

p = "()))(("
print(s.minAddToMakeValid(p)) #4


class Solution:
    def minAddToMakeValid(self, s: str) -> int:

        brackets = 0
        missing = 0

        for ch in s:
            if ch == '(':
                brackets += 1
            else:
                if brackets:
                    brackets -= 1
                else:
                    missing += 1

        return brackets + missing