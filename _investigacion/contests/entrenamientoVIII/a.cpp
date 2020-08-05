#include <bits/stdc++.h>

using namespace std;

int n, m;
int stickers[33], s2[33];

struct price {
  int cash, k, min_sticker;
  int a[30];

  void get_min() {
    min_sticker = -1;
    for (int i = 0; i < k; i++) {
      if (min_sticker == -1 || s2[min_sticker] > s2[a[i]])
        min_sticker = a[i];
    }
  }
};

price P[11];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
      cin >> P[i].k;
      for (int j = 0; j < P[i].k; j++)
        cin >> P[i].a[j];
      cin >> P[i].cash;
    }

    for (int i = 1; i <= m; i++)
      cin >> stickers[i];

    long long ans = 0;

    for (int mask = 0; mask < (1 << n); mask++) {
      for (int i = 1; i <= m; i++)
        s2[i] = stickers[i];

      long long sum = 0;
      for (int i = 0; i < n; i++) {
        if (mask & (1 << i)) {
          price & p = P[i];
          p.get_min();
          sum += p.cash * s2[p.min_sticker];

          int d = s2[p.min_sticker];
          if (d == 0) continue;
          for (int j = 0; j < p.k; j++) {
            s2[p.a[j]] -= d;
          }
        }
      }
      ans = max(ans, sum);
    }

    cout << ans << '\n';
  }

  return 0;
}
