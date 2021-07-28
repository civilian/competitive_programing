# https://leetcode.com/problems/permutation-in-string/
class Solution:

    def checkInclusion(self, s1: str, s2: str) -> bool:
        s1 = sorted(s1)
        for i in range(0, len(s2) - len(s1) + 1):
            tmp = sorted(s2[i:i+len(s1)])
            if s1 == tmp:
                return True
        return False

# obj = Solution()
# # print(obj.checkInclusion("ab", "eidbaooo"))
# # print(obj.checkInclusion("ab", "eidbaooo"))
# # print(obj.checkInclusion("eidbaooo", "ab"))
# # print(obj.checkInclusion("ab", "eidboaoo"))
# # print(obj.checkInclusion("ab", "eidboaoo"))
# # print(obj.checkInclusion("ab", "ooba"))
# # print(obj.checkInclusion("", "qwerqwer"))
# tmp = ''.join([str(elem) for elem in [i for i in range(10**4)]])
# print(tmp)
# print(obj.checkInclusion("ab", tmp))