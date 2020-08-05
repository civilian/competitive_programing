#include <bits/stdc++.h>

using namespace std;

const int maxn = int(2e4)+10;
const int max_edges = int(1e5);

struct edge {
  int from, to;
  long long cost;
  edge(int f=0, int t=0, long long c=0) :
    from(f), to(t), cost(c) {}
  bool operator<(const edge& e) const {
    return cost > e.cost;
  }
} E[max_edges];

int N, M, S;
vector< pair<int,int> > g[maxn];

int pset[maxn], _rank[maxn];

void init(int n) { for (int i = 0; i < n; i++) pset[i]=i, _rank[i] = 0; }
int find(int x) { return (x == pset[x])? x : pset[x]=find(pset[x]); }
void unite(int x, int y) {
  int px = find(x), py = find(y);
  if (px == py) return;
  if (_rank[px] < _rank[py])
    pset[px] = py;
  else {
    pset[py] = px;
    if (_rank[px] == _rank[py])
      _rank[px]++;
  }
}
bool same_set(int a, int b) {
  return find(a) == find(b);
}

void kruskal() {
  int done = 0;

  for (int i = 0; i < M && done < N-1; i++) {
    int u = E[i].from, v = E[i].to;
    if (!same_set(u, v)) {
      unite(u,v);
      g[u].push_back(make_pair(v, E[i].cost));
      g[v].push_back(make_pair(u, E[i].cost));
      done++;
    }
  }
}

int L[maxn], T[maxn], P[maxn][20], Q[maxn][20], minEdge[maxn];

void build_tree(int u, int level, int p = -1) {
  L[u] = level;
  for (int i = 0; i < g[u].size(); i++) {
    int v = g[u][i].first;
    if (v != p) {
      T[v] = u;
      minEdge[v] = g[u][i].second;
      build_tree(v, level+1, u);
    }
  }
}

const int INF = int(1e9);

void preprocess() {
  for(int i = 0; i < N; i++)
    for(int j = 0; (1 << j) < N; j++) {
      P[i][j] = -1;
      Q[i][j] = INF;
    }

  //the first ancestor of every node i is T[i]
  for(int i = 0; i < N; i++) {
    P[i][0] = T[i];
    Q[i][0] = minEdge[i];
  }

  for(int j = 1; (1 << j) < N; j++)
    for(int i = 0; i < N; i++)
      if(P[i][j-1] != -1) {
        P[i][j] = P[P[i][j-1]][j-1];
        Q[i][j] = min(Q[i][j-1], Q[P[i][j-1]][j-1]);
      }
}

pair<int,int> query(int p, int q) {
  int lg, i;

  //if p is situated on a higher level than q then we swap them
  if(L[p] < L[q])
    swap(p, q);

  //we compute the value of [log(L[p)]
  for(lg = 1; (1 << lg) <= L[p]; lg++);
  lg--;

  int min_edge = INF;
  //we find the ancestor of node p situated on the same level
  //with q using the values in P
  for(int i = lg; i >= 0; i--) {
    if(L[p] - (1 << i) >= L[q]) {
      min_edge = min(min_edge, Q[p][i]);
      p = P[p][i];
    }
  }

  if(p == q) {
    return make_pair(p, min_edge);
  }

  //we compute LCA(p, q) using the values in P
  for(int i = lg; i >= 0; i--) {
    if(P[p][i] != -1 && P[p][i] != P[q][i]) {
      min_edge = min(min_edge, min(Q[p][i], Q[q][i]));
      p = P[p][i];
      q = P[q][i];
    }
  }

  min_edge = min(min_edge, min(minEdge[p], minEdge[q]));
  return make_pair(T[p], min_edge);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> N >> M >> S) {
    for (int i = 0; i < M; i++) {
      int from, to, cost;
      cin >> from >> to >> cost;
      E[i] = edge(from-1,to-1,cost);
    }
    sort(E, E+M);

    for (int i = 0; i < N; i++)
      g[i].clear();

    init(N);
    kruskal();
    minEdge[0] = int(1e9);
    build_tree(0, 0);
    preprocess();

    while (S--) {
      int u, v;
      cin >> u >> v;
      pair<int,int> ans = query(u-1, v-1);
      cout << ans.second << '\n';
    }
  }

  return 0;
}
