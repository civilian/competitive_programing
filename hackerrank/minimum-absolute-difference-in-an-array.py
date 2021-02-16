#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the minimumAbsoluteDifference function below.
def minimumAbsoluteDifference(arr, n):
    arr.sort()
    ans = math.inf
    for i in range(n-1):
        ans = min(ans, abs(arr[i] - arr[i+1]))
    return ans


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')
    # fptr = open("out.txt", 'w')

    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    result = minimumAbsoluteDifference(arr, n)

    fptr.write(str(result) + '\n')

    fptr.close()