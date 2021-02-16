#!/bin/python3

import math
import os
import random
import re
import sys
from heapq import heapify, heappush, heappop


def find_medium(min_heap, min_heap_size, max_heap, max_heap_size):
    return 0

# Complete the activityNotifications function below.
def activityNotifications(expenditure, d, n):
    max_heap = []
    heapify(max_heap)
    min_heap = []
    heapify(min_heap)

    min_heap_size = 0
    max_heap_size = 0
    even = False
    ans = 0
    for i in range(d):
        if even:
            even = False
            max_heap_size += 1
            heappush(max_heap, expenditure[i] * -1)
        else:
            even = True
            min_heap_size += 1
            heappush(min_heap, expenditure[i])
    
    for i in range(d, n):
        find_medium(min_heap, min_heap_size, max_heap, max_heap_size)

    return ans



if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nd = input().split()

    n = int(nd[0])

    d = int(nd[1])

    expenditure = list(map(int, input().rstrip().split()))

    result = activityNotifications(expenditure, d, n)

    fptr.write(str(result) + '\n')

    fptr.close()
