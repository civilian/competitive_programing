#include <bits/stdc++.h>

using namespace std;

int n, m;
vector<long long> a, b;
set< vector<long long> > seen;
bool solved = false;

bool check(const vector<long long>& t) {
  for (int i = 0; i < m; i++)
    if (t[i] != b[i])
      return false;
  return true;
}

vector<long long> fold(const vector<long long>& t, int k) {
  vector<long long> s;
  int sz = t.size();

  int l = k-1, r = k;
  while (l >= 0 && r < sz) {
    s.push_back(t[l]+t[r]);
    l--, r++;
  }

  reverse(s.begin(), s.end());

  if (l >= 0) { // termino primero derecho: r >= n
    while (l >= 0)
      s.insert(s.begin(), t[l--]);
  }
  else if (r < sz) { // termino primero el izquierdo: l < 0
    while (r < sz)
      s.insert(s.begin(), t[r++]);
  }

  return s;
}

void solve(const vector<long long>& t) {
  if (!solved) {
    if (t.size() < m)
      return;
    if (seen.count(t))
      return;
    seen.insert(t);

    if (t.size() == m) {
      if (check(t))
        solved = true;
      else {
        vector<long long> s(t.begin(), t.end());
        reverse(s.begin(), s.end());
        if (check(s))
          solved = true;
      }
      return;
    }

    for (int i = 1; i < t.size(); i++) {
      vector<long long> s = fold(t, i);

      solve(s);
      if (solved)
        return;
    }
  }
}
int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n) {
    a.resize(n);
    for (int i = 0; i < n; i++)
      cin >> a[i];

    cin >> m;
    b.resize(m);
    for (int i = 0; i < m; i++)
      cin >> b[i];

    solved = false;
    seen.clear();
    solve(a);
    if (solved) cout << "S\n";
    else cout << "N\n";
  }

  return 0;
}
