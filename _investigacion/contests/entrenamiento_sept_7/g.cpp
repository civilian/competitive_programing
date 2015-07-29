#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    int c, p;
    cin >> c >> p;

    int ans = p;
    for (int i = 1; i < c; i++)
      ans += p - 2;

    cout << c << ' ' << p << '\n';
    cout << ans << '\n';
  }

  return 0;
}
