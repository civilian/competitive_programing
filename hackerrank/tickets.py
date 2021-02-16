#!/bin/python3

import math
import os
import random
import re
import sys



# Complete the maxTickets function below.
def maxTickets(tickets):
    tickets.sort()
    ans = 0
    j = 0
    m = 1
    while j < len(tickets) - 1:
        difference = tickets[j+1] - tickets[j]
        if difference == 1 or difference == 0:
            m += 1
        else:
            ans = max(ans, m)
            m = 1
        j += 1
    
    return ans

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    tickets_count = int(input().strip())

    tickets = []

    for _ in range(tickets_count):
        tickets_item = int(input().strip())
        tickets.append(tickets_item)

    res = maxTickets(tickets)

    fptr.write(str(res) + '\n')

    fptr.close()
