#include <bits/stdc++.h>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)

using namespace std;

const int maxn = 22;

int turns[maxn];
vector<int> codes[maxn+1];

int getsum(int mask) {
  int sum = 0;

  for (int i = 0; i < maxn; i++)
    if (mask & (1 << i))
      sum += i+1;

  return sum;
}

int msb(int mask) {
  int pos = 0, i = 0;
  while (mask > 0) {
    if (mask & 1)
      pos = i;
    i++;
    mask >>= 1;
  }
  return pos;
}

void precalc() {
  for (int mask = 0; mask < (1 << maxn); mask++) {
    int sum = getsum(mask);
    if (sum <= maxn)
      codes[sum].push_back(mask);
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  precalc();
  int N, T, tc = 1;

  while (cin >> N >> T) {
    if ((N|T) == 0)
      break;

    for (int i = 0; i < T; i++)
      cin >> turns[i];

    int ans = 0;
    set<int> seen;
    seen.insert(0);

    for (int i = 0; i < T; i++) {
      int turn = turns[i];

      set<int> next_seen;

      foreach (it, seen) {
        int mask = *it;
        for (int j = 0; j < codes[turn].size(); j++) {
          int next_mask = codes[turn][j];
          if ((mask & next_mask) == 0 && msb(next_mask) < N) {
            next_mask |= mask;
            next_seen.insert(next_mask);
            ans = max(ans, __builtin_popcount(next_mask));
          }
        }
      }

      seen = next_seen;
      if (seen.empty())
        break;
    }

    cout << "Game " << tc++ << ": " << ans << '\n';
  }

  return 0;
}
