#include <bits/stdc++.h>

using namespace std;

int compareTo(double x, double y, double tol=1e-9) {
  return (x <= y + tol) ? (x + tol < y) ? -1 : 0 : 1;
}

struct event {
  int ind, type;
  event() {}
  event(int i, int t) :
    ind(i), type(t) {}
};

struct point {
  double x, y;
};

const int maxn = 5010;

int n, e;
point rects[maxn][2];
vector<event> events_v, events_h;
vector<bool> in_set;
double area;

bool cmp_x(event a, event b) {
  int r = compareTo(rects[a.ind][a.type].x, rects[b.ind][b.type].x);
  if (r == 0)
    return compareTo(rects[a.ind][a.type].y, rects[b.ind][b.type].y) < 0;
  return r < 0;
}

bool cmp_y(event a, event b) {
  int r = compareTo(rects[a.ind][a.type].y, rects[b.ind][b.type].y);
  if (r == 0)
    return compareTo(rects[a.ind][a.type].x, rects[b.ind][b.type].x) < 0;
  return r < 0;
}

void solve() {
  area = 0;
  e = events_v.size();
  sort(events_v.begin(), events_v.end(), cmp_x);
  sort(events_h.begin(), events_h.end(), cmp_y);

  in_set.assign(e, 0);

  in_set[events_v[0].ind] = true;

  for (int i = 1; i < e; i++) {
    event c = events_v[i];
    int cnt = 0;

    double delta_x = rects[c.ind][c.type].x - rects[events_v[i-1].ind][events_v[i-1].type].x;
    //if (compareTo(delta_x, 0) <= 0)
    //  continue;

    double begin_y = 0;
    for (int j = 0; j < e; j++) {
      if (in_set[events_h[j].ind]) {
        if (events_h[j].type == 0) {
          if (cnt == 0) {
            begin_y = rects[events_h[j].ind][0].y;
          }
          cnt++;
        }
        else {
          cnt--;
          if (cnt == 0) {
            double delta_y = (rects[events_h[j].ind][1].y - begin_y);
            area += delta_x * delta_y;
          }
        }
      }
    }
    in_set[c.ind] = (c.type == 0);
  }

  printf("%.2lf\n", area);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  e = 0;
  while (cin >> n) {
    if (n == 0)
      break;

    events_v.clear();
    events_h.clear();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 2; j++)
        cin >> rects[i][j].x >> rects[i][j].y;
      events_v.push_back(event(i, 0)); events_h.push_back(event(i, 0));
      events_v.push_back(event(i, 1)); events_h.push_back(event(i, 1));
    }
    solve();
  }

  return 0;
}
