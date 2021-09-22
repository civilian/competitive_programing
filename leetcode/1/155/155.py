from collections import deque

class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self._stack = deque()
        self.minStack = deque()

    def push(self, val: int) -> None:
        if not self.minStack or val <= self.minStack[-1]:
            self.minStack.append(val)
        self._stack.append(val)

    def pop(self) -> None:
        ans = self._stack.pop()
        if ans == self.minStack[-1]:
            self.minStack.pop()
        return ans

    def top(self) -> int:
        return self._stack[-1]

    def getMin(self) -> int:
        return self.minStack[-1]


# minStack = MinStack()
# minStack.push(2**31)
# print(minStack.getMin()) # return -3
# minStack.push(-2) # -2
# minStack.push(0) # 0, -2
# minStack.push(-3) # -3, 0, -2,
# print(minStack.getMin()) # return -3
# print(minStack.pop()) # -3
# print(minStack.top())  # return 0
# minStack.push(-2**31)
# print(minStack.getMin()) # return -2

# minStack = MinStack()
# for i in range(3 * 5**4):
#     minStack.push(i*-1)  # -3, 0, -2,
#
# for i in range(3 * 5 ** 4):
#     print(minStack.getMin())  # return -3
#     minStack.pop()  # return -3

# print("hello")