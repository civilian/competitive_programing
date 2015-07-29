#include <bits/stdc++.h>

using namespace std;

// 2642 - Spelling Be
// https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8&category=136&page=show_problem&problem=643


int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int n;
  while (cin >> n) {
    set<string> dict;
    string s;

    for (int i = 0; i < n; i++) {
      cin >> s;
      dict.insert(s);
    }

    int m;
    cin >> m;
    for (int email = 1; email <= m; email++) {
      vector<string> miss;
      while (cin >> s) {
        if (s == "-1")
          break;
        if (!dict.count(s))
          miss.push_back(s);
      }
      cout << "Email " << email << " is ";
      if (miss.empty())
        cout << "spelled correctly.\n";
      else {
        cout << "not spelled correctly.\n";
        for (int i = 0; i < miss.size(); i++)
          cout << miss[i] << '\n';
      }
    }
  }
  cout << "End of Output\n";

  return 0;
}
