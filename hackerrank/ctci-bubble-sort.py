#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the countSwaps function below.
def countSwaps(a):
    n = len(a)
    steps = 0
    for i in range(n):
        for j in range(n-1):
            # Swap adjacent elements if they are in decreasing order
            if a[j] > a[j + 1]:
                steps += 1
                a[j], a[j + 1] = a[j + 1], a[j]
    
    return steps, a
        
    
def printAnswer(a):
    steps, array = countSwaps(a)
    print(f"Array is sorted in {steps} swaps.")
    print(f"First Element: {array[0]}")
    print(f"Last Element: {array[-1]}")

if __name__ == '__main__':
    n = int(input())

    a = list(map(int, input().rstrip().split()))

    printAnswer(a)