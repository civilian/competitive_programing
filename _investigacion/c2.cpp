#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <vector>
#include <string>
#include <queue>
#include <map>
#include <set>
#include <utility>
#include <algorithm>
#include <iostream>

using namespace std;

int n;
string s;

int solve(int i, int j) {
  int cnt = 0;
  for (int ii = i, jj = j; ii < n || jj < n; ii++, jj++) {
    if (s[ii] != s[jj])
      return cnt;
    cnt++;
  }
  return cnt;
}

int main() {
#ifdef LOCAL
  freopen("c.in", "r", stdin);
  freopen("c.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
#endif

  while (cin >> s) {
    if (s == "*") break;
    
    n = s.size();
    int Q;
    cin >> Q;
    while (Q--) {
      int i, j;
      cin >> i >> j;
      cout << solve(i,j) << '\n';
    }
  }

  return 0;
}
