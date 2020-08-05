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

string names[1010];
int t[1010];

int p[1010], rank[1010];

void init(int n) {
  for(int i = 0; i < n; i++) {
    p[i] = i;
    rank[i] = 0;
  }
}

int find(int x) {
  return (x == p[x])? x : p[x] = find(p[x]);
}

void unite(int x, int y) {
  int px = find(x);
  int py = find(y);

  if(px == py)
    return;

  if(rank[px] < rank[py]) {
    p[px] = py;
  }
  else {
    p[py] = px;
    if(rank[px] == rank[py])
      rank[px]++;
  }
}

map<string,int> g;
map<int,int> best;

void solve(int n) {
  best.clear();
  for(int i = 0; i < n; i++) {
    int curr = find(i);
    if(!best.count(curr))
      best[curr] = i;
    else {
      if(t[i] > t[best[curr]])
        best[curr] = i;
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);

  int n, m;

  while(cin >> n >> m) {
    if(!(n|m)) break;

    g.clear();
    for(int i = 0; i < n; i++) {
      cin >> names[i] >> t[i];
      g[names[i]] = i;
    }

    init(n);

    for(int i = 0; i < m; i++) {
      string a, b;
      cin >> a >> b;
      unite(g[a], g[b]);
    }

    solve(n);
    vector<string> ans;

    foreach(it, best)
      ans.push_back(names[it->second]);
    sort(ans.begin(), ans.end());

    foreach(x, ans)
      cout << *x << endl;
  }

  return 0;
}

