#include <bits/stdc++.h>

using namespace std;

const int maxn = 100010;

int a[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int n;
  cin >> n;

  int ans = 0;
  for (int i = 0; i < n; i++)
    cin >> a[i];
  sort(a, a+n);
  reverse(a, a+n);

  for (int i = 0; i < n; i++)
    ans = max(ans, a[i]+i+1);

  cout << ans+1 << '\n';

  return 0;
}
