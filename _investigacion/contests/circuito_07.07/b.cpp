#include <bits/stdc++.h>

using namespace std;

// Live Archive: 6151 - Beehives
// Algorithm: BFS Cycle detection, Cycle length

const int INF = int(1e9);
const int maxn = 510;

int ans, n;
vector<int> g[maxn];
int d[maxn], p[maxn];

int bfs(int u) {
  for (int i = 0; i < n; i++)
    d[i] = INF;

  int r = INF;
  queue<int> Q;

  d[u] = 0;
  p[u] = -1;
  Q.push(u);

  while (!Q.empty()) {
    u = Q.front();
    Q.pop();

    for (int i = 0; i < g[u].size(); i++) {
      int v = g[u][i];
      if (v != p[u]) {
        if (d[v] == INF) {
          d[v] = d[u]+1;
          p[v] = u;
          Q.push(v);
        }
        else {
          r = min(r, d[u] + d[v] + 1);
          if (r == 3) return r;
        }
      }
    }
  }

  return r;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t, m;
  cin >> t;

  for (int tc = 1; tc <= t; tc++) {
    cin >> n >> m;

    for (int i = 0; i < n; i++)
      g[i].clear();

    for (int i = 0, u, v; i < m; i++) {
      cin >> u >> v;
      g[u].push_back(v);
      g[v].push_back(u);
    }

    ans = INF;
    for (int i = 0; i < n && ans != 3; i++) {
      ans = min(ans, bfs(i));
    }

    cout << "Case " << tc << ": ";
    if (ans == INF) cout << "impossible\n";
    else cout << ans << '\n';
  }

  return 0;
}
