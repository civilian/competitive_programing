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

int a[1111];

long long eval(int n, long long x) {
  if(x == 0) return a[0];
  long long sum = 0;

  for(int i = n; i > 0; i--) {
    sum = (sum + a[i])*x;
  }
  sum += a[0];

  return sum;
}

int main() {
  ios_base::sync_with_stdio(false);
  int n, k, i, x, cases;
  cases = 1;
  while(cin >> n) {
    if(n < 0) break;
    for(i = n; i >= 0; i--)
      cin >> a[i];
    cin >> k;
    printf("Case %d:\n", cases++);
    for(i = 0; i < k; i++) {
      cin >> x;
      printf("%d\n", eval(n, x));
    }
  }
  return 0;
}

