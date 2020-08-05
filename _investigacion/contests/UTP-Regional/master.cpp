#include <bits/stdc++.h>

using namespace std;

int n, m, s, k, h, a[26];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n >> m >> s >> k >> h) {
    for (int i = 0; i < m; i++) {
      string t;
      cin >> t;
      a[i] = strtol(t.c_str(), NULL, 2);
    }

    int ans = -1;
    for (int subset = 0; subset < (1 << n); subset++) {
      int card = __builtin_popcount(subset);
      if (card == s) {
        int curr = 0;
        bool ok = true;
        for (int i = 0; i < m; i++) {
          int hi = min(h, __builtin_popcount((subset & a[i])));
          curr += hi;
          if (hi < k)
            ok = false;
        }
        if (ok)
          ans = max(ans, curr);
      }
    }
    if (ans < 0) cout << "Impossible\n";
    else cout << ans << '\n';
  }

  return 0;
}
