#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the rotLeft function below.
def rotLeft(a, d, n):
    b = [0] * n
    for i in range(n):
        b[(i-d) % n ] = a[i]
    return b

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nd = input().split()

    n = int(nd[0])

    d = int(nd[1])

    a = list(map(int, input().rstrip().split()))

    result = rotLeft(a, d, n)

    fptr.write(' '.join(map(str, result)))
    fptr.write('\n')

    fptr.close()
