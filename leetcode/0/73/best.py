class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """

        first_row_zero = False
        first_col_zero = False

        m = len(matrix)
        n = len(matrix[0])

        for r in range(m):
            for c in range(n):
                if matrix[r][c] == 0:
                    if r == 0:
                        first_row_zero = True
                    if c == 0:
                        first_col_zero = True
                    if r != 0 and c != 0:
                        matrix[r][0] = 0
                        matrix[0][c] = 0

        for r in range(1, m):
            if matrix[r][0] == 0:
                matrix[r] = [0 for i in range(n)]
        for c in range(1, n):
            if matrix[0][c] == 0:
                for r in range(1, m):
                    matrix[r][c] = 0
        if first_row_zero:
            matrix[0] = [0 for i in range(n)]

        if first_col_zero:
            for r in range(m):
                matrix[r][0] = 0