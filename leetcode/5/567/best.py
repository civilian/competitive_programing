# https://leetcode.com/problems/permutation-in-string/
class Solution:

    def checkInclusion(self, s1, s2):
        A = [ord(x) - ord('a') for x in s1]
        B = [ord(x) - ord('a') for x in s2]

        target = [0] * 26
        for x in A:
            target[x] += 1

        window = [0] * 26
        for i, x in enumerate(B):
            window[x] += 1
            if i >= len(A):
                window[B[i - len(A)]] -= 1
            if window == target:
                return True
        return False

obj = Solution()
print(obj.checkInclusion("ab", "eidbaooo"))
# # print(obj.checkInclusion("ab", "eidbaooo"))
# # print(obj.checkInclusion("eidbaooo", "ab"))
# # print(obj.checkInclusion("ab", "eidboaoo"))
# # print(obj.checkInclusion("ab", "eidboaoo"))
# # print(obj.checkInclusion("ab", "ooba"))
# # print(obj.checkInclusion("", "qwerqwer"))
# tmp = ''.join([str(elem) for elem in [i for i in range(10**4)]])
# print(tmp)
# print(obj.checkInclusion("ab", tmp))