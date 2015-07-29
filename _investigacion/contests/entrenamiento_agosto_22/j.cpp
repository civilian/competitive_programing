#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);

const int maxn = 81;

int n, memo[55][55][26];
string s;
vector<string> rules[26];

int solve(int i, int j, char ch) {
  int c = ch-'A';

  if (memo[i][j][c] < 0) {
    if (i == j)
      return memo[i][j][c] = (s[i] == ch)? 0 : INF;

    int best = INF;
    for (int r = 0; r < rules[c].size(); r++) {
      char A = rules[c][r][0];
      char B = rules[c][r][1];

      for (int k = i; k < j; k++) {
        int curr = 1 + max(solve(i, k, A), solve(k+1, j, B));
        best = min(best, curr);
      }
    }
    memo[i][j][c] = best;
  }
  return memo[i][j][c];
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n) {
    if (n == 0)
      break;

    for (int i = 0; i < 26; i++)
      rules[i].clear();

    for (int i = 0; i < n; i++) {
      string r;
      cin >> r;
      rules[r[0]-'A'].push_back(r.substr(1));
    }

    cin >> s;
    int m = s.size();

    memset(memo, -1, sizeof(memo));
    int ans = INF;
    for (char ch = 'A'; ch <= 'T'; ch++)
      ans = min(ans, solve(0, m-1, ch));

    if (ans == INF)
      ans = -1;

    cout << ans << '\n';
  }

  return 0;
}
