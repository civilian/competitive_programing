#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  string s, t;
  while (cin >> s >> t) {
    int n = s.size();
    int m = t.size();

    int best = 0;
    string ans = "No matches";

    for (int space = 0; space < n; space++) {
      int curr = 0;
      string curr_str;
      for (int i = 0; i < space; i++)
        curr_str.push_back(s[i]);

      int i;
      for (i = 0; i < m; i++) {
        if (i+space >= n)
          break;
        if (t[i] == s[i+space]) {
          curr++;
          curr_str.push_back(t[i]);
        }
        else {
          curr_str.push_back('X');
        }
      }

      for (; i+space < n; i++)
        curr_str.push_back(s[i+space]);

      for (; i < m; i++)
        curr_str.push_back(t[i]);

      if (curr > best) {
        best = curr;
        ans = curr_str;
      }
    }

    for (int space = 0; space < m; space++) {
      int curr = 0;
      string curr_str;
      int start = m-1-space;
      for (int i = 0; i < start; i++)
        curr_str.push_back(t[i]);

      for (int i = 0; i < n && i <= space; i++) {
        if (s[i] == t[start]) {
          curr++;
          curr_str.push_back(s[i]);
        }
        else
          curr_str.push_back('X');
        start++;
      }

      for (int i = space+1; i < n; i++)
        curr_str.push_back(s[i]);
      for (; start < m; start++)
        curr_str.push_back(t[start]);

      if (curr > best) {
        best = curr;
        ans = curr_str;
      }
    }

    cout << best << '\n';
    cout << ans << '\n';
    cout << '\n';
  }

  return 0;
}
