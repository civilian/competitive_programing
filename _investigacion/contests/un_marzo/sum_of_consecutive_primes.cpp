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

map<int,int> sum, sum_count;

long long sieve_size;
bitset<10010> bs;
vector<int> primes;

void sieve(long long upperbound) {
  sieve_size = upperbound + 1;
  bs.set();
  bs[0] = bs[1] = true;
  for(long long i = 2; i <= sieve_size; i++) if(bs[i]) {
    for(long long j = i*i; j <= sieve_size; j += i) bs[j] = 0;
    primes.push_back((int)i);
  }
}

void precalc() {
  int m = primes.size();

  sum[primes[0]] = 2;
  for(int i = 1; i < m; i++) {
    sum[primes[i]] = primes[i] + sum[primes[i-1]];
  }

  for(int i = 0; i < m; i++) {
    int curr_sum = sum[primes[i]];
    sum_count[curr_sum]++;
    for(int j = i-1; j >= 0; j--) {
      int curr = curr_sum - sum[primes[j]];
      if(curr > 10000) break;
      sum_count[curr]++;
    }
  }
}

int main() {
#ifdef LOCAL
  freopen("inputs/sum_primes.in", "r", stdin);
  freopen("outputs/sum_primes.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
#endif

  sieve(10000);
  precalc();

  int n;
  while(cin >> n) {
    if(n == 0) break;
    int ans = (sum_count.count(n))? sum_count[n] : 0;
    cout << ans << endl;
  }

  return 0;
}

