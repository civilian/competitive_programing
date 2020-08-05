#include <bits/stdc++.h>

using namespace std;

bool seen[50];

bool solve() {
  for (int i = 1; i <= 49; i++)
    if (!seen[i])
      return false;
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int n;
  while (cin >> n) {
    if (n == 0)
      break;
    memset(seen, 0, 50);
    for (int i = 0; i < n; i++)
      for (int j = 0, v; j < 6; j++) {
        cin >> v;
        seen[v] = 1;
      }
    cout << (solve()? "Yes\n" : "No\n");
  }

  return 0;
}
