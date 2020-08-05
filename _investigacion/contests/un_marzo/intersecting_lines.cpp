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

int compareTo(double x, double y, double tol = EPS) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

struct point {
  double x, y;
  point(double _x=0, double _y=0) {
    x = _x; y = _y;
  }
};

struct line {
  double a, b, c;
};

void points_to_line(point p1, point p2, line* l) {
  if(compareTo(p1.x, p2.x) == 0) {
    l->a = 1.0; l->b = 0.0; l->c = -p1.x;
  } else {
    l->a = -(double)(p1.y - p2.y) / (p1.x - p2.x);
    l->b = 1.0;
    l->c = -(double)(l->a * p1.x) - (l->b * p1.y);
  }
}

bool are_parallel(line l1, line l2) {
  return (fabs(l1.a - l2.a) < EPS) && (fabs(l1.b - l2.b) < EPS);
}

bool are_same(line l1, line l2) {
  return are_parallel(l1, l2) && (fabs(l1.c - l2.c) < EPS);
}

bool are_intersect(line l1, line l2, point* p) {
  if(are_same(l1, l2)) return false;
  if(are_parallel(l1, l2)) return false;

  p->x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
  if(fabs(l1.b) > EPS) {
    p->y = - (l1.a * p->x + l1.c) / l1.b;
  }
  else {
    p->y = - (l2.a * p->x + l2.c) / l2.b;
  }

  return true;
}

int main() {
  ios_base::sync_with_stdio(false);

  int t;
  cin >> t;

  cout.precision(2);
  cout.setf(ios::fixed);
  cout << "INTERSECTING LINES OUTPUT\n";

  while(t--) {
    point p1, p2, p3, p4, inter;
    cin >> p1.x >> p1.y >> p2.x >> p2.y;
    cin >> p3.x >> p3.y >> p4.x >> p4.y;

    line l1, l2;
    points_to_line(p1, p2, &l1);
    points_to_line(p3, p4, &l2);

    if(are_same(l1, l2)) {
      cout << "LINE\n";
    }
    else if(are_intersect(l1, l2, &inter)) {
      cout << "POINT " << inter.x << ' ' << inter.y << endl;
    }
    else {
      cout << "NONE\n";
    }
  }

  cout << "END OF OUTPUT\n";

  return 0;
}

