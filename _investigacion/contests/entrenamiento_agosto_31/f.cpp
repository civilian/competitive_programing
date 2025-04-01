#include <cstdlib>
#include <cstring>
#include <cassert>
#include <cmath>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <utility>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

int dx[] = {1, 0, -1, 0}; // rigth, down, left, up
int dy[] = {0, -1, 0, 1};

int dir, x, y;

void rotate(int d) {
  if (d == 0) // left
    dir = (dir-1+4) % 4;
  else if (d == 1) // rigth
    dir = (dir+1) % 4;
  else if (d == 2) // uturn
    dir = (dir+2) % 4;
}

void move(int v) {
  x += dx[dir] * v;
  y += dy[dir] * v;
}

void to(int xf, int yf) {
  int delta_x = abs(xf-x);

  if (delta_x == 0) { // move y
    int delta_y = abs(yf - y);

    if (yf < y) { // move down
      if (dir == 0) { // rigth
        cout << "RIGHT\n";
        rotate(1);
      }
      else if (dir == 2) { // left
        cout << "LEFT\n";
        rotate(0);
      }
      else if (dir == 3) { // uturn
        cout << "UTURN\n";
        rotate(2);
      }
    }
    else { // move up
      if (dir == 1) { // uturn
        cout << "UTURN\n";
        rotate(2);
      }
      else if (dir == 0) { // down
        cout << "LEFT\n";
        rotate(0);
      }
      else if (dir == 2) { // up
        cout << "RIGHT\n";
        rotate(1);
      }
    }
    move(delta_y);
    cout << "MOVE " << delta_y << '\n';

    return;
  }

  if (xf < x) { // move left
    if (dir == 0) { // uturn
      cout << "UTURN\n";
      rotate(2);
    }
    else if (dir == 1) { // down
      cout << "RIGHT\n";
      rotate(1);
    }
    else if (dir == 3) { // up
      cout << "LEFT\n";
      rotate(0);
    }
  }
  else { // move rigth
    if (dir == 2) { // uturn
      cout << "UTURN\n";
      rotate(2);
    }
    else if (dir == 1) { // down
      cout << "LEFT\n";
      rotate(0);
    }
    else if (dir == 3) { // up
      cout << "RIGHT\n";
      rotate(1);
    }
  }
  move(delta_x);
  cout << "MOVE " << delta_x << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  for (int tc = 1; tc <= t; tc++) {
    x = y = dir = 0;
    int xc, yc, xd, yd, n;

    cin >> n;
    for (int i = 0; i < n; i++) {
      string m;
      cin >> m;
      if (m == "MOVE") {
        int v;
        cin >> v;
        move(v);
      }
      else {
        if (m == "LEFT")
          rotate(0);
        else if (m == "RIGHT")
          rotate(1);
        else
          rotate(2);
      }
    }
    cin >> xc >> yc >> xd >> yd;

    cout << "Robot Program #" << tc << ":\n";
    cout << "The robot is at (" << x << "," << y << ")\n";

    while (x != xc || y != yc)
      to(xc, yc);
    while (x != xd || y != yd)
      to(xd, yd);

    cout << "\n";
  }

  return 0;
}
