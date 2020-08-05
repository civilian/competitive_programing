#include <bits/stdc++.h>

using namespace std;

const int maxn = 310;

int R, C, a[maxn][maxn], c[maxn], q[maxn*maxn];

bool check() {
  int val = 1;
  for (int i = 0; i < R; i++) {
    for (int j = 0; j < C; j++) {
      if (a[i][j] != val)
        return false;
      val++;
    }
  }
  return true;
}

bool valid_row(int r) {
  for (int i = 0; i < C; i++)
    if (a[r][i] > C)
      return false;
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> R >> C) {
    int ini_r = 0;

    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        cin >> a[i][j];
        if (a[i][j] == 1)
          ini_r = i;
      }
    }

    if (!valid_row(ini_r)) {
      cout << '*' << '\n';
      continue;
    }

    for (int i = 0; i < C; i++)
      c[i] = a[ini_r][i];
    sort(c, c+C);

    for (int i = 0; i < C; i++)
      q[c[i]] = i;

    int ans = 0;
    for (int i = 0; i < C; i++) {
      while (a[ini_r][i] != c[i]) {
        ans++;
        int pos = q[a[ini_r][i]];
        for (int j = 0; j < R; j++)
          swap(a[j][i], a[j][pos]);
      }
    }

    for (int i = 0; i < R; i++)
      c[i] = a[i][0];
    sort(c, c+R);

    for (int i = 0; i < R; i++)
      q[c[i]] = i;

    for (int i = 0; i < R; i++) {
      while (a[i][0] != c[i]) {
        ans++;
        int pos = q[a[i][0]];
        for (int j = 0; j < C; j++)
          swap(a[i][j], a[pos][j]);
      }
    }

    if (check())
      cout << ans << '\n';
    else
      cout << "*\n";
  }

  return 0;
}
