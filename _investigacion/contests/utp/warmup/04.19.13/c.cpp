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
#define foreach(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

char a[30], _s[100];

int main() {
  ios_base::sync_with_stdio(false);
  int t;
  cin >> t;

  while(t--) {
    int n, m;
    cin >> n >> m;

    for(int i = 0; i < n; i++)
      cin >> a[i];

    cin >> _s;

    map<char,int> cnt;
    for(int i = 0; i < m; i++)
      cnt[_s[i]]++;

    vector< pair<int, char> > v;
    foreach(it, cnt) {
      v.push_back(make_pair(it->second, it->first));
    }
    sort(v.rbegin(), v.rend());

    for(int i = 0; i < v.size(); i++)
      cnt[v[i].second] = i;

    string t(m, ' ');
    for(int i = 0; i < m; i++) {
      t[i] = a[cnt[_s[i]]];
    }
    cout << t << endl;
  }

  return 0;
}

