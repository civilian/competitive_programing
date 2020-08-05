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

int n, m, min_dist[1<<15], switches[44];
char _s[20];

void bfs(int start) {
  memset(min_dist, -1, sizeof min_dist);
  queue<int> q;

  q.push(start);
  min_dist[start] = 0;

  while(!q.empty()) {
    int u = q.front();
    q.pop();

    for(int i = 0; i < m; i++) {
      int v = u ^ switches[i];
      if(min_dist[v] < 0) {
        min_dist[v] = min_dist[u] + 1;
        q.push(v);
      }
    }
  }
}

int main() {
  int t;
  scanf("%d", &t);

  for(int case_id = 1; case_id <= t; case_id++) {
    scanf("%d%d", &n, &m);

    for(int i = 0; i < m; i++) {
      switches[i] = 0;
      int k;
      scanf("%d", &k);
      for(int j = 0; j < k; j++) {
        int x;
        scanf("%d", &x);
        switches[i] |= (1 << x);
      }
    }

    bfs(0);

    printf("Case %d:\n", case_id);

    int q;
    scanf("%d", &q);

    while(q--) {
      scanf("%s", _s);
      int pos = strtol(_s, NULL, 2);
      printf("%d\n", min_dist[pos]);
    }
    printf("\n");
  }

  return 0;
}

