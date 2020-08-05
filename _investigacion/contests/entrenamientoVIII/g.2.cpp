#include <bits/stdc++.h>

using namespace std;

#define LSOne(S) (S & (-S))

const int maxn = 22;

int n, friends[maxn];
int g[1 << maxn];

void print(int x) {
  string t;
  while (x) {
    t.push_back('0' + (x & 1));
    x >>= 1;
  }
  reverse(t.begin(), t.end());
  cout << t << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    cin >> n;

    memset(g, 0, sizeof g);
    for (int i = 0; i < n; i++) {
      int m;
      cin >> m;
      for (int j = 0, v; j < m; j++) {
        cin >> v;
        v--;
        g[1 << i] |= (1 << v);
        g[1 << v] |= (1 << i);
      }
      g[1 << i] |= (1 << i);
    }

    int best = int(1e9);
    for (int mask = 0; mask < (1 << n); mask++) {
      int posts = 0;

      for (int curr = mask; curr > 0; curr -= LSOne(curr))
        posts |= g[LSOne(curr)];

      int card = __builtin_popcount(posts);
      if (card == n)
        best = min(best, __builtin_popcount(mask));
    }
    cout << best << '\n';
  }

  return 0;
}
