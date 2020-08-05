#include <bits/stdc++.h>

using namespace std;

const double EPS = 1e-9;

inline
int compareTo(double x, double y, double tol=EPS) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

int solve(double R, double B, double M) {

  for (int i = 1; i <= 1200; i++) {
    double interest = floor(B * R * 100 + 0.5 + EPS);
    interest /= 100;
    B += interest;
    B -= M;

    if (compareTo(B, 0) <= 0)
      return i;
  }

  return -1;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    double R, B, M;
    cin >> R >> B >> M;
    R /= 100;

    int ans = solve(R, B, M);

    if (ans < 0) cout << "impossible\n";
    else cout << ans << '\n';
  }

  return 0;
}
