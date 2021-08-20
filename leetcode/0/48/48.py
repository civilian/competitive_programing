# import pprint

class Solution:
    def rotate(self, matrix) -> None:
        n = len(matrix)
        m = n - 1
        i = 0
        while m > i:
            matrix[m], matrix[i] = matrix[i], matrix[m]
            i += 1
            m -= 1

        for i in range(n):
            for j in range(i + 1, n):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
        # pprint.pprint(matrix)
#
# obj = Solution()
# obj.rotate([[1,2,3],[4,5,6],[7,8,9]])
# obj.rotate([[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]])
# obj.rotate([[1]])
# obj.rotate([[1,2],[3,4]])
# m = [[i for i in range(20)] for i in range(20)]
# pprint.pprint(m)
# obj.rotate(m)

# [0][0] = [0][2]
# [0][1] = [1][2]
# [0][2] = [2][2]