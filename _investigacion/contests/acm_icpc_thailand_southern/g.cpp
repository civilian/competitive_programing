#include <bits/stdc++.h>

using namespace std;

const int maxn = 100010;

long long sum[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    int N, Q;
    cin >> N >> Q;

    for (int i = 0, v; i < N; i++) {
      cin >> v;
      sum[i] = v;
      if (i) sum[i] += sum[i-1];
    }

    while (Q--) {
      int l, r;
      cin >> l >> r;
      long long ans = sum[r];
      if (l) ans -= sum[l-1];
      cout << ans << '\n';
    }
    if (t) cout << '\n';
  }

  return 0;
}
