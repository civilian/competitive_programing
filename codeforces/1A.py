# Theatre Square
# Remember that the -OO is only local
import sys

if not __debug__:
    sys.stdin = open("1A.in.txt")
    sys.stdout = open("1A.out.txt", "w")

a = map(int,raw_input().split())
l,b,s = a[0],a[1],a[2]
print(((l+s-1)/s)*((b+s-1)/s))

sys.stdin.close() 
sys.stdout.close()