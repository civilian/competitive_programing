#include <bits/stdc++.h>

using namespace std;

const int maxn = 1010;
const int INF = int(1e9);

int n, m, a[maxn], sum[maxn], memo[maxn][maxn], value[maxn][maxn];

int calculate_value(int i, int j) {
  if (i == j)
    return value[i][j] = 0;

  if (value[i][j] != -1)
    return value[i][j];

  value[i][j] = (sum[j]-sum[i]) * a[i];
  value[i][j] += calculate_value(i+1, j);

  return value[i][j];
}

int dp(int index, int moves) {
  if (index == n)
    return (moves == 0)? 0: INF;
  if (moves == 0) {
    if (n-index > 1)
      memo[index][moves] = value[index][n-1];
    else
      memo[index][moves] = 0;
    return memo[index][moves];
  }

  if (memo[index][moves] != -1)
    return memo[index][moves];

  memo[index][moves] = INF;
  for (int i = 0; i < n-index; i++) {
    int val = value[index][index+i];
    memo[index][moves] = min(memo[index][moves],
                             val + dp(index+i+1, moves-1));
  }
  return memo[index][moves];
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
    cout << dp(0, m) << '\n';
  }

  return 0;
}
