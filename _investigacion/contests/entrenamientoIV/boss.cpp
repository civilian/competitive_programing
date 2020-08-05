#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);

const int maxn = 510;

struct person {
  bool bosses[maxn];
} a[maxn];

int N, M, I, ages[maxn];
vector<int> g[maxn];
bool seen[maxn];

void dfs(int u) {
  if (seen[u])
    return;

  seen[u] = true;

  for (int i = 0; i < g[u].size(); i++) {
    int v = g[u][i];
    dfs(v);
    a[u].bosses[v] = true;
    for (int i = 0; i < N; i++)
      if (a[v].bosses[i])
        a[u].bosses[i] = true;
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> N >> M >> I) {
    for (int i = 0; i < N; i++) {
      g[i].clear();
      memset(a[i].bosses, 0, N);
      cin >> ages[i];
    }

    for (int i = 0; i < M; i++) {
      int u, v;
      cin >> u >> v;
      g[v-1].push_back(u-1);
    }

    memset(seen, 0, sizeof(seen));
    for (int i = 0; i < N; i++)
      if (!seen[i]) dfs(i);

    while (I--) {
      char op;
      int u, v;
      cin >> op;
      if (op == 'T') {
        cin >> u >> v;
        u--, v--;
        for (int i = 0; i < N; i++) {
          bool ch1 = a[i].bosses[u];
          bool ch2 = a[i].bosses[v];
          if (ch1) {
            a[i].bosses[v] = true;
            a[i].bosses[u] = ch2;
          }
          if (ch2) {
            a[i].bosses[u] = true;
            a[i].bosses[v] = ch1;
          }
        }
        swap(a[u].bosses, a[v].bosses);
      }
      else {
        cin >> u;
        u--;
        int ans = INF;
        for (int i = 0; i < N; i++)
          if (a[u].bosses[i])
            ans = min(ans, ages[i]);
        if (ans == INF) cout << "*\n";
        else cout << ans << '\n';
      }
    }
  }

  return 0;
}
