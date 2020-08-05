#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <ctime>
#include <cctype>
#include <cassert>
#include <iostream>
#include <sstream>
#include <iomanip>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <deque>
#include <list>
#include <set>
#include <map>
#include <bitset>
#include <algorithm>
#include <numeric>
#include <complex>

#define D(x) cerr << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

long long calc(long long x) {
  long long a = x, b = x+1;
  if(a & 1) b /= 2;
  else a /= 2;
  return a*b;
}

long long search(long long n) {
  long long lo = 1, hi = INF<<1;

  while(lo+1 < hi) {
    long long mid = lo + (hi-lo) / 2;

    if(calc(mid) >= n) hi = mid;
    else lo = mid;
  }

  //if(calc(lo) > n)
  //  lo--;
  return lo;
}

int main() {
  long long n;

  while(scanf("%lld", &n)) {
    if(n == 0) break;

    long long d = search(n);
    long long x, y;

    if(calc(d) == n) {
      x = d;
      y = 1;
      if(d & 1)
        swap(x, y);
    }
    else {
      n -= calc(d);

      x = d-n+2;
      y = n;

      if(d & 1)
        swap(x, y);
    }

    printf("%lld %lld\n", x, y);
  }

  return 0;
}

