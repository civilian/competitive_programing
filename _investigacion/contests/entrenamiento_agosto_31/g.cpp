#include <cstring>
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

const int maxn = 500;

long long L, H, ans[maxn];
int count_p[maxn];
bool used[maxn], has_p[maxn];
set<long long> candidates[maxn];
bool solved;

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

void backt(int pos) {
  if (!solved) {
    if (pos > H-L) {
      for (long long i = L; i <= H; i++) {
        if (i-L) cout << ' ';
        cout << ans[i-L];
      }
      cout << '\n';

      solved = true;
      return;
    }

    pos++;
    long long next = 0;
    for (long long i = L; i <= H; i++) {
      if (!has_p[i-L] && (next == 0 || count_p[i-L] == 1))
          next = i;
    }

    has_p[next-L] = true;
    foreach (it, candidates[next-L]) {
      long long c = *it;

      // already used
      if (c <= H-L && used[c])
        continue;

      bool failed = false;

      for (long long i = L; i <= H; i++) {
        if (!has_p[i-L] && candidates[i-L].count(c)) {
          count_p[i-L]--;
          if (count_p[i-L] == 0)
            failed = true;
        }
      }

      if (!failed) {
        ans[next-L] = c;
        if (c <= H-L) used[c] = true;

        backt(pos);

        if (solved)
          break;
        if (c <= H-L) used[c] = false;
      }

      for (long long i = L; i <= H; i++) {
        if (!has_p[i-L] && candidates[i-L].count(c))
          count_p[i-L]++;
      }
    }
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> L >> H) {
    if (L == 0 && H == 0)
      break;

    memset(count_p, 0, sizeof(count_p));
    for (long long i = L; i <= H; i++)
      candidates[i-L].clear();

    for (long long i = L; i <= H; i++) {
      vector<long long> p = factorize(i);
      candidates[i-L].insert(p.begin(), p.end());
      count_p[i-L] = p.size();
    }

    solved = false;
    memset(has_p, 0, sizeof(has_p));
    memset(used, 0, sizeof(used));
    backt(0);
  }

  return 0;
}
