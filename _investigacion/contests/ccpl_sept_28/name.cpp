#include <bits/stdc++.h>

using namespace std;

void solve(string s) {
  string t = "not a quine";
  int n = s.size();
  if (s[0] == '\"') {
    int i;
    for (i = 1; i < n; i++)
      if (s[i] == '\"')
        break;
    string a = s.substr(1, i-1);

    if (i+1 < n && s[i+1] == ' ') {
      i += 2;
      if (i < n) {
        if (a == s.substr(i))
          t = "Quine(" + a + ")";
      }
    }
  }

  cout << t << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  string ln;
  while (getline(cin, ln)) {
    if (ln == "END")
      break;

    solve(ln);
  }
  return 0;
}
