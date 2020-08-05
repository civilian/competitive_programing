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

int p[] = { 0, 2, 3, 5, 7, 13, 17, 19, 31, 61, 89,
            107, 127, 521, 607, 1279, 2203, 2281,
            3217, 4253, 4423, 9689, 9941, 11213,
            19937, 21701, 23209, 44497, 86243,
            110503, 132049, 216091, 756839, 859433,
            1257787, 1398269, 2976221, 3021377, 6972593 };

int main() {
  ios_base::sync_with_stdio(false);
  int t;
  cin >> t;

  while(t--) {
    int n;
    cin >> n;
    cout << p[n] << endl;
  }

  return 0;
}

