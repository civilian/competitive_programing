#!/bin/python3

import math
import os
import random
import re
import sys

def switch(q, i):
    q[i], q[i - 1] = q[i-1], q[i]

# Complete the minimumBribes function below.
def minimumBribes(q, n):
    i = n - 1
    moves = 0
    finish = True
    while(i > -1):
        if q[i] != n:
            if q[i-1] == n:
                moves += 1
                switch(q, i)
            elif q[i-2] == n:
                moves += 2
                switch(q, i-1)
                switch(q, i)
            else:
                finish = False
                print("Too chaotic")
                break
        i -= 1
        n -= 1
    if (finish):
        print(moves)



if __name__ == '__main__':
    t = int(input())

    for t_itr in range(t):
        n = int(input())

        q = list(map(int, input().rstrip().split()))

        minimumBribes(q, n)