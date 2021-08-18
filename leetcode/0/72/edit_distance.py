from pprint import pprint


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        x = len(word1) + 1
        y = len(word2) + 1
        memo = [[0]*y for i in range(x)]
        for i in range(x):
            for j in range(y):
                if i == 0:
                    memo[i][j] = j
                elif j == 0:
                    memo[i][j] = i
                elif word1[i-1] == word2[j-1]:
                    memo[i][j] = memo[i-1][j-1]
                else:
                    memo[i][j] = 1 + min(
                        memo[i - 1][j],  # deleting
                        memo[i - 1][j - 1],  # replacing
                        memo[i][j - 1]  # adding
                    )
        # pprint(memo)
        return memo[x-1][y-1]


    # def mD(self, word1: str, word2: str, i, j) -> int:
    #     # x = len(word1)
    #     # y = len(word2)
    #     # memo = [[] for i in range(y)]
    #     if i == 0:
    #         return j
    #     if j == 0:
    #         return i
    #     if word1[i] == word2[j]:
    #         return self.mD(word1, word2, i-1, j-1)
    #     else:
    #         return 1 + min(
    #                 self.mD(word1, word2, i-1, j), # deleting
    #                 self.mD(word1, word2, i-1, j-1),# replacing
    #                 self.mD(word1, word2, i, j-1) # adding
    #             )
    #
    # def minDistance(self, word1: str, word2: str) -> int:
    #     # x = len(word1)
    #     # y = len(word2)
    #     # memo = [[] for i in range(y)]
    #     word1 = '1' + word1
    #     word2 = '1' + word2
    #     return self.mD(word1, word2, len(word1)-1, len(word2)-1)

# obj = Solution()
# print(obj.minDistance("horse", "ros"))
# print(obj.minDistance("horse", "ros"))
# print(obj.minDistance("", "ros"))
# print(obj.minDistance("ros", ""))
# str1 = ''.join([chr(ord('a') + elem) for elem in [i for i in range(500)]])
# str2 = ''.join([chr(ord('a') + elem) for elem in [i for i in range(501, 1001)]])
# print(obj.minDistance(str1, str2))
# print(obj.minDistance("intention", "execution"))
