#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);
const int mod = INF+7;

const int maxn = 50010;

int n, k, a[maxn];
long long comb[maxn][20];

int count_lis() {
  int cnt = 0;

  for (int i = 0; i < n; i++) {
    int j = i+1;
    while (j < n && a[j] > a[j-1])
      j++;
    cnt++;
    i = j-1;
  }

  return cnt;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  for (int i = 0; i < maxn; i++) {
    for (int j = 0; j < 20; j++) {
      if (j == 0 || j == i)
        comb[i][j] = 1;
      else
        comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]) % mod;
    }
  }

  int t;
  cin >> t;

  while (t--) {
    cin >> n >> k;
    for (int i = 0; i < n; i++)
      cin >> a[i];

    int cnt = count_lis();
    cout << comb[cnt][k] << '\n';
  }

  return 0;
}
