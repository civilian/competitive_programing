#include <bits/stdc++.h>

using namespace std;

struct point {
  int x, y;
  point(int _x = 0, int _y = 0) {
    x = _x; y = _y;
  }

  int sqr(int x) {
    return x*x;
  }

  int dist2(point c) {
    return sqr(c.x - x) + sqr(c.y - y);
  }
};

struct rectangle {
  point a, b;
  rectangle(point _a, point _b) {
    a = _a; b = _b;
  }

  bool inside(point c) {
    return a.x <= c.x && c.x <= b.x && a.y <= c.y && c.y <= b.y;
  }
};

struct circle {
  point center;
  int r;
  circle(point c, int _r) {
    center = c;
    r = _r;
  }

  bool inside(point c) {
    return center.dist2(c) <= (r*r);
  }
};

int main() {
#ifdef LOCAL
  freopen("a.in", "r", stdin);
  freopen("a.txt", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
#endif

  int m, n;
  while(cin >> m) {
    if(m == 0)
      break;

    vector<rectangle> rs;
    vector<circle> cs;

    for(int i = 0; i < m; i++) {
      string w;
      cin >> w;

      if(w == "rectangle") {
        point p1, p2;
        cin >> p1.x >> p1.y >> p2.x >> p2.y;
        rs.push_back(rectangle(p1, p2));
      }
      else {
        point c;
        int r;
        cin >> c.x >> c.y >> r;
        cs.push_back(circle(c, r));
      }
    }

    cin >> n;
    for(int i = 0; i < n; i++) {
      point p;
      cin >> p.x >> p.y;
      int ans = 0;
      for(int i = 0; i < rs.size(); i++)
        if(rs[i].inside(p))
          ans++;
      for(int i = 0; i < cs.size(); i++)
        if(cs[i].inside(p))
          ans++;
      cout << ans << endl;
    }
  }

  return 0;
}
