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
#define foreach(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

const double PI = acos(-1.0);

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
} pivot;

// solve system ecuation
bool intersect(const point & A, const point & B, const point & C, const point & D, point& E) {
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

  E.x = x;
  E.y = y;

  return true;
}

double cross(point p, point q, point r) {
  return (r.x - q.x) * (p.y - q.y) - (r.y - q.y) * (p.x - q.x);
}

double angle(point a, point b, point c) {
  double ux = b.x - a.x, uy = b.y - a.y;
  double vx = c.x - a.x, vy = c.y - a.y;

  return acos((ux*vx + uy*vy) /
              sqrt((ux*ux + uy*uy) * (vx*vx + vy*vy)));
}

bool in_polygon(point p, vector<point> P) {
  if(P.size() == 0) return false;
  double sum = 0;
  for(int i = 0; i < P.size() - 1; i++) {
    if(cross(p, P[i], P[i+1]) < 0)
      sum -= angle(p, P[i], P[i+1]);
    else
      sum += angle(p, P[i], P[i+1]);
  }
  return (fabs(sum - 2*PI) < EPS || fabs(sum + 2*PI) < EPS);
}

vector<point> get_polygon(int n) {
  vector<point> p(n);
  for(int i = 0; i < n; i++) {
    cin >> p[i].x >> p[i].y;
  }

  p.push_back(p[0]);
  return p;
}

struct cmp {
  bool operator ()(point a, point b) const {
    if(fabs(a.x - b.x) < EPS)
      return a.y < b.y;
    return a.x < b.x;
  }
};

int main() {
  ios_base::sync_with_stdio(false);
  int n, m;

  cout.precision(2);
  cout.setf(ios::fixed);

  while(cin >> n) {
    if(n < 3) break;

    vector<point> p1 = get_polygon(n);

    cin >> m;
    vector<point> p2 = get_polygon(m);

    set<point, cmp> ans;
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        point e;
        if(intersect(p1[i], p1[i+1], p2[j], p2[j+1], e)) {
          ans.insert(e);
        }
      }
    }

    for(int i = 0; i < n; i++)
      if(in_polygon(p1[i], p2))
        ans.insert(p1[i]);
    for(int i = 0; i < m; i++)
      if(in_polygon(p2[i], p1))
        ans.insert(p2[i]);

    cout << (int)ans.size() << endl;
    if(!ans.empty()) {
      foreach(p, ans) {
        cout << p->x << " " << p->y << endl;
      }
    }
  }

  return 0;
}

