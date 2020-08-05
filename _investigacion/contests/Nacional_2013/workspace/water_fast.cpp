#include <cstring>
#include <iostream>
#include <vector>

#define min(a,b) ((a) < (b))? (a) : (b)

using namespace std;

const int maxn = 1010;

int n, m, total_vertex, numSCC, dfsNumberCounter;
vector<int> g[maxn];
int in_degree[maxn];
int comp[maxn];

int dfs_num[maxn], dfs_low[maxn], S[maxn<<1], sh;
bool visited[maxn];

void tarjanSCC(int u) {
  dfs_low[u] = dfs_num[u] = dfsNumberCounter++;
  S[sh++] = u;

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
      int v = S[--sh];
      visited[v] = 0;
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

    for (int i = 0, u, v; i < m; i++) {
      cin >> u >> v;
      g[u].push_back(v);
    }

    total_vertex = n+1;
    memset(dfs_num, -1, total_vertex*sizeof(int));
    memset(dfs_low, 0, total_vertex*sizeof(int));
    memset(visited, 0, total_vertex);

    dfsNumberCounter = numSCC = 0;
    sh = 0;

    for (int i = 0; i < total_vertex; i++)
      if (dfs_num[i] < 0)
        tarjanSCC(i);

    memset(in_degree, 0, numSCC*sizeof(int));

    int ans = numSCC;

    for (int u = 0; u < total_vertex; u++) {
      for (int j = 0; j < g[u].size(); j++) {
        int v = g[u][j];
        if (comp[v] != comp[u]) {
          if (in_degree[comp[v]] == 0)
            ans--;
          in_degree[comp[v]]++;
        }
      }
    }

    if (in_degree[comp[0]] == 0)
      ans--;

    cout << ans << '\n';
  }

  return 0;
}
