#include <bits/stdc++.h>

using namespace std;

const double EPS = 1e-9;

struct point {
  int x, y;
  point(int _x = 0, int _y = 0) {
    x = _x; y = _y;
  }
} pivot;

void print(vector<point> P) {
  cout << "{ ";
  for(int i = 0; i < P.size(); i++)
    cout << "(" << P[i].x << ", " << P[i].y << "), ";
  cout << " }\n";
}

double dist(point p1, point p2) {
  return hypot(p1.x - p2.x, p1.y - p2.y);
}

double cross(point p, point q, point r) {
  return (r.x - q.x) * (p.y - q.y) - (r.y - q.y) * (p.x - q.x);
}

bool collinear(point p, point q, point r) {
  return fabs(cross(p, q, r)) < EPS;
}

bool ccw(point p, point q, point r) {
  return cross(p, q, r) > -EPS;
}

bool angleCmp(point a, point b) {
  if(collinear(pivot, a, b))
    return dist(pivot, a) < dist(pivot, b);

  double d1x = a.x - pivot.x, d1y = a.y - pivot.y;
  double d2x = b.x - pivot.x, d2y = b.y - pivot.y;

  return (atan2(d1y, d1x) - atan2(d2y, d2x)) < 0;
}

bool cmp(point a, point b) {
  if(fabs(a.x - b.x) < EPS)
    return (a.y - b.y) < 0;
  return (a.x - b.x) < 0;
}

// Andrew's Monotone Chain Algorithm
vector<point> monotone_chain(vector<point> P) {
  int N = P.size();

  if(N <= 3) // check if P[0] == P[N-1] and if N = 3 if they are collinear
    return P;

  vector<point> H(2*N);
  sort(P.begin(), P.end(), cmp);

  int k, start;

  k = start = 0;

  for(int i = 0; i < N; i++) {
    while(k-start >= 2 && !ccw(H[k-2], H[k-1], P[i]))
      k--;

    H[k++] = P[i];
  }

  k--;
  start = k;
  for(int i = N-1; i >= 0; i--) {
    while(k-start >= 2 && !ccw(H[k-2], H[k-1], P[i]))
      k--;
    H[k++] = P[i];
  }

  H.resize(k);

  return H;
}

// Graham scan algorithm
vector<point> CH(vector<point> P) {
  int i, N = P.size();

  if(N <= 3)
    return P;

  int P0 = 0;
  for(i = 1; i < N; i++)
    if(P[i].y < P[P0].y || (P[i].y == P[P0].y && P[i].x < P[P0].x))
      P0 = i;

  swap(P[0], P[P0]);

  pivot = P[0];
  sort(++P.begin(), P.end(), angleCmp);

  point prev, now;

  stack<point> S;
  S.push(P[N-1]); S.push(P[0]);
  i = 1;

  while(i < N) {
    now = S.top();
    S.pop(); prev = S.top(); S.push(now);

    if(ccw(prev, now, P[i])) S.push(P[i++]);
    else S.pop();
  }

  vector<point> ConvexHull;
  while(!S.empty()) {
    ConvexHull.push_back(S.top());
    S.pop();
  }
  return ConvexHull;
}

int main() {
  vector<point> P;
  P.push_back(point(0,0));
  P.push_back(point(2,0));
  P.push_back(point(2,2));
  P.push_back(point(1,2));
  P.push_back(point(0,2));

  print(P);
  print(CH(P));
  print(monotone_chain(P));

  return 0;
}
