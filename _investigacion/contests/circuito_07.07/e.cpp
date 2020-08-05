#include <bits/stdc++.h>

using namespace std;

map<char,int> alpha;

void init() {
  alpha['a'] = alpha.size();  alpha['b'] = alpha.size();
  alpha['k'] = alpha.size();  alpha['d'] = alpha.size();
  alpha['e'] = alpha.size();  alpha['g'] = alpha.size();
  alpha['h'] = alpha.size();  alpha['i'] = alpha.size();
  alpha['l'] = alpha.size();  alpha['m'] = alpha.size();
  alpha['n'] = alpha.size();  alpha['?'] = alpha.size();
  alpha['o'] = alpha.size();  alpha['p'] = alpha.size();
  alpha['r'] = alpha.size();  alpha['s'] = alpha.size();
  alpha['t'] = alpha.size();  alpha['u'] = alpha.size();
  alpha['w'] = alpha.size();  alpha['y'] = alpha.size();
}

bool cmp(string a, string b) {
  int n = a.size(), m = b.size();
  for (int i = 0; i < min(n,m); i++) {
    if (alpha[a[i]] < alpha[b[i]])
      return true;
    if (alpha[a[i]] > alpha[b[i]])
      return false;
  }
  return n < m;
}

string f(string a, string r = "ng", string t = "?") {
  int pos;
  while ((pos = a.find(r)) != string::npos) {
    a = a.replace(pos, r.size(), t);
  }
  return a;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  init();
  int n, t;
  cin >> t;

  while (t--) {
    cin >> n;
    vector<string> s;
    for (int i = 0; i < n; i++) {
      s.push_back("");
      cin >> s[i];
      s[i] = f(s[i]);
    }
    sort(s.begin(),s.end(), cmp);
    for (int i = 0; i < n; i++) {
      cout << f(s[i], "?", "ng") << '\n';
    }
  }

  return 0;
}
