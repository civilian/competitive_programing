#include <bits/stdc++.h>

using namespace std;

string s, t;

int solve() {
  int n = s.size();
  int m = t.size();

  int sl = 0, sr = n-1;
  int tl = 0, tr = m-1;

  while ((sl < sr) && (tl < tr)) {
    if (s[sl] == t[tl])
      sl++, tl++;
    if (s[sr] == t[tr])
      sr--, tr--;

    if (s[sl] != t[tl] && s[sr] != t[tr])
      break;
  }

  if (tr <= tl)
    return 0;
  return tr - tl + 1;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cin >> s >> t;
  cout << solve() << '\n';

  return 0;
}
