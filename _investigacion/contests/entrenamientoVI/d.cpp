#include <bits/stdc++.h>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

struct point {
  long long x, y;
  int index;
  point() {}
  point(long long x, long long y) : x(x), y(y) {}
  //point(const point &p) : x(p.x), y(p.y), index(p.index)    {}
  point operator + (const point &p)  const { return point(x+p.x, y+p.y); }
  point operator - (const point &p)  const { return point(x-p.x, y-p.y); }
  point operator * (long long c)     const { return point(x*c,   y*c  ); }
  point operator / (long long c)     const { return point(x/c,   y/c  ); }
  bool operator < (const point &p) const { return (x*p.y - y*p.x) < 0; };
};

long long dot(point p, point q)     { return p.x*q.x+p.y*q.y; }
long long dist2(point p, point q)   { return dot(p-q,p-q); }
long long cross(const point& p, const point& q)   { return p.x*q.y-p.y*q.x; }
ostream &operator<<(ostream &os, const point &p) {
  os << "(" << p.x << "," << p.y << ")";
}

bool cmp(const point& p, const point& q) {
  return cross(p, q) < 0;
}

const int maxn = 2e5;
int above_list[maxn], below_list[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int N, W, H;
  int tc = 1;

  while (cin >> N >> W >> H) {
    vector<point> above, below, total;
    for (int i = 0; i < N; i++) {
      point c;
      cin >> c.x >> c.y;
      c.x = 2 * c.x - W; // translate
      c.y = 2 * c.y - H;
      c.index = i;
      //cerr << "c.index => " << c.index << '\n';

      if (c.y > 0 || (c.y == 0 && c.x > 0))
        above.push_back(c);
      else
        below.push_back(c);
      total.push_back(c);
    }

    //cerr << "Sorting...\n";
    sort(above.begin(), above.end());
    sort(below.begin(), below.end());

    //cerr << "above => { ";
    //for (int i = 0; i < above.size(); i++)
      //cerr << above[i].index << ' ';
    //cerr << "}\n";
    //cerr << "below => { ";
    //for (int i = 0; i < below.size(); i++)
      //cerr << below[i].index << ' ';
    //cerr << "}\n";
    int abh = 0, abt = 0, absz = 0;
    int beh = 0, bet = 0, besz = 0;

    if (above.size() > below.size()) {
      for (int i = 0; i < below.size(); i++, absz++)
        above_list[abt++] = below[i].index;
      for (int i = 0; i < above.size(); i++, besz++)
        below_list[bet++] = above[i].index;
    }
    else {
      for (int i = 0; i < above.size(); i++, absz++)
        above_list[abt++] = above[i].index;
      for (int i = 0; i < below.size(); i++, besz++)
        below_list[bet++] = below[i].index;
    }

    //int iter = 0;
    while (absz < besz) {
      int i = below_list[beh];
      if (absz == 0) {
        above_list[abt++] = i;
        absz++;
        beh++; besz--;
      }
      else {
        int j = above_list[abh];

        if (!(total[i] < total[j])) {
          above_list[abt++] = i;
          absz++;
          beh++; besz--;
        }
        else {
          below_list[bet++] = j;
          besz++;
          abh++; absz--;
        }
      }
      //iter++;
    }

    for (int i = abh; i < abt; i++) {
      int p = above_list[i];
      cout << ((total[p].x + W) / 2) << ' ' << ((total[p].y + H) / 2) << '\n';
    }
  }

  return 0;
}
