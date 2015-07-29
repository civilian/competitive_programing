#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);
const int maxn = 1010;

int n, K, tropes[maxn], a[maxn];
vector<int> g[maxn];
bool adj[maxn][maxn];

struct node {
  int index, value;
  node(int i=0, int v=0) :
    index(i), value(v) {}

  bool operator<(const node& x) const {
    return value < x.value;
  }
} t[maxn << 2];

void build(int v, int tl, int tr) {
  if (tl == tr) {
    t[v] = node(tl, a[tl]);
  }
  else {
    int tm = (tl + tr) >> 1;
    int next = v << 1;

    build(next, tl, tm);
    build(next+1, tm+1, tr);

    t[v] = min(t[next], t[next+1]);
  }
}

void update(int v, int tl, int tr, int pos, int val) {
  if (tl == tr) {
    t[v] = node(tl, val);
  }
  else {
    int tm = (tl + tr) >> 1;
    int next = v << 1;

    if (pos <= tm)
      update(next, tl, tm, pos, val);
    else
      update(next+1, tm+1, tr, pos, val);

    t[v] = min(t[next], t[next+1]);
  }
}

node query(int v, int tl, int tr, int l, int r) {
  if (l > tr || r < tl)
    return node(INF, INF);

  if (l <= tl && tr <= r)
    return t[v];

  int tm = (tl + tr) >> 1;
  int next = v << 1;

  node left_part = query(next, tl, tm, l, r);
  node right_part = query(next+1, tm+1, tr, l, r);

  return min(left_part, right_part);
}

void solve() {
  // build segment tree
  build(1, 0, n-1);

  int ans = 0, sum = 0;

  while (true) {
    node rmq = query(1, 0, n-1, 0, n);

    if (rmq.value >= K)
      break;

    int u = rmq.index;
    // remove city
    for (int j = 0; j < (int)g[u].size(); j++) {
      int v = g[u][j];
      if (adj[u][v]) {
        a[v] -= tropes[u];
        adj[u][v] = adj[v][u] = 0;
        update(1, 0, n-1, v, a[v]);
      }
    }
    a[u] = 0;
    update(1, 0, n-1, u, INF);
  }

  for (int i = 0; i < n; i++) {
    if (a[i] != 0) {
      ans++;
      sum += tropes[i];
    }
  }
  cout << ans << ' ' << sum << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n >> K) {
    if ((n|K) == 0)
      break;

    memset(adj, 0, sizeof(adj));
    for (int i = 0; i < n; i++)
      g[i].clear();

    for (int i = 0, m; i < n; i++) {
      cin >> tropes[i] >> m;
      for (int j = 0, v; j < m; j++) {
        cin >> v;
        g[i].push_back(v);
        adj[i][v] = 1;
      }
    }

    for (int i = 0; i < n; i++) {
      a[i] = tropes[i];
      for (int j = 0; j < (int)g[i].size(); j++) {
        int v = g[i][j];
        a[i] += tropes[v];
      }
    }

    solve();
  }

  return 0;
}
