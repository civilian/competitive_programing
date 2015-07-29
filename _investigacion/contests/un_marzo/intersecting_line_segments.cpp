#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <ctime>
#include <cctype>
#include <cassert>
#include <iostream>
#include <sstream>
#include <iomanip>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <deque>
#include <list>
#include <set>
#include <map>
#include <bitset>
#include <algorithm>
#include <numeric>
#include <complex>

#define D(x) cerr << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

double sqr(double v) {
  return v*v;
}

struct point {
  double x, y;

  point(double _x=0, double _y= 0) {
    x = _x; y = _y;
  }

  double dist(const point& p) const {
    double cc = sqr(p.x - x) + sqr(p.y - y);
    return sqrt(cc);
  }
};

// solve system ecuation
bool intersect(const point & A, const point & B, const point & C, const point & D) {
  double a, b, c, d, rx, ry, det, s, t, x, y;
  a = (B.x - A.x); // R'
  b = (C.x - D.x); // R
  c = (B.y - A.y);
  d = (C.y - D.y);

  rx = C.x - A.x;
  ry = C.y - A.y;

  det = a*d - b*c;
  // parallel lines
  if(fabs(det) < EPS) return false; // Not valid det = 0

  s = (rx * d - b * ry) / det;
  t = (a * ry - rx * c) / det;

  // check if s, t are in [0,1] for an open line segment
  // if not they do not intersects
  if(s < 0.0 || s > 1.0 || t < 0.0 || t > 1.0) return false;

  // use s or t in any parametric ecuation
  x = A.x + (B.x - A.x) * s;
  y = A.y + (B.y - A.y) * s;

  return true;
}

vector<point> start, end;

int main() {
  ios_base::sync_with_stdio(false);
  int t;
  cin >> t;

  while(t--) {
    int n;
    cin >> n;

    start.assign(n, point());
    end.assign(n, point());

    for(int i = 0; i < n; i++) {
      cin >> start[i].x >> start[i].y;
      cin >> end[i].x >> end[i].y;
    }

    int ans = 0;
    for(int i = 0; i < n; i++) {
      int cnt = 1;
      for(int j = 0; j < n; j++) {
        if(i == j) continue;
        if(intersect(start[i], end[i], start[j], end[j]))
          cnt++;
      }
      ans += cnt;
    }
    cout << ans << endl;
    if(t) cout << endl;
  }

  return 0;
}

