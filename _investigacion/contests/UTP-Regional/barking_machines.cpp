#include <bits/stdc++.h>

using namespace std;

// WA

const double EPS = 1e-4;

inline
int compareTo(double x, double y, double tol=EPS) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

struct edge {
  int u;
  double p;
  edge(int u = 0, double p = 0) :
    u(u), p(p) { }
};

const int maxn = 510;

int n, m;
vector<edge> g[maxn];
double safety[maxn];
bool seen[maxn];

double dfs(int u) {
  if (seen[u])
    return safety[u];

  seen[u] = true;

  for (int i = 0; i < g[u].size(); i++) {
    int v = g[u][i].u;
    double p = g[u][i].p;
    double curr = dfs(v) + p;
    if (compareTo(safety[u], curr) < 0)
      safety[u] = curr;
  }
  return safety[u];
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cout.precision(4);
  cout.setf(ios::fixed);

  while (cin >> n >> m) {
    for (int i = 1; i <= n; i++) {
      g[i].clear();
    }

    for (int i = 0; i < m; i++) {
      int u, v;
      double p;
      cin >> u >> v >> p;
      if (p == 0)
        continue;
      g[v].push_back(edge(u, -log10(p)));
    }
    memset(seen, 0, sizeof seen);

    double ans = dfs(n);
    if (compareTo(ans, 100) >= 0) {
      cout << "Impossible\n";
    } else {
      cout << "10^" << ans << '\n';
    }
  }

  return 0;
}
