#!/bin/python3

import math
import os
import random
import re
import sys

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nm = input().split()

    n = int(nm[0])

    m = int(nm[1])

    queries = []

    values = [0] * (n+1)
    for _ in range(m):
        query = list(map(int, input().rstrip().split()))
        values[query[0]-1] += query[2]
        values[query[1]] -= query[2]
    
    result = 0
    sum_ = 0
    for delta in values:
        sum_ += delta
        result = max(result, sum_)
    fptr.write(str(result) + '\n')

    fptr.close()
