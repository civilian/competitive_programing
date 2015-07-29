#include <bits/stdc++.h>

using namespace std;

// Wrong Answer!

const double EPS = 1e-9;
const double PI = acos(-1.0);

int compareTo(double x, double y, double tol = EPS) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

struct Polar {
  int index;
  double t, r;
  Polar(double t = 0, double r = 0) :
    t(t), r(r) { }

  bool operator<(const Polar& p) const {
    int c = compareTo(t, p.t);
    if (c == 0)
      return compareTo(r, p.r) >= 0;
    return c < 0;
  }
};

const int maxn = 100010;

Polar P[maxn];
int N, x[maxn], y[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> x[i] >> y[i];
    P[i].t = atan2(y[i], x[i]);
    P[i].r = hypot(x[i], y[i]);
    P[i].index = i;
  }

  sort(P, P+N);

  double ans = -2e200;
  for (int i = 0; i < N; i++) {
    int j = lower_bound(P, P+N, Polar(P[i].t + PI)) - P;
    j--;
    int l = P[i].index, k = P[j].index;
    double curr = hypot(x[l]-x[k], y[l]-y[k]);

    if (compareTo(curr, ans) > 0)
      ans = curr;

    j = lower_bound(P, P+N, Polar(P[i].t + PI, 2e200)) - P;
    if (j >= N)
      j--;
    k = P[j].index;
    curr = hypot(x[l]-x[k], y[l]-y[k]);

    if (compareTo(curr, ans) > 0)
      ans = curr;
  }

  cout.precision(10);
  cout.setf(ios::fixed);
  cout << ans << '\n';

  return 0;
}
