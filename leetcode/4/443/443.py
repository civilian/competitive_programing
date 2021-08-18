import math

class Solution:
    def show(self, amount):
        if amount == "1":
            return 0
        else:
            return len(amount)

    def compress(self, chars) -> int:
        chars.append(' ')
        # print(chars)
        curr = chars[0]
        amount = 1
        ansSize = 0
        index = 0
        for c in chars[1:]:
            if curr == c:
                amount += 1
            else:
                amount = str(amount)
                count = self.show(amount)
                ansSize += count + 1
                chars[index] = curr
                if amount != "1":
                    for ch in amount:
                        index += 1
                        chars[index] = ch
                index += 1
                curr = c
                amount = 1
        # print(chars)
        return ansSize


# obj = Solution()
# print(obj.compress(["a", "a", "b", "b", "c", "c", "c"]))
# print(obj.compress(["a", "a", "b", "b", "c", "c", "c"]))
# print(obj.compress(["a"]))
# print(obj.compress(["a", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"]))
# print(obj.compress(["a", "a", "a", "b", "b", "a", "a"]))
# print(obj.compress([chr(ord('a') + elem) for elem in [2 for i in range(1, 2001)]]))
# print(obj.compress([chr(ord('a') + elem) for elem in [i for i in range(1, 2001)]]))
