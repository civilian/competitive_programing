#include <bits/stdc++.h>

using namespace std;

int n, a[111][111], sum[111][111], mul[111][111];

int dp_sum(int i, int j) {
  if (i == n || j == n)
    return 0;

  if (sum[i][j] != -1)
    return sum[i][j];

  sum[i][j] = a[i][j] + max(dp_sum(i+1, j), dp_sum(i, j+1));

  return sum[i][j];
}

int dp_mul(int i, int j) {
  if (i == n || j == n)
    return 0;

  if (mul[i][j] != -1)
    return mul[i][j];

  mul[i][j] = a[i][j] * a[i][j] + max(dp_mul(i+1, j), dp_mul(i, j+1));

  return mul[i][j];
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cin >> n;

  for (int i = 1; i <= n; i++)
    for (int j = 0, k = i-1; j < i; j++, k--)
      cin >> a[k][j];

  memset(sum, -1, sizeof(sum));
  memset(mul, -1, sizeof(mul));
  int m = dp_mul(0,0);
  int s = dp_sum(0,0);

  cout << m << ' ' << s << '\n';
  cout << char('a'+(m%26)) << char('a'+(s%26));

  return 0;
}
