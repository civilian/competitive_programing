#include <bits/stdc++.h>

using namespace std;

int pass[8];

int fact(int n) {
  int r = 1;
  for (int i = 1; i <= n; i++)
    r *= i;
  return r;
}

void solve(int n) {
  for (int i = 0; i < n; i++)
    pass[i] = i+1;

  int limit = fact(n) / 3;

  for (int i = 0; i < limit; i++)
    next_permutation(pass, pass+n);

  for (int i = 0; i < n; i++)
    cout << pass[i];
  cout << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    int n;
    cin >> n;
    solve(n);
  }

  return 0;
}
