#include <bits/stdc++.h>

#define D(x) cerr << #x " = " << x << endl

using namespace std;

int k, c[15], sum;
double dp[1<<16];

double solve(int mask, int turns = 0) {
  if(mask == (1 << k))
    return 1.0;

  if(dp[mask] < 0) {
    dp[mask] = 0;
    for(int i = 0; i < k; i++) {
      int card = __builtin_popcount(mask);
      if((mask & (1 << i)) == 0)
        dp[mask] += (double)c[i] / (double)(sum-card) * solve(mask | (1<<i), turns+1);
      else
        dp[mask] += (double)(c[i]-1) / (double)(sum-card) * (turns+1); // probability depends of turns used to take the second sox.
    }
  }

  return dp[mask];
}

int main() {
  int t;
  scanf("%d", &t);

  for(int run = 1; run <= t; run++) {
    scanf("%d", &k);

    sum = 0;
    for(int i = 0; i < k; i++) {
      scanf("%d", c+i);
      sum += c[i];
    }

    for (int i = 0; i < 1<<16; i++) dp[i] = -1;
    printf("Case %d: %.3lf\n", run, solve(0));
  }

  return 0;
}
