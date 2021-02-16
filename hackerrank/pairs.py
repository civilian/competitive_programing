#!/bin/python3

import math
import os
import random
import re
import sys


#
# Complete the 'countPairs' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER_ARRAY numbers
#  2. INTEGER k
#

def countPairs(numbers, k):
    numbers.sort()
    i = 0
    j = 1
    ans = 0
    while(j < len(numbers)):
        # a + k = b
        # k is the only constant
        # if i move a or b i will always have a different problem
        # k = b - a?
        if i == j:
            j += 1
        difference = numbers[j] - numbers[i]
        
        if (difference == k):
            ans += 1
            j +=1
        elif (difference > k):
            i += 1
        elif (difference < k):
            j += 1
    
    return ans

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    numbers_count = int(input().strip())

    numbers = []

    for _ in range(numbers_count):
        numbers_item = int(input().strip())
        numbers.append(numbers_item)

    k = int(input().strip())

    result = countPairs(numbers, k)

    print(result)

    fptr.write(str(result) + '\n')

    fptr.close()
