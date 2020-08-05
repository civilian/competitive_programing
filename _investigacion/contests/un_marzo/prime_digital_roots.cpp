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

long long sieve_size;
bitset<1000010> bs;

void sieve(long long upperbound) {
  sieve_size = upperbound + 1;
  bs.set();
  bs[0] = bs[1] = 0;
  for(long long i = 2; i <= sieve_size; i++) if(bs[i]) {
    for(long long j = i*i; j <= sieve_size; j += i)
      bs[j] = 0;
  }
}

int solve(int x) {
  if(bs[x]) { // is prime
    return x;
  }
  int sum = 0, sz = 0;
  while(x > 0) {
    sum += x % 10;
    x /= 10;
    sz++;
  }
  if(sz == 1) return -1;
  return solve(sum);
}

int main() {
#ifdef LOCAL
  freopen("inputs/prime_digital_roots.in", "r", stdin);
  freopen("outputs/prime_digital_roots.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
#endif
  sieve(1000000);

  int n;
  while(cin >> n) {
    if(n == 0) break;
    int ans = solve(n);
    printf("%7d ", n);
    if(ans == -1) printf("   none\n");
    else printf("%7d\n", ans);
  }

  return 0;
}

