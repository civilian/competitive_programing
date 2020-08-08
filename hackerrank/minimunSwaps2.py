#!/bin/python3

import math
import os
import random
import re
import sys

def find_cicle(arr, visited, i):
    len_ = -1
    while i not in visited:
        visited.add(i)
        i = arr[i] - 1
        len_ += 1
    return len_

# Complete the minimumSwaps function below.
def minimumSwaps(arr, n):
    i = 0
    visited = set()
    ans = 0
    while(i<n):
        if i not in visited:
            ans += find_cicle(arr, visited, i)
        i += 1
    return ans

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    arr = list(map(int, input().rstrip().split()))

    res = minimumSwaps(arr, n)

    fptr.write(str(res) + '\n')

    fptr.close()