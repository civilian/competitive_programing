class Solution:
    def transpose(self, matrix):
        n = len(matrix[0])

        for i in range(n):
            for j in range(i, n):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]

    def reverse(self, matrix):

        n = len(matrix[0])

        for i in range(n):
            for j in range(n // 2):
                matrix[i][j], matrix[i][n - 1 - j] = matrix[i][n - 1 - j], matrix[i][j]

    def rotate(self, matrix) -> None:

        self.transpose(matrix)
        self.reverse(matrix)