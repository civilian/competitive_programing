#include <bits/stdc++.h>

using namespace std;

const int maxn = 22;

int n, moves, cnt[10], times_used[10];
string g[maxn];
bool seen[maxn][maxn];

int dr[] = {1, 0, -1, 0};
int dc[] = {0, 1, 0, -1};

bool valid(int nr, int nc) {
  return nr >= 0 && nr < n && nc >= 0 && nc < n && g[nr][nc] != '*';
}

void dfs_count(int r, int c, int color) {
  if (seen[r][c])
    return;

  seen[r][c] = true;
  cnt[color]++;

  for (int i = 0; i < 4; i++) {
    int nr = r + dr[i];
    int nc = c + dc[i];

    if (valid(nr, nc) && g[nr][nc] - '0' == color)
      dfs_count(nr, nc, color);
  }
}

void dfs_paint(int r, int c, int color) {
  g[r][c] = '*';
  for (int i = 0; i < 4; i++) {
    int nr = r + dr[i];
    int nc = c + dc[i];

    if (valid(nr, nc) && g[nr][nc] - '0' == color)
      dfs_paint(nr, nc, color);
  }
}

int find_best_color() {
  memset(seen, 0, sizeof seen);
  memset(cnt, 0, sizeof cnt);

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (g[i][j] == '*') {
        for (int k = 0; k < 4; k++) {
          int nr = i + dr[k];
          int nc = j + dc[k];

          if (valid(nr, nc) && !seen[nr][nc])
            dfs_count(nr, nc, g[nr][nc] - '0');
        }
      }
    }
  }

  int best_color = 1;
  for (int i = 2; i <= 6; i++) {
    if (cnt[best_color] < cnt[i])
      best_color = i;
  }
  times_used[best_color]++;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (g[i][j] == '*') {
        for (int k = 0; k < 4; k++) {
          int nr = i + dr[k];
          int nc = j + dc[k];

          if (valid(nr, nc) && g[nr][nc] - '0' == best_color)
            dfs_paint(nr, nc, best_color);
        }
      }
    }
  }
}

bool check() {
  for (int i = 0; i < n; i++)
    for (int j = 0; j < n; j++)
      if (g[i][j] != '*')
        return false;
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    cin >> n;
    for (int i = 0; i < n; i++)
      cin >> g[i];

    moves = 0;
    memset(times_used, 0, sizeof times_used);
    dfs_paint(0, 0, g[0][0] - '0');

    while (!check()) {
      find_best_color();
      moves++;
    }

    cout << moves << '\n';
    cout << times_used[1];
    for (int i = 2; i <= 6; i++)
      cout << ' ' << times_used[i];
    cout << endl;
  }

  return 0;
}
