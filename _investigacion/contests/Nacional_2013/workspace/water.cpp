#include <cstring>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

const int maxn = 1010;

int n, m, total_vertex, numSCC, dfsNumberCounter;
vector<int> g[maxn];
int in_degree[maxn];
int comp[maxn];

vector<int> dfs_num, dfs_low, S, visited;

void tarjanSCC(int u) {
  dfs_low[u] = dfs_num[u] = dfsNumberCounter++;
  S.push_back(u);
  visited[u] = 1;
  for (int j = 0; j < (int)g[u].size(); j++) {
    int v = g[u][j];
    if (dfs_num[v] < 0)
      tarjanSCC(v);
    if (visited[v])
      dfs_low[u] = min(dfs_low[u], dfs_low[v]);
  }

  if (dfs_low[u] == dfs_num[u]) {
    while (true) {
      int v = S.back(); S.pop_back(); visited[v] = 0;
      comp[v] = numSCC;
      if (u == v) break;
    }
    numSCC++;
  }
}

int main() {
#ifdef LOCAL
  freopen("water.in", "r", stdin);
  freopen("water.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
#endif

  while (cin >> n >> m) {
    for (int i = 0; i <= n; i++)
      g[i].clear();
    memset(in_degree, 0, sizeof(in_degree));

    for (int i = 0; i < m; i++) {
      int u, v;
      cin >> u >> v;
      g[u].push_back(v);
    }

    total_vertex = n+1;
    dfs_num.assign(total_vertex, -1);
    dfs_low.assign(total_vertex, 0);
    visited.assign(total_vertex, 0);
    dfsNumberCounter = numSCC = 0;

    for (int i = 0; i < total_vertex; i++) {
      if (dfs_num[i] < 0) {
        tarjanSCC(i);
      }
    }

    for (int i = 0; i < total_vertex; i++) {
      int u = i;
      for (int j = 0; j < g[u].size(); j++) {
        int v = g[u][j];
        if (comp[v] != comp[u]) {
          in_degree[comp[v]]++;
        }
      }
    }

    int ans = 0;
    for (int i = 0; i < numSCC; i++) {
      if (in_degree[i] == 0)
        ans++;
    }
    if (in_degree[comp[0]] == 0)
      ans--;

    cout << ans << '\n';
  }

  return 0;
}
