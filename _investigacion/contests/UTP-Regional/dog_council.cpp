#include <bits/stdc++.h>

using namespace std;

const int maxn = 110;

int N, A[maxn], B[maxn], C[maxn];
vector<int> g[maxn];
bool invalid[maxn];

void dfs(int u) {
  invalid[u] = true;
  for (int i = 0; i < g[u].size(); i++)
    dfs(g[u][i]);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> N) {
    if (N == 0)
      break;

    for (int i = 1; i <= N; i++)
      g[i].clear();

    memset(invalid, 0, sizeof invalid);
    for (int i = 1; i <= N; i++) {
      cin >> A[i] >> B[i] >> C[i];
      invalid[i] = B[i];
      if (A[i] > 0)
        g[A[i]].push_back(i);
    }

    for (int i = 1; i <= N; i++)
      if (A[i] < 0 && B[i] == 1)
        dfs(i);

    int ans = 0;
    for (int i = 1; i <= N; i++) {
      if (C[i] == 1 && invalid[i])
        ans++;
    }
    cout << ans << '\n';
  }

  return 0;
}
