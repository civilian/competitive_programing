#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int n, t1, t2, t3;
  while (cin >> n) {
    if (n == 0)
      break;
    cin >> t1 >> t2 >> t3;

    int ans = 3 * n;
    ans += n-1;

    if (t2 < t1) ans += t2 + (n-t1);
    else ans += t2 - t1;

    if (t2 < t3) ans += t2 + (n-t3);
    else ans += t2 - t3;

    cout << ans << '\n';
  }

  return 0;
}
