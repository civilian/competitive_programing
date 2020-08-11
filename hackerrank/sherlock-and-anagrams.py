#!/bin/python3

import math
import os
import random
import re
import sys
import pprint
from collections import Counter

primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199]

def sherlockAndAnagrams(s):
    identifiers = Counter()
    anagrams = 0
    for i in range(len(s)):
        for j in range(i + 1, len(s) + 1):
            multiplication = 1
            for k in range(i, j):
                index = ord(s[k]) - ord('a')
                identifier = primes[index]
                multiplication *= identifier
            if multiplication in identifiers:
                anagrams += identifiers[multiplication]
                identifiers.update({multiplication: 1})
            else:
                identifiers.update({multiplication: 1})
    return anagrams

    

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input())

    for q_itr in range(q):
        s = input()

        result = sherlockAndAnagrams(s)

        fptr.write(str(result) + '\n')

    fptr.close()
