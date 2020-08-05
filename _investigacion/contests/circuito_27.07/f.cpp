#include <bits/stdc++.h>

using namespace std;

int compareTo(double x, double y, double tol=1e-9) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

const int maxn = 55;

int n, x[maxn], y[maxn];

double dist(int i, int j) {
  return hypot(x[i]-x[j], y[i]-y[j]);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n) {
    if (n == 0)
      break;

    double ans = 0.0;
    for (int i = 0; i < n; i++) {
      cin >> x[i] >> y[i];
      for (int j = 0; j < i; j++) {
        double d = dist(i, j);
        ans += d;
      }
    }

    printf("%.2lf\n", ans / (n-1));
  }

  return 0;
}
