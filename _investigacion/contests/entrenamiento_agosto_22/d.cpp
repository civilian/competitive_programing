#include <bits/stdc++.h>

using namespace std;

const double EPS = 1e-9;

inline
int compareTo(double x, double y, double tol=EPS) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

struct point {
  double x, y;

  point(double _x=0, double _y=0) :
    x(_x), y(_y) { }
  bool operator<(const point& p) const {
    if (compareTo(x, p.x) == 0)
      return compareTo(y, p.y) < 0;
    return compareTo(x, p.x) < 0;
  }
};

bool end_input(const vector<point>& P) {
  for (int i = 0; i < P.size(); i++)
    if (compareTo(P[i].x, 0) != 0 || compareTo(P[i].y, 0) != 0)
      return false;
  return true;
}

point line_segment_inter(point p, point q, point A, point B) {
  double a = B.y - A.y;
  double b = A.x - B.x;
  double c = B.x * A.y - A.x * B.y;
  double u = fabs(a * p.x + b * p.y + c);
  double v = fabs(a * q.x + b * q.y + c);
  return point((p.x * v + q.x * u) / (u + v),
               (p.y * v + q.y * u) / (u + v));
}

double calculate_area(vector<point> P) {
  double result = 0, x1, y1, x2, y2;
  for (int i = 0; i < P.size()-1; i++) {
    x1 = P[i].x; x2 = P[i+1].x;
    y1 = P[i].y; y2 = P[i+1].y;
    result += (x1 * y2 - x2 * y1);
  }
  return fabs(result) * .5;
}

double dist(point p, point q) {
  return hypot(p.x-q.x, p.y-q.y);
}

double calculate_perimeter(point p, point center, point q) {
  double result = dist(p, center) + dist(q, center) + dist(p, q);
  return result;
}

double area[5], per[5];

void solve(vector<point> P) {
  P.push_back(P.front());

  point center = line_segment_inter(P[0], P[2], P[1], P[3]);
  int n = P.size();

  vector< pair<int,int> > ans;

  for (int i = 0; i < n-1; i++) {
    point p1 = P[i], p2 = P[i+1];
    vector<point> Q;
    Q.push_back(p1); Q.push_back(center);
    Q.push_back(p2);

    Q.push_back(Q.front());

    area[i] = calculate_area(Q);
    per[i] = calculate_perimeter(p1, center, p2);
    ans.push_back(make_pair((int)round(area[i] * 1000), (int)round(per[i] * 1000)));
  }
  sort(ans.begin(), ans.end());
  for (int i = n-2; i >= 0; i--) {
    printf("%d.%03d ", ans[i].first / 1000, ans[i].first % 1000);
    printf("%d.%03d", ans[i].second / 1000, ans[i].second % 1000);
    if (i) printf(" ");
  }
  printf("\n");
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  vector<point> P;
  while (true) {
    P.clear();
    for (int i = 0; i < 4; i++) {
      point p;
      cin >> p.x >> p.y;
      P.push_back(p);
    }
    if (end_input(P))
      break;

    solve(P);
  }

  return 0;
}
