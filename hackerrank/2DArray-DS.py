#!/bin/python3

import math
import os
import random
import re
import sys

coords = ((-1, -1), (0,-1), (1,-1), 
                    (0, 0),
           (-1, 1), (0, 1), (1,1),)

# Complete the hourglassSum function below.
def hourglassSum(arr):
    ans = 0
    for i in range(1,5):
        for j in range(1,5):
            partial_sum = 0
            for i_coord in coords:
                x = i + i_coord[0]
                y = j + i_coord[1]
                partial_sum += arr[y][x]
            ans = max(ans, partial_sum)
    return ans

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    arr = []

    for _ in range(6):
        arr.append(list(map(int, input().rstrip().split())))

    result = hourglassSum(arr)

    fptr.write(str(result) + '\n')

    fptr.close()