#include <bits/stdc++.h>

using namespace std;

// WA!

const int maxn = 22;

int n, friends[maxn];
vector<int> g[maxn];
bool seen[maxn];

void count_friend() {
  memset(friends, 0, sizeof friends);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < g[i].size(); j++) {
      int v = g[i][j];
      friends[i] += seen[v] == 0;
    }
    friends[i] += seen[i] == 0;
  }
}

bool check() {
  for (int i = 0; i < n; i++)
    if (!seen[i])
      return false;
  return true;
}

int solve() {
  int ans = 0;
  while (!check()) {
    count_friend();
    vector< pair<int,int> > ord;
    for (int i = 0; i < n; i++)
      ord.push_back(make_pair(friends[i], i));
    sort(ord.rbegin(), ord.rend());

    int u = ord[0].second;
    seen[u] = true;
    for (int i = 0; i < g[u].size(); i++) {
      int v = g[u][i];
      seen[v] = true;
    }
    ans++;
  }
  return ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    cin >> n;
    for (int i = 0; i < n; i++)
      g[i].clear();

    for (int i = 0; i < n; i++) {
      int m;
      cin >> m;
      for (int j = 0, v; j < m; j++) {
        cin >> v;
        v--;
        g[i].push_back(v);
        g[v].push_back(i);
      }
    }

    memset(seen, 0, sizeof seen);
    cout << solve() << endl;
  }

  return 0;
}
