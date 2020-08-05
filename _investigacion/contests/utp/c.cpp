#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);

const int maxn = 1010;

int n;
int start[maxn], end[maxn], min_dist[maxn], max_dist[maxn];
vector<int> g[maxn];

void dfs(int u = 0) {
  if(min_dist[u] != -1)
    return;

  if(g[u].empty()) {
    min_dist[u] = max_dist[u] = (end[u] - start[u] + 1);
    return;
  }

  int mmin = INF, mmax = -INF;

  for(int i = 0; i < g[u].size(); i++) {
    dfs(g[u][i]);
    int d = (end[u] - start[u] + 1);
    mmin = min(mmin, d + min_dist[g[u][i]]);
    mmax = max(mmax, d + max_dist[g[u][i]]);
  }

  min_dist[u] = mmin;
  max_dist[u] = mmax;
}

int main() {
  int t;
  scanf("%d", &t);

  for(int run = 1; run <= t; run++) {
    scanf("%d", &n);

    for(int i = 0; i < n; i++)
      g[i].clear();

    map<int,int> sections;
    map<int,int>::iterator it;

    for(int i = 0; i < n; i++) {
      int s, e, p;
      scanf("%d%d%d", &s, &e, &p);
      start[i] = s; end[i] = e;

      if(p != -1)
        g[p].push_back(i);

      sections[e] = i;
    }

    memset(min_dist, -1, n*sizeof(int));
    dfs();

    int q;
    scanf("%d", &q);

    printf("Caso %d:\n", run);
    while(q--) {
      int u;
      scanf("%d", &u);
      it = sections.lower_bound(u);
      int i = it->second;

      int d = end[i] - start[i] + 1;
      int min_ans = min_dist[i] - d + end[i] - u + 1;
      int max_ans = max_dist[i] - d + end[i] - u + 1;

      printf("%d %d\n", min_ans, max_ans);
    }
  }

  return 0;
}
