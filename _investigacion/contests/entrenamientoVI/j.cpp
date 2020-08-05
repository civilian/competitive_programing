#include <bits/stdc++.h>

using namespace std;

const int maxn = 1000010;

long long dp[maxn], cubes[3], pot[3];

inline
long long get_vol(int index, long long W) {
  long long area = (W / cubes[index]);
  area = area * area;
  return area * pot[index];
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  long long base, top, height;
  while (cin >> height >> base >> top) {
    for (int i = 0; i < 3; i++) {
      cin >> cubes[i];
      pot[i] = cubes[i] * cubes[i] * cubes[i];
    }

    memset(dp, 0, (height + 1) * sizeof(long long));
    long long ans = 0;
    for (long long h = 1; h <= height; h++) {
      long long width = ((height - h) * base + h * top) / height;
      for (int i = 0; i < 3; i++) {
        if (h >= cubes[i])
          dp[h] = max(dp[h], dp[h-cubes[i]] + get_vol(i, width));
        ans = max(ans, dp[h]);
      }
    }
    cout << ans << '\n';
  }

  return 0;
}
