#include <bits/stdc++.h>

using namespace std;

const int maxn = 10010;

vector<int> g1[maxn], g2[maxn];
int N, deg1[maxn], deg2[maxn];
map<int,int> m1, m2;

bool solve() {
  if (m1.size() != m2.size())
    return false;

  map<int,int>::iterator it1, it2;
  it1 = m1.begin();
  it2 = m2.begin();

  while (it1 != m1.end() && it2 != m2.end()) {
    int k1 = it1->first, v1 = it1->second;
    int k2 = it2->first, v2 = it2->second;

    if (k1 != k2 || v1 != v2)
      return false;

    it1++;
    it2++;
  }
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> N) {
    for (int i = 0; i < N; i++) {
      g1[i].clear();
      g2[i].clear();
    }

    memset(deg1, 0, sizeof(deg1));
    memset(deg2, 0, sizeof(deg2));

    for (int i = 0; i+1 < N; i++) {
      int u, v;
      cin >> u >> v;
      u--, v--;
      g1[u].push_back(v);
      g1[v].push_back(u);
      deg1[u]++;
      deg1[v]++;
    }

    for (int i = 0; i+1 < N; i++) {
      int u, v;
      cin >> u >> v;
      u--, v--;
      g2[u].push_back(v);
      g2[v].push_back(u);
      deg2[u]++;
      deg2[v]++;
    }

    m1.clear();
    m2.clear();
    for (int i = 0; i < N; i++) {
      m1[deg1[i]]++;
      m2[deg2[i]]++;
    }

    cout << (solve()? 'S' : 'N') << '\n';
  }

  return 0;
}
