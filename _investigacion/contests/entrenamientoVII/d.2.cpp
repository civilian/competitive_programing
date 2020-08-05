#include <bits/stdc++.h>

using namespace std;

// Accepted

struct point {
  int x, y;
  point(int x = 0, int y = 0) : x(x), y(y) { }
};

int N;
vector<point> P, H;

double cross(point p, point q, point r) {
  return (r.x - q.x) * (p.y - q.y) - (r.y - q.y) * (p.x - q.x);
}

bool collinear(point p, point q, point r) {
  return cross(p, q, r) == 0;
}

bool ccw(point p, point q, point r) {
  return cross(p, q, r) > 0;
}

bool cmp(const point& a, const point& b) {
  if(a.x == b.x)
    return a.y < b.y;
  return a.x < b.x;
}

vector<point> & monotone_chain() {

  if(N <= 3) // check if P[0] == P[N-1] and if N = 3 if they are collinear
    return P;

  H.resize(2*N);
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

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cin >> N;
  P.clear();

  for (int i = 0; i < N; i++) {
    int x, y;
    cin >> x >> y;
    P.push_back(point(x,y));
  }

  vector<point> & ch = monotone_chain();
  double ans = -1;

  for (int i = 0; i < ch.size(); i++) {
    for (int j = 0; j < i; j++)
      ans = max(ans, hypot(ch[i].x-ch[j].x, ch[i].y-ch[j].y));
  }
  cout.precision(10);
  cout.setf(ios::fixed);
  cout << ans << '\n';

  return 0;
}
