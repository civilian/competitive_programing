#include <bits/stdc++.h>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

const int maxn = 100010;
const long long INF = (long long)(1e18);

typedef pair<long long,long long> point;
#define X first
#define Y second

int n, m;
set<long long> x, y;
point p[maxn];
set<point> ps[2];

void read_path(string s) {
  char axis;
  long long v;
  axis = s[0];
  v = atoi(s.substr(2).c_str());

  if (axis == 'x')
    x.insert(v);
  else
    y.insert(v);
}

bool is_left(int i, point p, int v) {
  set<point>::iterator it = ps[i].find(point(p.Y, p.X));

  if (it != ps[i].begin()) {
    it--;
    point q(it->Y, it->X);
    if (q.Y != p.Y || (q.X < v && v < p.X))
      return true;
    else
      return false;
  }

  return true;
}

bool is_right(int i, point p, int v) {
  set<point>::iterator it = ps[i].find(point(p.Y, p.X));
  it++;

  if (it != ps[i].end()) {
    point q(it->Y, it->X);
    if (q.Y != p.Y || (p.X < v && v < q.X))
      return true;
    else
      return false;
  }

  return true;
}

bool visible(int i, bool axis) {
  set<long long>::iterator it;

  if (axis) {
    it = x.lower_bound(p[i].X);
    if (it != x.begin()) {
      it--;

      if (is_left(1, p[i], *it))
        return true;

      it++;
    }
    if (it != x.end()) {
      if (is_right(1, p[i], *it))
        return true;
    }
  }
  else {
    it = y.lower_bound(p[i].Y);
    if (it != y.begin()) {
      it--;

      if (is_left(0, point(p[i].Y, p[i].X), *it))
        return true;

      it++;
    }
    if (it != y.end()) {
      if (is_right(0, point(p[i].Y, p[i].X), *it))
        return true;
    }
  }

  return false;
}

void solve() {
  int ans = 0;

  for (int i = 0; i < n; i++)
    if (visible(i, true) || visible(i, false))
      ans++;
  cout << ans << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n >> m) {
    if ((n|m) == 0)
      break;

    x.clear();
    y.clear();
    ps[0].clear();
    ps[1].clear();

    for (int i = 0; i < n; i++) {
      cin >> p[i].X >> p[i].Y;
      ps[0].insert(p[i]);
      ps[1].insert(point(p[i].Y, p[i].X));
    }

    for (int i = 0; i < m; i++) {
      string s;
      cin >> s;
      read_path(s);
    }

    solve();
  }

  return 0;
}
