class Solution:
    def compress(self, chars) -> int:
        n = len(chars)
        if n == 1:
            return 1

        l = r = 0
        while r < n:
            current = chars[r]
            count = 0
            while r < n and chars[r] == current:
                r += 1
                count += 1
            chars[l] = current
            if count > 1:
                for i in str(count):
                    chars[l + 1] = i
                    l += 1
            l += 1

        return l

# obj = Solution()
# print(obj.compress(["a", "a", "b", "b", "c", "c", "c"]))
# print(obj.compress(["a", "a", "b", "b", "c", "c", "c"]))
# print(obj.compress(["a"]))
# print(obj.compress(["a", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"]))
# print(obj.compress(["a", "a", "a", "b", "b", "a", "a"]))
# print(obj.compress([chr(ord('a') + elem) for elem in [2 for i in range(1, 2001)]]))
# print(obj.compress([chr(ord('a') + elem) for elem in [i for i in range(1, 2001)]]))