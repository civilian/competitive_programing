#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <ctime>
#include <cctype>
#include <cassert>
#include <iostream>
#include <sstream>
#include <iomanip>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <deque>
#include <list>
#include <set>
#include <map>
#include <bitset>
#include <algorithm>
#include <numeric>
#include <complex>

#define D(x) cerr << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

long long sieve_size;
bitset<10010> bs;

void sieve(long long upperbound) {
  sieve_size = upperbound+1;
  bs.set();
  bs[0] = bs[1] = 0;
  for(long long i = 2; i <= upperbound; i++) if(bs[i]) {
    for(long long j = i*i; j <= upperbound; j+=i)
      bs[j] = 0;
  }
}

bool is_prime(string n) {
  int x = atoi(n.c_str());
  return bs[x];
}

bool seen[10000];

int bfs(string s, string t) {
  queue< pair<string, int> > q;
  memset(seen, 0, sizeof seen);

  q.push(make_pair(s,0));
  seen[atoi(s.c_str())] = true;

  while(!q.empty()) {
    pair<string,int> curr = q.front();
    q.pop();

    if(curr.first == t)
      return curr.second;

    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 10; j++) {
        if(i == 0 && j == 0) continue;
        char old = curr.first[i];
        curr.first[i] = '0' + j;

        if(!seen[atoi(curr.first.c_str())]) {
          if(is_prime(curr.first)) {
            q.push(make_pair(curr.first, curr.second+1));
          }
          seen[atoi(curr.first.c_str())] = true;
        }

        curr.first[i] = old;
      }
    }
  }

  return -1;
}

int main() {
#ifdef LOCAL
  freopen("inputs/prime_path.in", "r", stdin);
  freopen("outputs/prime_path.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
#endif
  sieve(10000);

  int t;
  cin >> t;

  while(t--) {
    string a, b;
    cin >> a >> b;

    int ans = bfs(a, b);
    if(ans == -1) cout << "Impossible" << endl;
    else cout << ans << endl;
  }

  return 0;
}

