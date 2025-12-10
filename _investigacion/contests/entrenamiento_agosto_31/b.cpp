#include <cstdlib>
#include <cstring>
#include <cassert>
#include <cmath>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <utility>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

const int maxn = 111;

double d[maxn][maxn];
int x[maxn], y[maxn], n;

int compareTo(double x, double y, double tol=1e-9) {
  return (x <= y + tol)? (x + tol < y)? -1 : 0 : 1;
}

struct point {
  int x, y;
  point(int _x = 0, int _y = 0) {
    x = _x;
    y = _y;
  }
};

int cross(point p, point q, point r) {
  return (r.x - q.x) * (p.y - q.y) - (r.y - q.y)* (p.x - q.x);
}

bool collinear(point p, point q, point r) {
  return abs(cross(p,q,r)) == 0;
}

double dist(int i, int j) {
  return hypot((x[i]-x[j]), (y[i]-y[j]));
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  for (int tc = 1; tc <= t; tc++) {
    cin >> n;
    assert(1 <= n && n <= 100);
    for (int i = 0; i < n; i++) {
      cin >> x[i] >> y[i];
      for (int j = 0; j < i; j++)
        d[i][j] = d[j][i] = dist(i, j);
    }

    int ans = 0;

    for (int i = 0; i < n; i++) {
      for (int j = i+1; j < n; j++) {
        if (i == j) continue;
        for (int k = j+1; k < n; k++) {
          if (i == k || j == k) continue;
          vector<double> v(3);
          v[0] = d[i][j];
          v[1] = d[j][k];
          v[2] = d[k][i];
          sort(v.begin(), v.end());

          if (compareTo(v[0]+v[1], v[2]) > 0) {
            //point p(x[i], y[i]), q(x[j], y[j]), r(x[k], y[k]);
            //if (!collinear(p,q,r)) {
              ans++;
            //}
          }
        }
      }
    }
    cout << "Test case #" << tc << ": ";
    cout << ans << " triangle(s) can be formed.\n\n";
  }

  return 0;
}
