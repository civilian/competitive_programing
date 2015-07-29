#include <bits/stdc++.h>

using namespace std;

const double EPS = 1e-9;

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

typedef priority_queue< pair<double,int>, vector< pair<double,int> >, greater< pair<double,int> > > PQ;

const int maxn = 510;

int n, m;
vector<edge> g[maxn];
double dist[maxn];
bool seen[maxn];

double dijkstra(int s, int t) {
  for (int i = 1; i <= n; i++) {
    dist[i] = 1e200;
    seen[i] = 0;
  }

  PQ q;
  dist[s] = 0;
  q.push(make_pair(0, s));

  while (!q.empty()) {
    int u = q.top().second;
    double cost = q.top().first;
    q.pop();

    if (u == t)
      return cost;

    if (seen[u]) continue;
    seen[u] = true;

    for (int i = 0; i < g[u].size(); i++) {
      int v = g[u][i].u;
      double p = g[u][i].p;

      if (compareTo(dist[u] + p, dist[v]) < 0) {
        dist[v] = dist[u] + p;
        q.push(make_pair(dist[v], v));
      }
    }
  }

  return 1e200;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cout.precision(4);
  cout.setf(ios::fixed);

  while (cin >> n >> m) {
    for (int i = 1; i <= n; i++)
      g[i].clear();

    for (int i = 0; i < m; i++) {
      int u, v;
      double p;
      cin >> u >> v >> p;
      if (compareTo(p, 0) == 0)
        continue;
      g[u].push_back(edge(v, -log10(p)));
    }

    double ans = dijkstra(1, n);

    if (compareTo(ans, 1e200) >= 0) {
      cout << "Impossible\n";
    } else {
      if (compareTo(ans, 0) != 0)
        ans = -ans;
      cout << "10^" << ans << '\n';
    }
  }

  return 0;
}
