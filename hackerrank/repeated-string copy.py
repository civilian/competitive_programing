#!/bin/python3

import math
import os
import random
import re
import sys

def a_in_str(s):
    return sum(map(lambda x : 1 if 'a' in x else 0, s))

# Complete the repeatedString function below.
def repeatedString(s, n):
    l = len(s)
    ans = 0
    a_count_in_s =  a_in_str(s)
    final_piece_len = n % l
    if n >= l:
        repeated_str_count = int(n / l)
        ans += a_count_in_s * repeated_str_count
    
    ans += a_in_str(s[:final_piece_len])

    return ans



if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    n = int(input())

    result = repeatedString(s, n)

    fptr.write(str(result) + '\n')

    fptr.close()