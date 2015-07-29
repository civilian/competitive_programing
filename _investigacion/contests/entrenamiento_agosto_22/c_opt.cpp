#include <bits/stdc++.h>

using namespace std;

// DP Optimization: Divide and conquer
// More info: http://codeforces.com/blog/entry/8219

const int maxn = 1010;
const int INF = int(1e9);

int n, m, a[maxn], sum[maxn], memo[maxn][maxn], value[maxn][maxn];

int calculate_value(int i, int j) {
  if (value[i][j] != -1)
    return value[i][j];

  if (i == j)
    return value[i][j] = 0;

  value[i][j] = (sum[j]-sum[i]) * a[i];
  value[i][j] += calculate_value(i+1, j);

  return value[i][j];
}

// return best k index.
int dp(int index, int moves, int from, int to) {
  if (index >= n)
    return 0;

  if (moves == 0) {
    memo[index][moves] = value[index][n-1];
    return index;
  }

  int best = INF;
  int best_index = -1;

  if (from < index)
    from = index;

  for (int i = from; i <= to; i++) {
    int val = value[index][i] + (i+1 == n+1? 0: memo[i+1][moves-1]);

    if (val < best) {
      best = val;
      best_index = i;
    }
  }

  memo[index][moves] = best;

  return best_index;
}

void calculate_dp(int moves, int from, int to, int optL, int optR) {
  int mid = (from + to) >> 1;

  int best_mid = dp(mid, moves, optL, optR);
  if (from == mid) {
    dp(to, moves, optL, optR);
    return;
  }

  calculate_dp(moves, from, mid-1, optL, best_mid);
  calculate_dp(moves, mid+1, to, best_mid, optR);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n >> m) {
    if ((n|m) == 0)
      break;

    for (int i = 0; i < n; i++)
      cin >> a[i];

    if (m == n-1) {
      cout << 0 << '\n';
      continue;
    }

    sum[0] = a[0];
    for (int i = 1; i < n; i++)
      sum[i] = a[i] + sum[i-1];

    memset(value, -1, sizeof(value));
    for (int i = 0; i < n; i++)
      for (int j = i; j < n; j++)
        calculate_value(i,j);

    memset(memo, -1, sizeof(memo));
    for (int i = 0; i <= n; i++)
      dp(i, 0, 0, n-1);

    for (int i = 1; i <= m; i++)
      calculate_dp(i, 0, n, 0, n-1);
    cout << memo[0][m] << '\n';
  }

  return 0;
}
