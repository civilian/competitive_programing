#include <bits/stdc++.h>

using namespace std;

const double PI = acos(-1.0);

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  cout.precision(5);
  cout.setf(ios::fixed);

  while (t--) {
    double r;
    cin >> r;

    double h = sqrt(r*r + r*r) + r + tan(60.*PI/180.) * r;
    double side = h*cos(45.*PI/180.)*2;

    cout << side*side << '\n';
  }

  return 0;
}
