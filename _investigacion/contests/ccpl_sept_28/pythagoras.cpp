#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  long long a;

  while (cin >> a) {
    if (a == 0)
      break;

    long long a2 = a * a;
    int ans = 0;

    for (long long x = 1; 2 * x <= a; x++) {
      if (a2 % x == 0) {
        long long y = a2 / x;
        if ((y - x) % 2 == 0) {
          long long b = (y - x) / 2;
          if (b > a)
            ans++;
        }
      }
    }

    cout << ans << '\n';
  }

  return 0;
}
