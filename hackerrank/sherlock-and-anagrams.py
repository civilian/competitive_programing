#!/bin/python3

import math
import os
import random
import re
import sys
import pprint

def LCSubStr(X, Y, m, n):

    LCSuff = [[0 for k in range(n+1)] for l in range(m+1)] 
    result = 0
    for i in range(m + 1): 
        for j in range(i + 1): 
            if (i == 0 or j == 0): 
                LCSuff[i][j] = 0
            elif (X[i-1] == Y[j-1]): 
                LCSuff[i][j] = LCSuff[i-1][j-1] + 1
            else: 
                LCSuff[i][j] = 0
    pp = pprint.PrettyPrinter(indent=2)
    pp.pprint(LCSuff)
    
    for i in range(m + 1): 
        for j in range(i, n + 1):
            result += LCSuff[i][j]
    return result 

# Complete the sherlockAndAnagrams function below.
def sherlockAndAnagrams(s):
    return LCSubStr(s,s[::-1], len(s), len(s))

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input())

    for q_itr in range(q):
        s = input()

        result = sherlockAndAnagrams(s)

        fptr.write(str(result) + '\n')

    fptr.close()
