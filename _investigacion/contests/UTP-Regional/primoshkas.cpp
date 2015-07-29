#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);
const int maxn = 10010;

int N, x[maxn], y[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> N) {
    for (int i = 0; i < N; i++)
      cin >> x[i] >> y[i];
    sort(x, x+N);
    sort(y, y+N);

    int xf = x[N / 2];
    int yf = y[N / 2];

    long long ans = 0;
    for (int i = 0; i < N; i++)
      ans += abs(xf - x[i]) + abs(yf - y[i]);

    cout << ans << '\n';
  }

  return 0;
}
