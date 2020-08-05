#include <bits/stdc++.h>

using namespace std;

struct edge {
  int to;
  string s;
  edge(int t1=0, string t="") :
    to(t1), s(t) {}
};

const int INF = int(1e9);
const int maxn = 33;

int n, e, dist[maxn];
set<string> invalids;
vector<edge> g[maxn];

bool bfs(int s, int t) {
  for (int i = 0; i < n; i++)
    dist[i] = INF;

  queue<int> q;
  q.push(s);
  dist[s] = 0;

  while (!q.empty()) {
    int u = q.front();
    q.pop();

    if (u == t)
      return true;

    for (int i = 0; i < g[u].size(); i++) {
      int v = g[u][i].to;
      string m = g[u][i].s;

      if (dist[v] == INF) {
        if (!invalids.count(m)) {
          dist[v] = dist[u]+1;
          q.push(v);
        }
      }
    }
  }

  return 0;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int tc;
  cin >> tc;

  while (tc--) {
    int t;
    cin >> t;

    invalids.clear();
    for (int i = 0; i < t; i++) {
      string s;
      cin >> s;
      invalids.insert(s);
    }

    for (int i = 0; i < n; i++)
      g[i].clear();

    cin >> n >> e;
    for (int i = 0; i < e; i++) {
      int u, v;
      string s;
      cin >> u >> v >> s;
      g[u].push_back(edge(v,s));
      g[v].push_back(edge(u,s));
    }

    cout << bfs(0,n-1) << '\n';
  }

  return 0;
}
