#include <bits/stdc++.h>

using namespace std;

const double EPS = 1e-9;
const int maxn = 1010;

struct point {
  double x, y;
  point(double x=0, double y=0) :
    x(x), y(y) { }

  point operator + (const point &p)  const { return point(x+p.x, y+p.y); }
  point operator - (const point &p)  const { return point(x-p.x, y-p.y); }
  point operator * (double c)     const { return point(x*c,   y*c  ); }
  point operator / (double c)     const { return point(x/c,   y/c  ); }
};

int n, L, H;
point left_pt[maxn], right_pt[maxn];

double dot(point p, point q)     { return p.x*q.x+p.y*q.y; }
double dist2(point p, point q)   { return dot(p-q,p-q); }

point ProjectPointSegment(point a, point b, point c) {
  double r = dot(b-a,b-a);
  if (fabs(r) < EPS) return a;
  r = dot(c-a, b-a)/r;
  if (r < 0) return a;
  if (r > 1) return b;
  return a + (b-a)*r;
}

double distance_to_linesegment(point a, point b, point c) {
  return sqrt(dist2(c, ProjectPointSegment(a, b, c)));
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cout.precision(2);
  cout.setf(ios::fixed);

  while (cin >> n) {
    cin >> L >> H;
    for (int i = 0; i < n; i++) {
      point a, b;
      cin >> a.y >> b.x >> b.y;

      if (i & 1) {
        a.x = L;
        left_pt[i] = b;
        right_pt[i] = a;
      }
      else {
        a.x = 0;
        left_pt[i] = a;
        right_pt[i] = b;
      }
    }

    double ans = 1e200;
    for (int i = 0; i < n; i++) {
      if (i & 1)
        ans = min(ans, left_pt[i].x);
      else
        ans = min(ans, L - right_pt[i].x);
    }

    for (int i = 0; i < n-1; i++) {
      if (i & 1)
        ans = min(ans,
            distance_to_linesegment(left_pt[i+1], right_pt[i+1], left_pt[i]));
      else
        ans = min(ans,
            distance_to_linesegment(right_pt[i+1], left_pt[i+1], right_pt[i]));
    }
    for (int i = 1; i < n; i++) {
      if (i & 1)
        ans = min(ans,
            distance_to_linesegment(left_pt[i-1], right_pt[i-1], left_pt[i]));
      else
        ans = min(ans,
            distance_to_linesegment(right_pt[i-1], left_pt[i-1], right_pt[i]));
    }
    cout << ans << '\n';
  }

  return 0;
}
