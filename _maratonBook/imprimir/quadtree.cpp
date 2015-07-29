include <bits/stdc++.h>
 
using namespace std;
 
// Problem: UVa 11297 - Census
// Algorithm: Segment tree 2D.
 
const int maxn = 510;
const int INF = int(1e9);
 
struct node {
  int min, max;
  node(int mi=0, int ma=0) {
    min = mi;
    max = ma;
  }
} t[maxn<<2][maxn<<2];
 
int N, M, Q, a[maxn][maxn];
 
// build second coordinate
void build_x(int vy, int ly, int ry, int vx, int lx, int rx) {
  if (lx == rx) {
    if (ly == ry)
      t[vy][vx] = node(a[ly][lx], a[ly][lx]);
    else {
      int next = vy << 1;
      t[vy][vx].min = min(t[next][vx].min, t[next+1][vx].min);
      t[vy][vx].max = max(t[next][vx].max, t[next+1][vx].max);
    }
  }
  else {
    int tm = (lx + rx) >> 1;
    int next = vx << 1;
    build_x(vy, ly, ry, next, lx, tm);
    build_x(vy, ly, ry, next+1, tm+1, rx);
 
    t[vy][vx].min = min(t[vy][next].min, t[vy][next+1].min);
    t[vy][vx].max = max(t[vy][next].max, t[vy][next+1].max);
  }
}
 
// build first coordinate
void build_y(int vy, int tl, int tr) {
  if (tl != tr) {
    int tm = (tl + tr) >> 1;
    int next = vy << 1;
 
    build_y(next, tl, tm);
    build_y(next+1, tm+1, tr);
  }
  build_x(vy, tl, tr, 1, 0, M);
}
 
// update second coordinate
void update_x(int vy, int tl_y, int tr_y, int vx, int tl_x, int tr_x, int x, int y, int val) {
  if (tl_x == tr_x) {
    if (tl_y == tr_y)
      t[vy][vx] = node(val, val);
    else {
      t[vy][vx].min = min(t[vy << 1][vx].min, t[(vy<<1)+1][vx].min);
      t[vy][vx].max = max(t[vy << 1][vx].max, t[(vy<<1)+1][vx].max);
    }
  }
  else {
    int tmx = (tl_x + tr_x) >> 1;
    int next = vx << 1;
 
    if (x <= tmx)
      update_x(vy, tl_y, tr_y, next, tl_x, tmx, x, y, val);
    else
      update_x(vy, tl_y, tr_y, next+1, tmx+1, tr_x, x, y, val);
 
    t[vy][vx].min = min(t[vy][next].min, t[vy][next+1].min);
    t[vy][vx].max = max(t[vy][next].max, t[vy][next+1].max);
  }
}
 
// update first coordinate
void update_y(int vy, int tl_y, int tr_y, int x, int y, int val) {
  if (tl_y != tr_y) {
    int tmy = (tl_y + tr_y) >> 1;
    int next = vy << 1;
 
    if (y <= tmy)
      update_y(next, tl_y, tmy, x, y, val);
    else
      update_y(next+1, tmy+1, tr_y, x, y, val);
  }
  update_x(vy, tl_y, tr_y, 1, 0, M, x, y, val);
}
 
// query second coordinate
node query_x(int vy, int vx, int tl_x, int tr_x, int lx, int rx) {
  if (lx > tr_x || rx < tl_x)
    return node(INF, -INF);
  if (lx <= tl_x && tr_x <= rx)
    return t[vy][vx];
 
  int tmx = (tl_x + tr_x) >> 1;
  int next = vx << 1;
 
  node left_part = query_x(vy, next, tl_x, tmx, lx, rx);
  node right_part = query_x(vy, next+1, tmx+1, tr_x, lx, rx);
 
  return node(min(left_part.min, right_part.min), max(left_part.max, right_part.max));
}
 
// query first coordinate
node query_y(int vy, int tl_y, int tr_y, int lx, int rx, int ly, int ry) {
  if (ly > tr_y || ry < tl_y)
    return node(INF, -INF);
  if (ly <= tl_y && tr_y <= ry)
    return query_x(vy, 1, 0, M, lx, rx);
 
  int tmy = (tl_y + tr_y) >> 1;
  int next = vy << 1;
 
  node left_part = query_y(next, tl_y, tmy, lx, rx, ly, ry);
  node right_part = query_y(next+1, tmy+1, tr_y, lx, rx, ly, ry);
 
  return node(min(left_part.min, right_part.min), max(left_part.max, right_part.max));
}
 
int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
 
  while (cin >> N >> M) { // N => height, M => width
    for (int y = 0; y < N; y++) {
      for (int x = 0, v; x < M; x++) {
        cin >> v;
        a[y][x] = v;
      }
    }
    build_y(1, 0, N);
 
    cin >> Q;
    while (Q--) {
      int x1, y1, x2, y2, v;
      char op;
 
      cin >> op;
      if (op == 'c') { // update
        cin >> x1 >> y1 >> v;
        x1--, y1--;
        update_y(1, 0, N, y1, x1, v);
      }
      else { // query
        cin >> x1 >> y1 >> x2 >> y2;
        x1--, y1--, x2--, y2--;
        node ans = query_y(1, 0, N, min(y1, y2), max(y1, y2), min(x1, x2), max(x1, x2));
        cout << ans.max << ' ' << ans.min << '\n';
      }
    }
  }
 
  return 0;
}
