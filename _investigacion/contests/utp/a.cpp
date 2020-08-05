#include <cstdio>
#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

int sqr(int x) {
  return x*x;
}

int compareTo(double x, double y, double tol=1e-9) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

struct point {
  int x, y;
  double dist(const point& b) const {
    return sqrt(sqr(b.x-x) + sqr(b.y-y));
  }
} pivot;

struct asteroid {
  point c;
  int r, i;

  bool operator<(const asteroid& b) const {
    double da = pivot.dist(c) - r;
    double db = pivot.dist(b.c) - b.r;

    return compareTo(da, db) < 0;
  }

} a[1010];

int main() {
  int n;
  while(scanf("%d", &n)) {
    if(n == 0)
      break;
    scanf("%d%d", &pivot.x, &pivot.y);
    for(int i = 0; i < n; i++) {
      scanf("%d%d%d", &a[i].c.x, &a[i].c.y, &a[i].r);
      a[i].i = i+1;
    }
    sort(a,a+n);
    printf("%d\n", a[0].i);
  }
  return 0;
}

