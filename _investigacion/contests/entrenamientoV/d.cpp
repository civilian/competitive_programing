#include <bits/stdc++.h>

using namespace std;

const int maxn = 10010;

int n, a[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n) {
    long long sum = 0;
    for (int i = 0; i < n; i++) {
      cin >> a[i];
      sum += a[i];
    }

    if (sum % n)
      cout << -1 << '\n';
    else {
      sum /= n;

      long long ans = 1;
      for (int i = 0; i < n; i++) {
        if (a[i] < sum)
          ans += sum - a[i];
      }

      cout << ans << '\n';
    }
  }

  return 0;
}
