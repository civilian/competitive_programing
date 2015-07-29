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

// Algorithm: Little Fermat theorem for primality test

typedef unsigned long long ULL;

ULL mulmod(ULL a, ULL b, ULL c){
  ULL x = 0,y = a%c;

  while(b>0){
    if(b&1) x = (x+y)%c;
    y = (y<<1)%c;
    b >>= 1;
  }

  return x;
}

ULL pow(ULL a, ULL b, ULL c){
  ULL x = 1, y = a;

  while(b>0){
    if(b&1) x = mulmod(x,y,c);
    y = mulmod(y,y,c);
    b >>= 1;
  }

  return x;
}

bool little_fermat(ULL p, int k) {
  if(p < 2) return false;
  if(p == 2) return true;
  if((p&1) == 0) return false;

  while(k--) {
    ULL a = rand() % (p-1) + 1;
    ULL mod = pow(a, p-1, p);
    if(mod % p != 1) return false;
  }
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);

  ULL n;
  int t;
  cin >> t;

  while(t--) {
    cin >> n;
    cout << (little_fermat(n, 10)? "YES\n": "NO\n");
  }

  return 0;
}

