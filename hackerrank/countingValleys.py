#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the countingValleys function below.
def countingValleys(n, s):
    level = 0
    valey = False 
    count_valeys = 0
    for c in s:
        if c == 'D':
            level -= 1
        elif c == 'U':
            level += 1

        if not valey and level < 0:
            valey = True
            count_valeys += 1
        if level > -1:
            valey = False
    return count_valeys
        


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    s = input()

    result = countingValleys(n, s)

    fptr.write(str(result) + '\n')

    fptr.close()
