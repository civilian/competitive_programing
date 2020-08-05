#include <bits/stdc++.h>

using namespace std;

const int maxn = int(2e5)+10;

int n, sum, seq[maxn], dist[maxn];
bool seen[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n) {
    sum = 0;
    for (int i = 0; i < n; i++) {
      cin >> seq[i];
      sum += seq[i];
    }

    if (sum % 3 != 0) {
      cout << 0 << '\n';
      continue;
    }

    int arc = sum / 3;

    dist[0] = 0; // dist to point 0 from 0
    for (int i = 0; i < n; i++)
      dist[i+1] = dist[i] + seq[i];
    for (int i = 0; i < n; i++)
      dist[i+n+1] = dist[i+n] + seq[i];

    memset(seen, 0, sizeof(seen));
    int ans = 0;
    for (int i = 0; i < n; i++) if (!seen[i]) {
      int j = lower_bound(dist, dist+n+n, dist[i]+arc) - dist;
      if (dist[j] == dist[i]+arc) {
        int k = lower_bound(dist, dist+n+n, dist[j]+arc) - dist;
        if (dist[k] == dist[j]+arc) {
          int l = lower_bound(dist, dist+n+n, dist[k]+arc) - dist;
          if (dist[l] == dist[k]+arc) {
            l %= n;
            if (l == i) {
              ans++;
              seen[i] = seen[j] = seen[k] = true;
            }
          }
        }
      }
    }
    cout << ans << '\n';
  }

  return 0;
}
