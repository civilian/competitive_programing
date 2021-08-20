# import pprint
class Solution:
    def setZeroes(self, matrix) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        n = len(matrix)
        m = len(matrix[0])
        both = False
        if matrix[0][0] == 0:
            both = True
        # print(matrix)

        for i in range(n):
            for j in range(m):
                if matrix[i][j] == 0:
                    matrix[i][0] = 'r'
                    matrix[0][j] = 'c'
                    if i == 0 and j == 0 and \
                            ( matrix[0][0] == 'r' or matrix[0][0] == 'c'):
                        both = True
        # print(matrix)

        # special case
        if (matrix[0][0] == 'r' or matrix[0][0] == 'c') and m == 1:
            both = True

        # fill cols
        for j in range(1, m):
            if matrix[0][j] == 'c':
                for i in range(n):
                    matrix[i][j] = 0

        # print(matrix)
        # fill rows
        for i in range(1, n):
            if matrix[i][0] == 'r':
                for j in range(m):
                    matrix[i][j] = 0

        if matrix[0][0] == 'r':
            for j in range(m):
                matrix[0][j] = 0

        if matrix[0][0] == 'c':
            for i in range(n):
                matrix[i][0] = 0

        if both:
            for i in range(n):
                matrix[i][0] = 0
            for j in range(m):
                matrix[0][j] = 0

        print(matrix)

obj = Solution()
obj.setZeroes([[1,1,1],[1,0,1],[1,1,1]])
obj.setZeroes([[0,1,2,0],[3,4,5,2],[1,3,1,5]])
obj.setZeroes([[1,1,2,1],
               [3,4,5,2],
               [1,3,0,5],
               [1,3,1,5]])
obj.setZeroes([[0,1,2,0],[3,4,5,2],[1,3,1,5]])
obj.setZeroes([[0],[1]])
obj.setZeroes([[1],[0],[3]])
obj.setZeroes([[1,1,2,0]])
obj.setZeroes([[1,2,3,4],[5,0,7,8],[0,10,11,12],[13,14,15,0]])
obj.setZeroes([[-4,-2147483648,6,-7,0],[-8,6,-8,-6,0],[2147483647,2,-9,-6,-10]])

obj.setZeroes([[0,1,2,0],[3,4,5,2],[1,3,1,5]])
obj.setZeroes([[8,3,6,9,7,8,0,6],[0,3,7,0,0,4,3,8],[5,3,6,7,1,6,2,6],[8,7,2,5,0,6,4,0],[0,2,9,9,3,9,7,3]])