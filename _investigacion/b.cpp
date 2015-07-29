#include <bits/stdc++.h>

using namespace std;

const int maxn = 100010;

int n, x[maxn], r[maxn], ord[maxn], dp[maxn][2], ans[maxn];
pair< pair<int,int>, int> in[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n) {
    if (n == 0) break;

    for (int i = 0; i < n; i++) {
      cin >> in[i].first.first >> in[i].first.second;
      in[i].second = i;
    }
    sort(in, in+n);

    for (int i = 0; i < n; i++) {
      x[i] = in[i].first.first;
      r[i] = in[i].first.second;
      ord[i] = in[i].second;
    }

    memset(dp, 0, sizeof(dp));
    dp[0][0] = 0;
    for (int k = 1; k < n; k++) {
      int i = lower_bound(x, x+n, x[k]-r[k]) - x;
      if (x[k]-r[k] > x[i])
        i++;
      dp[k][0] = dp[i][0] + k-i;
    }

    dp[n-1][1] = 0;
    for (int k = n-2; k >= 0; k--) {
      int i = lower_bound(x, x+n, x[k]+r[k]) - x;
      if (i == n || x[k]+r[k] < x[i])
        i--;
      dp[k][1] = dp[i][1] + i-k;
    }

    for (int i = 0; i < n; i++)
      ans[ord[i]] = dp[i][0]+dp[i][1]+1;

    for (int i = 0; i < n; i++) {
      if (i) cout << ' ';
      cout << ans[i];
    }

    cout << '\n';
  }

  return 0;
}
