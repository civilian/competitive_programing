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

bool has_timina(string s) {
  int n = s.size();
  for(int i = 0; i < n; i++)
    if(s[i] == 'T')
      return true;
  return false;
}

bool can(string a, string b) {
  return a.find(b) != string::npos;
}

int main() {
  ios_base::sync_with_stdio(false);

  int t;
  cin >> t;

  while(t--) {
    string a, b;
    cin >> a >> b;

    cout << ((can(a,b) && has_timina(a))? 'S' : 'N') << endl;
  }

  return 0;
}

