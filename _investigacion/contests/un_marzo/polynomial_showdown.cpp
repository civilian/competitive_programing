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

int coef[9];

int main() {
  ios_base::sync_with_stdio(false);

  while(cin >> coef[8]) {
    for(int i = 7; i >= 0; i--)
      cin >> coef[i];
    bool first = true;
    for(int p = 8; p >= 0; p--) {
      if(coef[p] == 0) { // just print coef[0] when all other are 0
        if(p == 0 && first) {
          first = false;
          printf("%d", coef[p]);
        }
        continue;
      }

      if(p == 0) {
        if(first) printf("%d", coef[p]);
        else {
          if(coef[p] < 0) printf(" - ");
          else printf(" + ");
          coef[p] = abs(coef[p]);
          printf("%d", coef[p]);
        }
        continue;
      }

      if(first) {
        if(abs(coef[p]) != 1) printf("%d", coef[p]);
        else if(coef[p] == -1) printf("-");
        printf("x");
        if(p > 1) printf("^%d", p);
        first = false;
      }
      else {
        if(coef[p] < 0) printf(" - ");
        else printf(" + ");
        coef[p] = abs(coef[p]);
        if(coef[p] != 1) printf("%d", coef[p]);
        printf("x");
        if(p > 1) printf("^%d", p);
      }
    }
    printf("\n");
  }
  return 0;
}

