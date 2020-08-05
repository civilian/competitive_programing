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

bool solve(int q) {
  int p = 2;
  int cnt = 0;
  while(p*p <= q) {
    while(q % p == 0) {
      q /= p;
      cnt++;
    }
    p++;
  }
  if(q > 1) cnt++;
  return cnt == 2;
}

int main() {
#ifdef LOCAL
  freopen("inputs/semi_prime.in", "r", stdin);
  freopen("inputs/semi_prime.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
#endif

  int n;
  while(cin >> n) {
    cout << (solve(n)? "Yes\n" : "No\n");
  }

  return 0;
}

