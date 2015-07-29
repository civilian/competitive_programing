#include <bits/stdc++.h>

using namespace std;

bool solve(long long x1, long long y1, long long x2, long long y2) {
  return __gcd(abs(x1), abs(y1)) == __gcd(abs(x2), abs(y2));
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    int x1,y1,x2,y2;
    cin >> x1 >> y1 >> x2 >> y2;

    cout << solve(x1,y1,x2,y2) << '\n';
  }

  return 0;
}
