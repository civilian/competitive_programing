#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);

const int maxn = 2010;

int N, C, T[2], a[maxn], memo[maxn];

int dp(int i) {
  if (i >= N)
    return 0;
  if (memo[i] != -1)
    return memo[i];

  int r = INF;
  for (int k = 0; k < 2; k++) {
    int j = lower_bound(a, a+N+N, a[i]+T[k]) - a;
    if (a[j] == a[i] + T[k])
      j++;
    r = min(r, T[k]+dp(j));
  }

  return memo[i] = r;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> N >> C >> T[0] >> T[1]) {
    for (int i = 0; i < N; i++) {
      cin >> a[i];
      a[i+N] = a[i]+C;
    }

    memset(memo, -1, sizeof(memo));
    int ans = dp(0);
    cout << ans << '\n';
  }

  return 0;
}
