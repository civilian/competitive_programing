#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <algorithm>

using namespace std;

// Generate primes until 10^8

#define MAX 100000000
#define LMT 10000

int flag[MAX>>6];

#define ifc(x) (flag[x>>6]&(1<<((x>>1)&31)))
#define isc(x) (flag[x>>6]|=(1<<((x>>1)&31)))

int primes[5761460], sz;

void sieve() {
  sz = 0;
  primes[sz++] = 2;

  for(int i=3; i<LMT; i+=2)
    if(!ifc(i)) {
      for(int j=i*i,k=i<<1; j<MAX; j+=k)
        isc(j);
      primes[sz++] = i;
    }

  for(int i=primes[sz-1]+2; i<MAX; i+=2) {
    if(!ifc(i)) {
      primes[sz++] = i;
    }
  }
}

int main() {
  sieve();
  int x;
  while(scanf("%d", &x)) {
    if(x == 0) break;
    int px = int(upper_bound(primes, primes+sz, x) - primes);
    double lnx = (double)x / log(x);
    double ans = fabs(px - lnx) / (double)px;
    printf("%.1lf\n", ans*100.);
  }
  return 0;
}
