#include <bits/stdc++.h>

using namespace std;

const double PI = 3.14159;

inline
int compareTo(double x, double y, double tol=1e-9) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int n;
  cin >> n;

  double best = 0;
  for (int i = 0; i < n; i++) {
    char op;
    cin >> op;
    double r, h, curr;
    cin >> r;
    if (op != 'S') {
      cin >> h;
      if (op == 'C')
        curr = (1. / 3.) * PI * r * r * h;
      else
        curr = PI * r * r * h;
    }
    else curr = (4. / 3.) * PI * r * r * r;
    if (compareTo(curr, best) > 0)
      best = curr;
  }
  cout.precision(3);
  cout.setf(ios::fixed);
  cout << best << '\n';

  return 0;
}
