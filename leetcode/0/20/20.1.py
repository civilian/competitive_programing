from collections import deque

class Solution:
    matching = {
        '(': ')',
        '{': '}',
        '[': ']'
    }

    def isValid(self, s: str) -> bool:
        stack_ = deque()
        for c in s:
            if c in self.matching:
                stack_.append(c)
            else:
                try:
                    current = stack_.pop()
                    if self.matching[current] != c:
                        return False
                except IndexError:
                    return False

        return len(stack_) == 0