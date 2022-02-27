class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        prefix = ""
        min_l = min(strs, key=len)

        for i in range(len(min_l)):
            good_letter = strs[0][i]
            for j in range(len(strs)):
                if strs[j][i] != good_letter:
                    return prefix
                else:
                    pass
            prefix += good_letter

        return prefix