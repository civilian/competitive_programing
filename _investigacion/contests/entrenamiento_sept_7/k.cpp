#include <bits/stdc++.h>

using namespace std;

bool used[1010];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    int m, k, g;
    cin >> m >> g >> k;

    memset(used, 0, sizeof(used));

    for (int i = 0; i < m; i += k)
      if(i) used[i] = 1;
    for (int i = 0; i < m; i += g)
      if(i) used[i] = 1;
    cout << m << ' ' << g << ' ' << k << '\n';
    cout << count(used, used+m+1, 1) << '\n';
  }

  return 0;
}
