#include <bits/stdc++.h>

using namespace std;

int a[10], k, budget, n;

bool solve() {
  for (int i = 0; i < 1<<n; i++) {
    int card = __builtin_popcount(i);
    if (card == k) {
      int sum = 0;
      for (int j = 0; j < n; j++)
        if (i & (1<<j))
          sum += a[j];
      if (sum > budget) return false;
    }
  }
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n >> budget >> k) {
    if ((n|budget|k) == 0) break;
    for (int i = 0; i < n; i++)
      cin >> a[i];
    cout << (solve()? "YES" : "NO") << '\n';
  }

  return 0;
}
