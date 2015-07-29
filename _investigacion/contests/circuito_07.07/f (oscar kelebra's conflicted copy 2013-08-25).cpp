#include <bits/stdc++.h>

using namespace std;

const int maxn = 1010;

int n, k;
vector<int> g[maxn];

int r[maxn], c[maxn][maxn];
bool col[maxn];

string names[maxn];

void solve() {
  for (int u = 0; u < n; u++) {
    for (int j = 0; j < g[u].size(); j++) {
      int v = g[u][j];
      if (names[u] == names[v])
        col[v] = 1;
      r[v]++;
      c[v][u]++;
      if (c[v][u] > 1)
        col[v] = 1;
    }
  }

  int p = 0;
  for (int u = 0; u < n; u++) {
    if (col[u] || r[u] != k)
      p++;
  }

  if (p == 0) cout << "NO"; else cout << p;
  cout << " PROBLEM"; if (p != 1) cout << "S";  cout << " FOUND\n";
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> k >> n) {
    if ((n|k) == 0)
      break;

    for (int i = 0; i < n; i++) {
      cin >> names[i];
      g[i].clear();
      for (int j = 0, u; j < k; j++) {
        cin >> u;
        g[i].push_back(u-1);
      }
    }

    memset(r, 0, n*sizeof(int));
    memset(col, 0, n);
    memset(c, 0, sizeof(c));

    solve();
  }

  return 0;
}
