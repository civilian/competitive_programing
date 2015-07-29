#include <cassert>
#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <set>
#include <algorithm>
#include <bitset>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

const int INF = int(1e9);

long long L, H;
map<long long, vector<long long> > fp;
set<long long> primes;
map<long long, int> pkey;
vector<int> key;

int n, m, NIL;
vector<int> dist, match;
vector< vector<int> > g;

// left: primes, right: numbers

bool bfs() {
  queue<int> Q;

  for (int v = 0; v < n; v++) {
    dist[v] = INF;
    if (match[v] == NIL) {
      dist[v] = 0;
      Q.push(v);
    }
  }

  dist[NIL] = INF;

  while (!Q.empty()) {
    int v = Q.front();
    Q.pop();
    if (dist[v] < dist[NIL]) {
      for (int i = 0; i < g[v].size(); i++) {
        int u = g[v][i];
        if (dist[match[u]] == INF) {
          dist[match[u]] = dist[v]+1;
          Q.push(match[u]);
        }
      }
    }
  }

  return dist[NIL] != INF;
}

bool dfs(int v) {
  if (v != NIL) {
    for (int i = 0; i < g[v].size(); i++) {
      int u = g[v][i];
      if (dist[match[u]] == dist[v]+1 && dfs(match[u])) {
        match[u] = v;
        match[v] = u;
        return true;
      }
    }
    dist[v] = INF;
    return false;
  }
  return true;
}

int hopcroft_karp() {
  NIL = n+m;
  for (int i = 0; i < n+m; i++)
    match[i] = NIL;

  int matching = 0;
  for (int v = 0; v < n; v++) {
    for (int i = 0; i < g[v].size(); i++) {
      int u = g[v][i];
      if (match[u] == NIL) {
        matching++;
        match[u] = v;
        match[v] = u;
        break;
      }
    }
  }

  while (bfs()) {
    for (int v = 0; v < n; v++)
      if (match[v] == NIL && dfs(v))
        matching++;
  }
  return matching;
}


vector<long long> factorize(long long q) {
  vector<long long> f;

  for (long long p = 2; p*p <= q; p++) {
    if (q % p == 0) {
      f.push_back(p);
      while (q % p == 0)
        q /= p;
    }
  }

  if (q > 1)
    f.push_back(q);

  return f;
}

void build_graph() {
  pkey.clear();
  key.clear();

  foreach (it, primes) {
    pkey[*it] = pkey.size()-1;
    key.push_back(*it);
  }

  g.assign(n, vector<int>());

  for (long long i = L, k = 0; i <= H; i++, k++) {
    vector<long long> v = fp[i];
    for (int j = 0; j < v.size(); j++)
      g[pkey[v[j]]].push_back(n+k);
  }

  dist.assign(n+m+10, NIL);
  match.assign(n+m+10, NIL);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> L >> H) {
    if (L == 0 && H == 0)
      break;

    fp.clear();
    primes.clear();
    for (long long i = L; i <= H; i++) {
      fp[i] = factorize(i);
      primes.insert(fp[i].begin(), fp[i].end());
    }

    m = H-L+1;
    n = primes.size();

    build_graph();

    assert(hopcroft_karp() == m);

    for (int i = 0; i < m; i++) {
      if (i) cout << ' ';
      cout << key[match[n+i]];
      //assert((L+i) % key[match[n+i]] == 0);
    }
    cout << '\n';
  }

  return 0;
}
