#include <cstdlib>
#include <cmath>
#include <iostream>

using namespace std;

const double PI = 3.14159265;

const int maxn = 111;

int n, xs, ys, x[maxn], alpha[maxn], I[maxn];

inline
double compareTo(double x, double y, double tol=1e-9) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

inline
double deg_to_rad(double x) {
  return x * PI / 180.;
}

bool contains(int i) {
  double start = 90. - alpha[i];
  double end = start + 2*alpha[i];

  start = deg_to_rad(start);
  end = deg_to_rad(end);

  double theta = atan2(ys, xs-x[i]);

  return compareTo(start, theta) <= 0 && compareTo(theta, end) <= 0;
}

double solve() {
  double sum = 0;

  for (int i = 0; i < n; i++)
    if (contains(i)) {
      double d = hypot(x[i]-xs, ys);
      sum += I[i] / (d*d);
    }

  return sum + 1e-8;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cout.precision(3);
  cout.setf(ios::fixed);

  int t;
  cin >> t;

  for (int tc = 1; tc <= t; tc++) {
    cin >> xs >> ys >> n;

    for (int i = 0; i < n; i++)
      cin >> x[i];
    for (int i = 0; i < n; i++)
      cin >> alpha[i];
    for (int i = 0; i < n; i++)
      cin >> I[i];

    cout << "Scene #" << tc << ": Spotlight intensity on Stacie is ";
    cout << solve() << "\n\n";
  }

  return 0;
}

