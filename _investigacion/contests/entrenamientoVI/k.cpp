#include <bits/stdc++.h>

using namespace std;

int dr[] = {0, 1, 0, -1};
int dc[] = {1, 0, -1, 0};

const long long INF = int(1e9);

struct Edge {
  int from, to, cap, flow, index;
  Edge(int from, int to, int cap, int flow, int index) :
    from(from), to(to), cap(cap), flow(flow), index(index) {}
};

struct PushRelabel {
  int N;
  vector< vector<Edge> > G;
  vector<long long> excess;
  vector<int> dist, active, count;
  queue<int> Q;

  PushRelabel(int N) : N(N), G(N), excess(N), dist(N), active(N), count(2*N) {}

  void addEdge(int from, int to, int cap) {
    G[from].push_back(Edge(from, to, cap, 0, G[to].size()));
    if (from == to) G[from].back().index++;
    G[to].push_back(Edge(to, from, 0, 0, G[from].size() - 1));
  }

  void enqueue(int v) {
    if (!active[v] && excess[v] > 0) {
      active[v] = true;
      Q.push(v);
    }
  }

  void push(Edge& e) {
    int amt = int(min(excess[e.from], (long long)(e.cap - e.flow)));
    if (dist[e.from] <= dist[e.to] || amt == 0)
      return;
    e.flow += amt;
    G[e.to][e.index].flow -= amt;
    excess[e.to] += amt;
    excess[e.from] -= amt;
    enqueue(e.to);
  }

  void gap(int k) {
    for (int v = 0; v < N; v++) {
      if (dist[v] < k)
        continue;
      count[dist[v]]--;
      dist[v] = max(dist[v], N+1);
      count[dist[v]]++;
      enqueue(v);
    }
  }

  void relabel(int v) {
    count[dist[v]]--;
    dist[v] = 2*N;
    for (int i = 0; i < G[v].size(); i++)
      if (G[v][i].cap - G[v][i].flow > 0)
        dist[v] = min(dist[v], dist[G[v][i].to] + 1);
    count[dist[v]]++;
    enqueue(v);
  }

  void discharge(int v) {
    for (int i = 0; excess[v] > 0 && i < G[v].size(); i++)
      push(G[v][i]);
    if (excess[v] > 0) {
      if (count[dist[v]] == 1)
        gap(dist[v]);
      else
        relabel(v);
    }
  }

  long long getMaxFlow(int s, int t) {
    count[0] = N-1;
    count[N] = 1;
    dist[s] = N;
    active[s] = active[t] = true;
    for (int i = 0; i < G[s].size(); i++) {
      excess[s] += G[s][i].cap;
      push(G[s][i]);
    }

    while (!Q.empty()) {
      int v = Q.front();
      Q.pop();
      active[v] = false;
      discharge(v);
    }

    long long totflow = 0;
    for (int i = 0; i < G[s].size(); i++)
      totflow += G[s][i].flow;

    return totflow;
  }
};

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (!cin.eof()) {
    vector<string> b;
    string ln;
    while (getline(cin, ln)) {
      if (ln.empty())
        break;
      b.push_back(ln);
    }
    int n = b.size();
    int m = b[0].size();

    int total = n * m;
    int N = 2 * total + 2;
    int source = N - 2;
    int sink = source + 1;

    PushRelabel mf(N);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        mf.addEdge(i * m + j, total + i * m + j, 1);

        if (b[i][j] == 'W')
          mf.addEdge(source, i * m + j, 1);
        if (b[i][j] == 'N')
          mf.addEdge(total + i * m + j, sink, 1);
        for (int k = 0; k < 4; k++) {
          int r = i + dr[k];
          int c = j + dc[k];

          if (r >= 0 && r < n && c >= 0 && c < m) {
            if ((b[i][j] == 'W' && b[r][c] == 'I') ||
                (b[i][j] == 'I' && b[r][c] == 'N'))
              mf.addEdge(total + i * m + j, r * m + c, 1);
          }
        }
      }
    }

    cout << mf.getMaxFlow(source, sink) << '\n';
  }

  return 0;
}
