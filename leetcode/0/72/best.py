import collections

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n1, n2 = len(word1), len(word2)
        if n1 == 0:
            return n2
        if n2 == 0:
            return n1

        demo = [[float('inf')] * (n2 + 1) for _ in range(n1 + 1)]
        steps = max(n1, n2)  # replace parts and delete

        queue = collections.deque([])
        queue.append([0, 0, 0])
        while queue:
            i, j, step = queue.popleft()
            # print(i,j,step)
            if demo[i][j] <= step:
                continue
            else:
                demo[i][j] = step
            while i < n1 and j < n2 and word1[i] == word2[j]:
                i += 1
                j += 1
            # stop when i==n1 or j == n2 or not equal
            if i == n1 and j == n2:
                steps = step
                break
            if i == n1:
                queue.append([i, j + 1, step + 1])  # delete
                continue
            if j == n2:
                queue.append([i + 1, j, step + 1])  # insert
                continue
            else:

                queue.append([i + 1, j + 1, step + 1])  # replace

                queue.append([i, j + 1, step + 1])  # insert
                queue.append([i + 1, j, step + 1])  # delete

        return steps

obj = Solution()
print(obj.minDistance("horse", "ros"))
print(obj.minDistance("horse", "ros"))
print(obj.minDistance("", "ros"))
print(obj.minDistance("ros", ""))
str1 = ''.join([chr(ord('a') + elem) for elem in [i for i in range(500)]])
str2 = ''.join([chr(ord('a') + elem) for elem in [i for i in range(501, 1001)]])
print(obj.minDistance(str1, str2))
print(obj.minDistance("intention", "execution"))