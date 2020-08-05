# Theatre Square
# Remember that the -OO is only local
import sys

if not __debug__:
    sys.stdin = open(".in.txt")
    sys.stdout = open(".out.txt", "w")


sys.stdin.close() 
sys.stdout.close()