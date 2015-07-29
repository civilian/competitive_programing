#include <bits/stdc++.h>

using namespace std;

const int maxr = 1010;

int n, m;
long long R[maxr];

long long get_sibiling(long long x) {
  if (x & 1)
    return x-1;
  return x+1;
}

long long get_parent(long long x) {
  return x >> 1;
}

void solve() {
  set<long long> ans, inv;
  set<long long>::iterator it;

  for (int i = 0; i < m; i++) {
    long long u = R[i];

    long long p = get_parent(u);
    while (u != 1) {
      long long s = get_sibiling(u);
      ans.erase(u);
      inv.insert(u);

      if (!inv.count(s))
        ans.insert(s);

      u = p;
      p = get_parent(u);
    }
  }

  bool first = true;
  for (it = ans.begin(); it != ans.end(); it++) {
    if (!first) cout << ' ';
    cout << *it;
    first = false;
  }
  cout << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n >> m) {
    for (int i = 0; i < m; i++)
      cin >> R[i];
    solve();
  }

  return 0;
}
