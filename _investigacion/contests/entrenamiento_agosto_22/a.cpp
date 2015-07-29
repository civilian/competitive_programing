#include <bits/stdc++.h>

using namespace std;

const int maxn = 26;

int cnt[maxn];
double circuit[maxn][maxn];

inline
double calculate_parallel(double a, double b) {
  double sum = 1. / a + 1. / b;
  return 1. / sum;
}

bool check() {
  for (int i = 1; i+1 < maxn; i++)
    if (cnt[i] > 0)
      return false;
  return cnt[0] == 1 && cnt[maxn-1] == 1;
}

double solve() {
  double ans = -1.0;

  while (true) {
    bool changed = false;

    for (int i = 1; i+1 < maxn; i++) {
      if (cnt[i] != 2) continue;
      for (int j = 0; j < maxn; j++) {
        if (i == j || circuit[j][i] < 0) continue;
        for (int k = 0; k < maxn; k++) {
          if (j == k || circuit[k][i] < 0) continue;

          cnt[i] = 0;
          double new_val = circuit[j][i] + circuit[i][k];

          circuit[j][i] = circuit[i][j] = -1;
          circuit[k][i] = circuit[i][k] = -1;

          if (circuit[j][k] < 0) {
            circuit[j][k] = circuit[k][j] = new_val;
          }
          else {
            cnt[j]--; cnt[k]--;
            circuit[j][k] = calculate_parallel(circuit[j][k], new_val);
            circuit[k][j] = circuit[j][k];
          }
          changed = true;
        }
      }
    }

    if (!changed)
      break;
  }

  if (check())
    ans = circuit[0][maxn-1];
  else
    ans = -1;

  return ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cout.precision(3);
  cout.setf(ios::fixed);

  int n;
  while (cin >> n) {
    if (n == 0)
      break;

    for (int i = 0; i < maxn; i++) {
      cnt[i] = 0;
      for (int j = 0; j < maxn; j++)
        circuit[i][j] = -1;
    }

    for (int i = 0, r; i < n; i++) {
      char a, b;
      cin >> a >> b >> r;

      int u = a-'A';
      int v = b-'A';

      if (circuit[u][v] < 0) {
        cnt[u]++;
        cnt[v]++;
        circuit[u][v] = circuit[v][u] = r;
      }
      else {
        double new_r = calculate_parallel(circuit[u][v], r);
        circuit[u][v] = circuit[v][u] = new_r;
      }
    }

    cout << solve() << '\n';
  }

  return 0;
}
