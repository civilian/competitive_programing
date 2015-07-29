#include <bits/stdc++.h>

using namespace std;

const int maxn = 111;

long long sum[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int N, M, K, W;
  while (cin >> N >> M >> K >> W) {
    if ((N|M|K|W) == 0)
      break;

    memset(sum, 0, sizeof sum);
    for (int i = 0; i < N; i++) {
      int a, b, c;
      cin >> a >> b >> c;

      int diff = W - c + 1;
      for (int i = 0; i < b; i++) {
        int bi;
        cin >> bi;
        sum[bi] += diff * M;
      }
    }

    for (int i = 1; i <= K; i++)
      cout << sum[i] << '\n';
  }

  return 0;
}
