class Solution:
    def isValid(self, s: str) -> bool:
        pila = []
        open = "([{"
        close = ")]}"
        for parentesis in s:
            if parentesis in open:
                pila.append(parentesis)
            else:
                if pila and open.index(pila[-1]) == close.index(parentesis):
                    pila.pop()
                else:
                    return False
        return not pila

sol = Solution()
s = "()()"
print(sol.isValid(s)) # True
s = "()"
print(sol.isValid(s)) # True
s = "()[]{}"
print(sol.isValid(s)) # True
s = "(]"
print(sol.isValid(s)) # False
s = "([])"
print(sol.isValid(s)) # True
s = "([)]"
print(sol.isValid(s)) # False
s = "["
print(sol.isValid(s)) # False
s = "]"
print(sol.isValid(s)) # False

class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        dic = {'(':')', '[':']', '{':'}'}
        
        for ch in s:
            if ch in dic:
                stack.append(ch)
            else:
                if (len(stack) and dic[stack[-1]] == ch):
                    stack.pop()
                else:
                    return False
        return stack == []