#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <ctype.h>

using namespace std;

#define SIZE (1 << 19)
typedef long long int64;
 
char buff[SIZE], *p = buff + SIZE;
 
char nextChar() {
    if( p == buff + SIZE ) {
        fread( buff, 1, SIZE, stdin );
        p = buff;
    }
    return *(p++);
}
 
int nextInt() {
    char c;
    int r;
 
    while( !isdigit( c = nextChar() ) );
 
    r = c-'0';
    while( isdigit( c = nextChar() ) ) r = 10*r + c - '0';
 
    return r;
}
 
int main() {
//    freopen("FileReadingFast.txt", "r")
    int testCases = nextInt();
    int numero=nextInt();
    for (int i = 0;; i++) {
        printf("%d\n",nextChar());
    }
    return 0;
}
