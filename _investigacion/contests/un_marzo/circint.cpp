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

struct circle {
  long long x, y, r;
  circle(long long _x = 0, long long _y = 0, long long _r = 0) {
    x = _x; y = _y; r = _r;
  }
};

inline
long long sqr(long long x) {
  return x * x;
}

bool intersect(const circle& a, const circle& b) {
  long long dd = sqr(a.x-b.x) + sqr(a.y-b.y);
  long long rr = sqr(a.r+b.r);
  return dd <= rr;
}

int main() {
#ifdef LOCAL
  freopen("inputs/circint.in", "r", stdin);
  freopen("outputs/circint.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
#endif
  int t;
  cin >> t;

  while(t--) {
    circle a, b;
    cin >> a.x >> a.y >> b.x >> b.y >> a.r >> b.r;
    cout << (intersect(a,b)? "YES" : "NO") << endl;
  }

  return 0;
}

