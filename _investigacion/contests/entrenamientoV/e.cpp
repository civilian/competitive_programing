#include <bits/stdc++.h>

using namespace std;

// Binary search + Digit DP

int d[20];
bool seen[20][2][2];
unsigned long long memo[20][2][2];

unsigned long long dp(int index, int tope, int one) {
  if (index < 0)
    return 1;

  if (seen[index][tope][one])
    return memo[index][tope][one];

  int limit = tope? d[index] : 9;

  unsigned long long r = 0;
  for (int i = 0; i <= limit; i++) {
    if (i == 4 || (one && i == 3)) continue;

    r += dp(index-1, i != d[index]? 0 : tope, i == 1);
  }

  seen[index][tope][one] = true;
  return memo[index][tope][one] = r;
}

unsigned long long get_count(unsigned long long N) {
  int n = 0;
  while (N > 0) {
    d[n++] = N % 10;
    N /= 10;
  }

  memset(seen, 0, sizeof(seen));
  return dp(n-1, 1, d[n-1] == 1)-1;
}

unsigned long long solve(unsigned long long N) {
  unsigned long long lo = 1, hi = numeric_limits<unsigned long long>::max();

  while (lo < hi) {
    unsigned long long mid = lo + ((hi - lo) >> 1);

    unsigned long long r = get_count(mid);
    if (r >= N) hi = mid;
    else lo = mid+1;
  }

  return lo;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  unsigned long long N;

  while (cin >> N)
    cout << solve(N) << '\n';

  return 0;
}
