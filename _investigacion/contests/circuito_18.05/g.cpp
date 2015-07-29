#include <bits/stdc++.h>

using namespace std;

const double EPS = 1e-11;

struct point {
  int x, y;
  point(int _x = 0, int _y = 0) {
    x = _x; y = _y;
  }
};

struct line {
  double a, b, c;
} lines[111];

int w, n;
bool seen[111], allParallel;

void pointsToLine(point p1, point p2, line& l) {
  if(p1.x == p2.x) {
    l.a = 1.0; l.b = 0.0; l.c = -p1.x;
  }
  else {
    l.a = -(double)(p1.y - p2.y) / (p1.x - p2.x);
    l.b = 1.0;
    l.c = -(double)(l.a * p1.x) - (l.b * p1.y);
  }
}

bool areParallel(line l1, line l2) {
  return (fabs(l1.a - l2.a) < EPS) && (fabs(l1.b - l2.b) < EPS);
}

bool areSame(line l1, line l2) {
  return areParallel(l1, l2) && (fabs(l1.c - l2.c) < EPS);
}

bool areIntersect(line l1, line l2) {
  if(areSame(l1, l2)) return false;
  if(areParallel(l1, l2)) return false;

  return true;
}

void mark(int i) {
  for(int j = i+1; j < n; j++) {
    if(!seen[j]) {
      if(areSame(lines[i], lines[j]))
        seen[j] = true;
      else if(areIntersect(lines[i], lines[j]))
        allParallel = false;
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);

  while(cin >> w >> n) {
    if((w|n) == 0)
      break;

    for(int i = 0; i < n; i++) {
      int x1, y1, x2, y2;
      cin >> x1 >> y1 >> x2 >> y2;
      pointsToLine(point(x1, y1), point(x2, y2), lines[i]);
    }

    int cnt = 0;
    allParallel = true;
    memset(seen, 0, sizeof(seen));
    for(int i = 0; i < n; i++) {
      if(!seen[i]) {
        seen[i] = true;
        mark(i);
        cnt++;
      }
    }

    int ans = 0;
    if(allParallel) {
      int areas = cnt + 1;
      if(areas >= w) ans = 0;
      else ans = max(1, (w+1) / 2 - cnt);
    }
    else {
      int areas = 2 * cnt;
      if(areas >= w) ans = 0;
      else ans = max(1, (w+1) / 2 - cnt);
    }
    cout << ans << endl;
  }

  return 0;
}
