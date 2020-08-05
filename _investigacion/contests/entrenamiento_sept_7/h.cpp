#include <bits/stdc++.h>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

const int maxn = 20010;

int n, cities, indegree[maxn];
map<string,int> names;
priority_queue< pair<int,int>, vector< pair<int,int> >, greater< pair<int,int> > > pq;
vector< pair<int,int> > g[maxn];

void solve() {
  vector<int> ans;
  while (!pq.empty()) {
    int u = pq.top().second;
    int cost = pq.top().first;
    pq.pop();

    ans.push_back(cost);
    indegree[u]--;

    if (indegree[u] == 0) {
      for (int i = 0; i < g[u].size(); i++)
        pq.push(g[u][i]);
    }
  }

  for (int i = 0; i < ans.size(); i++) {
    if (i) cout << ' ';
    cout << ans[i];
  }
  cout << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    cin >> n;
    cities = 0;

    names.clear();
    while (!pq.empty()) pq.pop();

    for (int i = 0, x; i < n; i++) {
      string s, t;
      cin >> s >> t >> x;
      if (!names.count(s))
        names[s] = cities++;
      if (!names.count(t))
        names[t] = cities++;

      int u = names[s];
      int v = names[t];
      g[u].push_back(make_pair(x, v));
      indegree[v]++;
    }

    for (int i = 0; i < cities; i++) {
      if (indegree[i] == 0) {
        for (int j = 0; j < g[i].size(); j++)
          pq.push(g[i][j]);
      }
    }

    solve();

    for (int i = 0; i < cities; i++) {
      g[i].clear();
      indegree[i] = 0;
    }
  }

  return 0;
}
