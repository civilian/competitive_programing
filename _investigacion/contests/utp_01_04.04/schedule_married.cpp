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


int main() {
  int t;
  scanf("%d", &t);

  for(int case_id = 1; case_id <= t; case_id++) {
    int whs, wms, whe, wme, fhs, fms, fhe, fme;
    scanf("%d:%d %d:%d", &whs, &wms, &whe, &wme);
    scanf("%d:%d %d:%d", &fhs, &fms, &fhe, &fme);

    int wts = whs * 60 + wms;
    int wte = whe * 60 + wme;
    int fts = fhs * 60 + fms;
    int fte = fhe * 60 + fme;

    printf("Case %d: ", case_id);
    if((wts <= fts && fts <= wte) || (wts <= fte && fte <= wte) || (fts <= wts && wte <= fte))
      printf("Mrs Meeting\n");
    else
      printf("Hits Meeting\n");
  }

  return 0;
}

