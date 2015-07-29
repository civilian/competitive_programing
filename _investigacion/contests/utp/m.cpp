#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);

int n, m;
string s, seq;
char g[511][511];
long long dp[511][511], sum[511];
bool alpha[30], c[30];

long long kadane(int& l, int& r) {
  long long max_sum = -INF;
  long long current_sum = 0;

  for(int j = 0, i = 0; j < m; j++) {
    current_sum += sum[j];

    if(current_sum > max_sum) {
      l = i; r = j;
      max_sum = current_sum;
    }

    if(current_sum < 0) {
      current_sum = 0;
      i = j+1;
    }
  }
  return max_sum;
}

bool check(int i1, int j1, int i2, int j2) {
  memset(c, 0, 30);

  for(int i = i1; i <= i2; i++)
    for(int j = j1; j <= j2; j++)
      c[g[i][j]-'a'] = true;

  for(int i = 0; i < seq.size(); i++)
    if(!c[seq[i]-'a'])
      return false;
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);

  while(cin >> n >> m) {
    if((n|m) == 0)
      break;
    cin >> seq;
    memset(alpha, 0, sizeof(alpha));

    for(int i = 0; i < seq.size(); i++)
      alpha[seq[i]-'a'] = true;

    for(int i = 0; i < n; i++) {
      cin >> s;
      for(int j = 0; j < m; j++) {
        g[i][j] = s[j];
        if(alpha[s[j] - 'a'])
          dp[i][j] = 1;
        else
          dp[i][j] = -INF;
      }
    }

    long long ans = 0;

    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++)
        sum[j] = 0;

      for(int j = i; j < n; j++) {
        for(int k = 0; k < m; k++)
          sum[k] += dp[j][k];
        int j1, j2;
        long long curr = kadane(j1, j2);
        if(curr > ans) {
          if(check(i, j1, j, j2)) {
            ans = curr;
          }
        }
      }
    }
    printf("%lld\n", ans);
  }

  return 0;
}

