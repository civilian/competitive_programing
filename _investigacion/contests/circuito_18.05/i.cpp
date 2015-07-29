#include <bits/stdc++.h>

#define D(x) cout << #x " = " << x << endl;

using namespace std;

const int INF = int(1e9);
int dp[111][722];

int main() {
#ifdef LOCAL
  freopen("i.in", "r", stdin);
  freopen("i.txt", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
#endif

  int n, R;
  while(cin >> n >> R) {
    if((n|R) == 0)
      break;
    memset(dp, 0, sizeof(dp));

    for(int i = 0; i < n; i++) {
      int ri, t;
      cin >> ri >> t;
      dp[ri][t] = dp[ri][t+360] = 1;
    }

    for(int i = 1; i < 720; i++) {
      dp[0][i] += dp[0][i-1];
    }
    for(int i = 1; i <= R; i++)
      dp[i][0] += dp[i-1][0];

    for(int i = 1; i <= R; i++) {
      for(int j = 1; j < 720; j++) {
        dp[i][j] += dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
      }
    }

    int e;
    cin >> e;

    while(e--) {
      int H, A;
      cin >> H >> A;

      int ans = -INF;

      for(int i = 0; i+H <= R; i++) {
        for(int j = 0; j < 360; j++) {
          int curr = dp[i+H-1][j+A];
          if(j > 0)
            curr -= dp[i+H-1][j-1];
          if(i > 0)
            curr -= dp[i-1][j+A];
          if(i > 0 && j > 0)
            curr += dp[i-1][j-1];
          ans = max(ans, curr);
        }
      }
      cout << ans << endl;
    }
  }

  return 0;
}
