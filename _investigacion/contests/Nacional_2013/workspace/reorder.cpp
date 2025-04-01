#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

struct word {
  char f, l;
  string w;
  word(char f=' ', char l=' ', string s = "") :
    f(f), l(l) {
    sort(s.begin(), s.end());
    w = s;
  }
};

int main() {
#ifdef LOCAL
  freopen("reorder.in", "r", stdin);
  freopen("reorder.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
#endif

  int t;
  cin >> t;
  cin.get();
  while (t--) {
    string ln, s;
    getline(cin, ln);

    vector<word> v;
    stringstream ss1(ln);
    vector<string> orig;
    while (ss1 >> s) {
      v.push_back(word(s[0], s[s.size()-1], s));
      orig.push_back(s);
    }

    vector<word> w;
    getline(cin, ln);
    stringstream ss2(ln);

    vector<string> ans;
    while (ss2 >> s) {
      int n = s.size()-1;
      word curr(s[0], s[n], s);
      bool found = false;
      for (int i = 0; i < orig.size(); i++) {
        if (s[0] == v[i].f && s[n] == v[i].l) {
          if (v[i].w == curr.w) {
            ans.push_back(orig[i]);
            found = true;
            break;
          }
        }
      }
      if (!found)
        ans.push_back(s);
    }
    for (int i = 0; i < ans.size(); i++) {
      if (i) cout << ' ';
      cout << ans[i];
    }
    cout << '\n';
  }

  return 0;
}
