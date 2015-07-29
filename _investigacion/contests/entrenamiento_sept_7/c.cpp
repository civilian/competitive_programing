#include <bits/stdc++.h>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

// Problem: Fixing Traffic (UCF Local Contest - August 31, 2013)
// Algorithm: Maxflow (Push & Relabel algorithm) O(V^3)

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

int read_int(char lim = ',') {
  string s;
  getline(cin, s, lim);
  return atoi(s.c_str());
}

struct streets {
  int from, to, cap;
  string name;
  streets() {};
  streets(int from, int to, int cap, string s) :
    from(from), to(to), cap(cap), name(s) {}
} sts[222];

set<string> street_names;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t, n, e;

  cin >> t;

  while (t--) {
    cin >> n >> e;
    cin.get();

    street_names.clear();

    PushRelabel orig(n);

    for (int i = 0; i < e; i++) {
      int from, to, cap;
      string name;
      from = read_int();
      to = read_int();
      cap = read_int();
      getline(cin, name);

      sts[i] = streets(from, to, cap, name);
      street_names.insert(name);

      orig.addEdge(sts[i].from, sts[i].to, sts[i].cap);
    }

    long long orig_flow = orig.getMaxFlow(0, n-1);

    long long ans_flow = -INF;
    string ans_name;
    // Increase flow to infinity for every street
    foreach (t, street_names) {
      string st = *t;
      PushRelabel curr(n);
      for (int i = 0; i < e; i++) {
        int cap = sts[i].cap;
        if (sts[i].name == st)
          cap = INF;
        curr.addEdge(sts[i].from, sts[i].to, cap);
      }

      long long curr_flow = curr.getMaxFlow(0, n-1);
      if (curr_flow - orig_flow > ans_flow) {
        ans_flow = curr_flow - orig_flow;
        ans_name = st;
      }
    }

    cout << ans_name << ' ' << ans_flow << '\n';
  }

  return 0;
}
