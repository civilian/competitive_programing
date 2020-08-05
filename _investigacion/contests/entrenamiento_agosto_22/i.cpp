#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);

const int maxn = 222;
const int maxt = 201*201;

int n, m, total_spaces, start_r, start_c;
char b[maxn][maxn];
queue< pair<int,int> > Q;

int minDist[maxn][maxn];
int spaces_count[maxt], cum_num_spaces[maxt];
double avg_dist[maxt];

void push(int r, int c, int dist) {
  if (r < 0 || r >= n || c < 0 || c >= m)
    return;
  if (b[r][c] == 'X')
    return;
  if (minDist[r][c] < INF)
    return;

  minDist[r][c] = dist;
  Q.push(make_pair(r,c));
}

void bfs() {
  while (!Q.empty()) {
    pair<int,int> curr = Q.front();
    Q.pop();
    int r = curr.first;
    int c = curr.second;

    int new_dist = minDist[r][c] + 1;

    push(r+1, c, new_dist);
    push(r-1, c, new_dist);
    push(r, c+1, new_dist);
    push(r, c-1, new_dist);
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cout.precision(3);
  cout.setf(ios::fixed);

  while (cin >> n >> m) {
    if ((n|m) == 0)
      break;

    while (!Q.empty()) Q.pop();
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        minDist[i][j] = INF;

    total_spaces = start_r = start_c = 0;

    for (int i = 0; i < n; i++) {
      cin >> b[i];
      for (int j = 0; j < m; j++) {
        if (b[i][j] != 'X')
          total_spaces++;

        if (b[i][j] == 'Y') {
          start_r = i;
          start_c = j;
        }

        if (b[i][j] == 'E') {
          Q.push(make_pair(i,j));
          minDist[i][j] = 0;
        }
      }
    }

    bfs();

    memset(spaces_count, 0, sizeof(spaces_count));
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        if (minDist[i][j] < INF)
          spaces_count[minDist[i][j]]++;

    cum_num_spaces[0] = spaces_count[0];
    avg_dist[0] = 0;

    for (int i = 1; i < total_spaces; i++) {
      cum_num_spaces[i] = spaces_count[i] + cum_num_spaces[i-1];
      avg_dist[i] = i * spaces_count[i] + avg_dist[i-1];
    }

    for (int i = 0; i < total_spaces; i++)
      avg_dist[i] = avg_dist[i] / cum_num_spaces[i];

    double best_exp_value = 1e200;
    int best = 0;

    for (int dist = 0; dist < total_spaces; dist++) {
      double exp_value = 1.0 * total_spaces / cum_num_spaces[dist];
      exp_value += 1.0 * avg_dist[dist];

      if (exp_value < best_exp_value) {
        best_exp_value = exp_value;
        best = dist;
      }
    }

    double ans = best_exp_value;
    if (minDist[start_r][start_c] <= best)
      ans = minDist[start_r][start_c];

    cout << ans << '\n';
  }

  return 0;
}
