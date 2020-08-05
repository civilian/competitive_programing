#include <iostream>
#include <sstream>
#include <algorithm>

using namespace std;

int main() {
#ifdef LOCAL
  freopen("tennis.in", "r", stdin);
  freopen("tennis.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
#endif
  int n, i, j;
  while (cin >> n >> i >> j) {
    if (i > j) swap(i, j);

    i = (i+1) >> 1;
    j = (j+1) >> 1;

    int ans = 1;
    while (i != j) {
      i = (i+1) >> 1;
      j = (j+1) >> 1;
      ans++;
    }

    cout << ans << '\n';
  }

  return 0;
}
