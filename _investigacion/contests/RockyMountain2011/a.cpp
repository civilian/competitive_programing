#include <bits/stdc++.h>

using namespace std;

const int maxn = 22;

int n, a[maxn], b[maxn];

bool check() {
  for (int i = 1; i < n; i++)
    if (a[i] != a[i-1])
      return false;
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int tc = 1;
  while (cin >> n) {
    if (n == 0)
      break;
    for (int i = 0; i < n; i++)
      cin >> a[i];
    int ans = -1;

    for (int iter = 0; iter <= 1000; iter++) {
     if (check()) {
        ans = iter;
        break;
      }
      for (int i = 0; i+1 < n; i++)
        b[i] = abs(a[i] - a[i+1]);
      b[n-1] = abs(a[n-1] - a[0]);

      for (int i = 0; i < n; i++)
        a[i] = b[i];
     }

    cout << "Case " << tc++ << ": ";
    if (ans == -1)
      cout << "not attained\n";
    else
      cout << ans << " iterations\n";
  }

  return 0;
}
