#include <cstring>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

const int maxn = 501;
const int INF = int(1e9);

int n, m, cnt[maxn][27], curr_cnt[27];

int dist[maxn<<1], match[maxn<<1], NIL;

vector<int> g[maxn];

bool bfs() {
  queue<int> Q;

  for (int v = 0; v < n; v++) {
    dist[v] = INF;
    if (match[v] == NIL) {
      dist[v] = 0;
      Q.push(v);
    }
  }

  dist[NIL] = INF;

  while (!Q.empty()) {
    int v = Q.front();
    Q.pop();
    if (dist[v] < dist[NIL]) {
      for (int i = 0; i < g[v].size(); i++) {
        int u = g[v][i];
        if (dist[match[u]] == INF) {
          dist[match[u]] = dist[v]+1;
          Q.push(match[u]);
        }
      }
    }
  }
  return dist[NIL] != INF;
}

bool dfs(int v) {
  if (v != NIL) {
    for (int i = 0; i < g[v].size(); i++) {
      int u = g[v][i];
      if (dist[match[u]] == dist[v]+1 && dfs(match[u])) {
        match[u] = v;
        match[v] = u;
        return true;
      }
    }
    dist[v] = INF;
    return false;
  }
  return true;
}

int hopcroft_karp() {
  NIL = n+m;
  for (int i = 0; i < n+m; i++)
    match[i] = NIL;

  int matching = 0;
  for (int v = 0; v < n; v++) {
    for (int i = 0; i < g[v].size(); i++) {
      int u = g[v][i];
      if (match[u] == NIL) {
        matching++;
        match[u] = v;
        match[v] = u;
        break;
      }
    }
  }

  while (bfs()) {
    for (int v = 0; v < n; v++)
      if (match[v] == NIL && dfs(v))
        matching++;
  }
  return matching;
}

bool possible(int j) {
  for (int k = 0; k <= 26; k++)
    if (cnt[j][k] < curr_cnt[k])
      return false;
  return true;
}

int main() {
#ifdef LOCAL
  freopen("vocabulary.in", "r", stdin);
  freopen("vocabulary.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
#endif

  while (cin >> m >> n) {
    memset(cnt, 0, sizeof(cnt));
    string s;
    for (int i = 0; i < m; i++) {
      cin >> s;
      for (int j = 0; j < s.size(); j++)
        cnt[i][s[j]-'a']++;
    }

    for (int i = 0; i < n; i++) {
      g[i].clear();
      memset(curr_cnt, 0, sizeof(curr_cnt));
      cin >> s;
      for (int j = 0; j < s.size(); j++)
        curr_cnt[s[j]-'a']++;
      for (int j = 0; j < m; j++) {
        if (possible(j)) {
          g[i].push_back(n+j); // left
        }
      }
    }

    cout << hopcroft_karp() << '\n';
  }

  return 0;
}
