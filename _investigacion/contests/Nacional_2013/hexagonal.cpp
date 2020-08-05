#include <bits/stdc++.h>

using namespace std;

struct hexagon {
  int a[6], pos[7];
} h[7];

int a[7], p[6][2];

bool check() {
  int m = a[6];

  for (int i = 0; i < 6; i++) {
    int c = h[m].a[i];
    int j = a[i];

    int prev = h[j].a[(h[j].pos[c] + 5) % 6];
    int next = h[j].a[(h[j].pos[c] + 1) % 6];

    p[i][0] = next;
    p[i][1] = prev;
  }

  for (int i = 1; i < 5; i++) {
    if (p[i][0] != p[i-1][1] || p[i][1] != p[i+1][0])
      return false;
  }

  if (p[0][0] != p[5][1])
    return false;

  return true;
}

bool solve() {
  for (int j = 0; j < 7; j++) {
    a[6] = j;
    for (int i = 0, k = 0; i < 7; i++) {
      if (i != j)
        a[k++] = i;
    }

    do {
      if (check())
        return true;
    } while (next_permutation(a, a+6));
  }
  return false;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (true) {
    for (int i = 0; i < 7; i++) {
      if (cin.eof())
        return 0;
      for (int j = 0; j < 6; j++) {
        cin >> h[i].a[j];
        h[i].pos[h[i].a[j]] = j;
      }
      a[i] = i;
    }
    cout << (solve()? "YES" : "NO") << '\n';
  }

  return 0;
}
