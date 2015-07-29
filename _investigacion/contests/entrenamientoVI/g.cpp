#include <bits/stdc++.h>

using namespace std;

char bin[56];

string to_bin(long long x) {
  string t;

  while (x > 0) {
    if (x & 1) t.push_back('1');
    else t.push_back('0');
    x >>= 1;
  }

  reverse(t.begin(), t.end());
  return t;
}

string num;
int n;
long long memo[64][2][64]; // index, tope, count ones

long long dp(int index, int top, int count_ones) {
  if (memo[index][top][count_ones] != -1)
    return memo[index][top][count_ones];

  if (index == n) {
    if (count_ones != 0 && count_ones % 3 == 0)
      return memo[index][top][count_ones] = 1;
    return memo[index][top][count_ones] = 0;
  }

  long long ans = 0;
  if (!top || num[index] == '1')
    ans += dp(index+1, top && num[index] == '1', count_ones+1);
  ans += dp(index+1, top && num[index] == '0', count_ones);

  return memo[index][top][count_ones] = ans;
}

long long solve(long long N) {
  num = to_bin(N);
  n = num.size();
  memset(memo, -1, sizeof(memo));

  return dp(0, 1, 0);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  long long N;
  while (cin >> N)
    cout << "Day " << N << ": Level = " << solve(N) << '\n';

  return 0;
}
