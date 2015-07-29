#include <bits/stdc++.h>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

struct point {
  long long x, y;
  int index;
  point() {}
  point(long long x, long long y) : x(x), y(y) {}
  bool operator < (const point &p) const { return (x*p.y - y*p.x) < 0; };
};

const int maxn = 2e5;
int above_list[maxn], below_list[maxn];
point total[maxn>>2], above[maxn>>2], below[maxn>>2];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int N, W, H;
  int tc = 1;

  while (cin >> N >> W >> H) {
    int M = 0, T = 0;
    for (int i = 0; i < N; i++) {
      cin >> total[i].x >> total[i].y;
      total[i].x = 2 * total[i].x - W; // translate
      total[i].y = 2 * total[i].y - H;
      total[i].index = i;
      //cerr << "c.index => " << c.index << '\n';

      if (total[i].y > 0 || (total[i].y == 0 && total[i].x > 0)) {
        above[M].x = total[i].x, above[M].y = total[i].y;
        above[M++].index = i;
      }
      else {
        below[T].x = total[i].x, below[T].y = total[i].y;
        below[T++].index = i;
      }
    }

    //cerr << "Sorting...\n";
    sort(above, above+M);
    sort(below, below+T);

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

    if (M > T) {
      for (int i = 0; i < T; i++, absz++)
        above_list[abt++] = below[i].index;
      for (int i = 0; i < M; i++, besz++)
        below_list[bet++] = above[i].index;
    }
    else {
      for (int i = 0; i < M; i++, absz++)
        above_list[abt++] = above[i].index;
      for (int i = 0; i < T; i++, besz++)
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
