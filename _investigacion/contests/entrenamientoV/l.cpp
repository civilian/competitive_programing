#include <bits/stdc++.h>

using namespace std;

long long solve(long long q) {
  long long ans = 1;

  if (q % 2 == 0) {
    q >>= 1;
    while (!(q & 1)) {
      q >>= 1;
      ans <<= 1;
    }
  }

  for (long long p = 3; p*p <= q; p += 2) {
    if (q % p == 0) {
      ans *= (p-1);
      q /= p;
      while (q % p == 0) {
        q /= p;
        ans *= p;
      }
    }
  }

  if (q > 1)
    ans *= (q-1);

  return ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  long long N;
  while (cin >> N) {
    long long ans = solve(N) >> 1;
    cout << ans << '\n';
  }

  return 0;
}
