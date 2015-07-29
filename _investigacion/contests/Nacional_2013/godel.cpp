#include <bits/stdc++.h>

using namespace std;

int i, n;
string s;

bool parse() {
  if (i >= n)
    return false;
  if (s[i] == '0') {
    i++;
    return true;
  }

  i++;
  return parse() && parse();
}

int solve() {
  int cnt = 0;

  while (i < n) {
    if (s[i] == '0') {
      cnt++;
      i++;
    }
    else {
      i++;
      if (parse() && parse())
        cnt++;
      else
        return 0;
    }
  }

  return cnt;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> s) {
    i = 0;
    n = s.size();

    for (int j = 0; j < n; j++)
      if (s[j] == '?')
        s[j] = '0';

    cout << solve() << '\n';
  }

  return 0;
}
