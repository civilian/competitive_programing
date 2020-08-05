#include <bits/stdc++.h>

using namespace std;

const int maxn = 500000;

int a[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int n, b;
  while (cin >> n >> b) {
    if (n < 0 && b < 0)
      break;

    int lo = 1, hi = 0, mid;
    for (int i = 0; i < n; i++) {
      cin >> a[i];
      hi = max(hi, a[i]);
    }

    while (lo < hi) {
      mid = lo + (hi-lo) / 2;

      int cnt = 0;
      for (int i = 0; i < n; i++) {
        cnt += (a[i] + mid - 1) / mid;
      }

      if (cnt <= b) hi = mid;
      else lo = mid+1;
    }
    cout << lo << '\n';
  }

  return 0;
}
