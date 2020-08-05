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
bitset<100> bs;
vector<int> primes;

void sieve(long long upperbound) {
  sieve_size = upperbound+1;

  bs.set();
  bs[0] = bs[1] = 0;

  for(int i = 2; i <= sieve_size; i++) if(bs[i]) {
    for(int j = i*i; j <= sieve_size; j+=i)
      bs[j] = 0;
    primes.push_back(i);
  }
}

int main() {
  sieve(100);

  int n;
  while(scanf("%d", &n)) {
    if(n == 0) break;
    long long ans = 1;
    for(int i = 0; i < primes.size(); i++) {
      if(primes[i] > n) break;
      ans *= primes[i];
    }
    printf("%lld\n", ans);
  }

  return 0;
}

