#include <bits/stdc++.h>

using namespace std;

int memo[200][200][20];
int dr[] = {1, 0, -1, 0, -1, 1};
int dc[] = {0, 1, 0, -1, -1, 1};

int solve(int r, int c, int n) {
  if (n == 0)
    return (r == 100 && c == 100)? 1 : 0;
  int& ans = memo[r][c][n];

  if (ans == -1) {
    ans = 0;
    for (int k = 0; k < 6; k++)
      ans += solve(r+dr[k], c+dc[k], n-1);
  }
  return ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  memset(memo, -1, sizeof(memo));
  for (int i = 1; i <= 14; i++)
    solve(100, 100, i);

  int t, n;
  cin >> t;

  while (t--) {
    cin >> n;
    cout << memo[100][100][n] << '\n';
  }

  return 0;
}
