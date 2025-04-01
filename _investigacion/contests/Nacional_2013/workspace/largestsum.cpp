#include <iostream>
#include <sstream>

using namespace std;

const int maxn = int(1e5) + 10;

int a[maxn];

int main() {
#ifdef LOCAL
  freopen("largestsum.in", "r", stdin);
  freopen("largestsum.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
#endif

  string ln;
  while (getline(cin, ln)) {
    stringstream ss(ln);
    int x, n = 0;
    while (ss >> x)
      a[n++] = x;

    int ans = 0, current_sum = 0;
    for (int j = 0; j < n; j++) {
      current_sum += a[j];
      if (current_sum > ans)
        ans = current_sum;
      if (current_sum < 0)
        current_sum = 0;
    }
    cout << ans << '\n';
  }
  return 0;
}
