#include <bits/stdc++.h>

using namespace std;

// Live Archive: 4509 - Haunted Graveyard
// Algoritm: Bellman Ford

const int INF = int(1e9);

struct edge {
  int to, cost;
  edge(int t=0, int c=0) {
    to = t;
    cost = c;
  }
};

int W, H, V, dist[1000];
char b[33][33];
bool seen[1000];
vector<edge> g[1000];

int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};

inline
int get_pos(int x, int y) {
  return y * W + x;
}

void build() {
  for (int y = 0; y < H; y++) {
    for (int x = 0; x < W; x++) {
      if (b[y][x] != '.' || (y == H-1 && x == W-1)) continue;

      int from = get_pos(x,y);
      for (int k = 0; k < 4; k++) {
        int ny = y + dy[k];
        int nx = x + dx[k];

        if (nx >= 0 && nx < W && ny >= 0 && ny < H)
          if (b[ny][nx] != 'X')
            g[from].push_back(edge(get_pos(nx,ny), 1));
      }
    }
  }
}

bool bellman_ford(int s) {
  // seen => detects if node is rechable
  for (int i = 0; i < V; i++)
    dist[i] = INF, seen[i] = 0;
  dist[s] = 0;
  seen[s] = 1;

  for (int i = 0; i < V-1; i++)
    for (int u = 0; u < V; u++)
      for (int j = 0; j < g[u].size(); j++) {
        edge e = g[u][j];
        if (seen[u]) seen[e.to] = 1;
        dist[e.to] = min(dist[e.to], dist[u] + e.cost);
      }

  for (int u = 0; u < V; u++)
    for (int j = 0; j < g[u].size(); j++) {
      edge e = g[u][j];
      if (seen[u] && dist[u] + e.cost < dist[e.to])
        return true; // negative cycle
    }
  return false;
}

void solve() {
  V = W*H;

  if (bellman_ford(0))
    cout << "Never\n";
  else if (!seen[V-1])
    cout << "Impossible\n";
  else
    cout << dist[V-1] << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> W >> H) {
    if ((W|H) == 0)
      break;

    memset(b, '.', sizeof(b));
    int G, E;

    cin >> G;
    for (int i = 0; i < G; i++) {
      int x, y;
      cin >> x >> y;
      b[y][x] = 'X';
    }

    cin >> E;
    for (int i = 0; i < E; i++) {
      int x1, y1, x2, y2, t, u, v;
      cin >> x1 >> y1 >> x2 >> y2 >> t;
      b[y1][x1] = 'H';

      u = get_pos(x1, y1);
      v = get_pos(x2, y2);
      g[u].push_back(edge(v, t));
    }
    build();
    solve();

    for (int i = 0; i < V; i++)
      g[i].clear();
  }

  return 0;
}

