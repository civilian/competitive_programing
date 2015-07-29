#include <iostream>
#include <cmath>
#include <cstdio>
#include <cstdlib>

using namespace std;

const double g = 9.8;
const double EPS = 1e-6;
const double PI = acos(-1);

int compareTo(double x, double y, double tol=EPS) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

double sqr(double x) {
  return x*x;
}

int main() {
  ios_base::sync_with_stdio(false);

  int x0, y0, x, y, v;
  while (cin >> x0 >> y0 >> x >> y >> v) {
    double a = (g * sqr(x - x0)) / (2. * sqr(v));

    double b = x - x0;

    double c =  a + y - y0;

    double tan1 = 0;
    double tan2 = 0;
    if (b * b >= 4 * a * c) {
      double root = sqrt(sqr(b) - (4. * a * c));
      tan1 = (-b + root) / (2. * a);
      tan2 = (-b - root) / (2. * a);

      double ans1 = -1 * (atan(tan1) * 180) / PI;
      double ans2 = -1 * (atan(tan2) * 180) / PI;
      if (compareTo(ans1, ans2) < 0)
        printf("%.2f\n", ans1);
      else
        printf("%.2f\n", ans2);
    }
    else {
      printf("%s\n", "Impossible");
    }
  }
  return 0;
}
