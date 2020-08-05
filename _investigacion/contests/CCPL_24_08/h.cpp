#include <bits/stdc++.h>

using namespace std;

// 2646 - Cantoring Along
// https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8&category=136&page=show_problem&problem=647

int p[15];
string ans[15];

void solve(string& s, int l, int r) {
  if (r-l == 1)
    return;

  int cut = (r-l) / 3;
  for (int i = cut; i < cut+cut; i++)
    s[l+i] = ' ';
  solve(s, l, l+cut);
  solve(s, l+cut+cut, r);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  p[0] = 1;
  for (int i = 1; i <= 12; i++)
    p[i] = p[i-1] * 3;

  ans[0] = "-";
  for (int i = 1; i <= 12; i++) {
    string curr(p[i], '-');
    solve(curr, 0, p[i]);
    ans[i] = curr;
  }

  int n;
  while (cin >> n)
    cout << ans[n] << '\n';

  return 0;
}
