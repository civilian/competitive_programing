#include <cstring>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;
const long long INF = int(1e18);
const int maxn = 222;
const int max_edges = 1500;//100*100 mas los otros

int N, R, x[maxn], y[maxn];
//long long dist[maxn][maxn];

struct flow {
  long long cost,flow;
};

struct edge{
  int v;
  long long cost, cap, flow;
  edge(int v=0, long long cap=0, long long cost=0, long long flow=0) :
    v(v), cap(cap), cost(cost), flow(flow) { }
};

int row[2][maxn], lbl[2][maxn], qf[2];
long long dist[maxn];
edge edges[max_edges];
int degree[maxn], parent[maxn], adj[maxn][max_edges], NV, S, T, e_cnt;

struct MCMF {
  void inic(int n){
    //configure your needs!!
    NV = n+2, S=n, T = n+1, e_cnt = 0;
    for (int i = 0; i < NV; i++) degree[i] = 0;
  }
  
  void addEdge(int u, int v, long long cap, long long cost) {
    edges[e_cnt++] = edge(v, cap, cost, 0);
    edges[e_cnt++] = edge(u, 0, -cost, 0);
    adj[u][degree[u]++] = e_cnt - 2;
    adj[v][degree[v]++] = e_cnt - 1;
  }
  
  void addToSource(int u, long long cap, long long cost) {
    addEdge(S, u, cap, cost);
  }
  
  void addToSink(int u, long long cap, long long cost) {
    addEdge(u, T, cap, cost);
  }
  
  bool bellman() {
    for (int i = 0; i < NV; i++) {
      dist[i] = INF;
      lbl[0][i] = lbl[1][i] = 0;
      parent[i] = -1;
    }
    qf[0] = 0; row[0][qf[0]++] = S;
    parent[S] = -2; dist[S] = 0;
    
    for (int k = 0; k < NV; k++) {
      int row_at = k & 1;
      int next_row = 1 - row_at;
      qf[next_row] = 0;
      for (int i = 0; i < qf[row_at]; i++) {
        int v = row[row_at][i];
        lbl[row_at][v] = 0;
        for (int j = 0; j < degree[v]; j++) {
          edge e = edges[adj[v][j]];
          edge ei = edges[adj[v][j] ^ 1];
          int w = e.v;
          long long cap = e.cap - e.flow, cost = e.cost;
          if (ei.flow)
            cost = -ei.cost;
          if (cap > 0 && dist[w] > dist[v] + cost) {
            if (!lbl[next_row][w]) {
              row[next_row][qf[next_row]++] = w;
              lbl[next_row][w] = 1;
            }
            dist[w] = dist[v] + cost;
            parent[w] = adj[v][j];
          }
        }
      }
    }
    return parent[T] != -1;
  }
  
  flow mcmf() {
    flow f = (flow) { 0, 0 };
    while (bellman()) {
      long long flow = INF, cost = 0;
      for (int v = T; parent[v] >= 0; v = edges[parent[v] ^ 1].v) {
        flow = min(flow, edges[parent[v]].cap - edges[parent[v]].flow);
      }
      for (int v = T; parent[v] >= 0; v = edges[parent[v] ^ 1].v) {
        edge &e = edges[parent[v]];
        edge &ei = edges[parent[v] ^ 1];
        
        if (ei.flow)
          cost += flow * (-ei.cost);
        else
          cost += flow * e.cost;
        e.flow += flow;
        ei.flow -= flow;
      }
      f.flow += flow;
      f.cost += cost;
    }
    return f;
  }
};

int main() {
#ifdef LOCAL
  freopen("minutes.in", "r", stdin);
  freopen("minutes.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
#endif

  while (cin >> N >> R) {
    for (int i = 0; i < R; i++)
      cin >> x[i] >> y[i];
      
    for (int i = 0; i < N; i++)
      cin >> x[R+i] >> y[R+i];
      
    MCMF graph;
    graph.inic(R+N);
    for (int i = 0; i < R; i++) {
      graph.addToSource(i, 1, 0);
      for (int j = 0; j < N; j++) {
        long long dist = abs(x[i]-x[R+j]) + abs(y[i]-y[R+j]);
        graph.addEdge(i, R+j, 1, dist);
      }
    }
    for (int i = 0; i < N; i++) {
      graph.addToSink(R+i, 1, 0);
    }
    
    flow ans = graph.mcmf();
    cout << ans.cost << '\n';
  }

  return 0;
}
