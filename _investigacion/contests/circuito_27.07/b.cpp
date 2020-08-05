#include <bits/stdc++.h>

using namespace std;

const int INF = int(1e9);
const int maxn = 100010;

int n, x[maxn], r[maxn], ord[maxn], dp[maxn][2], ans[maxn], ind;
pair< pair<int,int>, int> in[maxn];

int t[maxn<<2];

void update(int v, int tl, int tr, int pos, int val) {
  if (tl == tr) {
    t[tl] = val;
  }
  else {
    int tm = (tl + tr) >> 1;
    int next = v << 1;

    if (pos <= tm)
      update(next, tl, tm, pos, val);
    else
      update(next+1, tm+1, tr, pos, val);
    t[v] = max(t[next], t[next+1]);
  }
}

int query(int v, int tl, int tr, int l, int r) {
  if (l > tr || r < tl)
    return -INF;

  if (l <= tl && tr <= r)
    return t[v];

  int tm = (tl + tr) >> 1;
  int next = v << 1;

  int left_part = query(next, tl, tm, l, r);
  int right_part = query(next+1, tm+1, tr, l, r);

  return max(left_part, right_part);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n) {
    if (n == 0) break;

    for (int i = 0; i < n; i++) {
      cin >> in[i].first.first >> in[i].first.second;
      in[i].second = i;
    }
    sort(in, in+n);

    for (int i = 0; i < n; i++) {
      x[i] = in[i].first.first;
      r[i] = in[i].first.second;
      ord[i] = in[i].second;
    }

    memset(dp, 0, sizeof(dp));

    memset(t, 0, sizeof(t));
    update(1, 0, n, 0, dp[0][0]);

    for (int k = 1; k < n; k++) {
      int i = lower_bound(x, x+n, x[k]-r[k]) - x;
      if (x[k]-r[k] > x[i])
        i++;
      int s = query(1, 0, n, i, k);
      dp[k][0] = s; // !!!
    }

    memset(t, 0, sizeof(t));
    for (int k = n-2; k >= 0; k--) {
      int i = lower_bound(x, x+n, x[k]+r[k]) - x;
      if (i == n || x[k]+r[k] < x[i])
        i--;
      dp[k][1] = dp[i][1] + i-k;
    }

    for (int i = 0; i < n; i++)
      ans[ord[i]] = dp[i][0]+dp[i][1]+1;

    for (int i = 0; i < n; i++) {
      if (i) cout << ' ';
      cout << ans[i];
    }

    cout << '\n';
  }
  cout << INT_MIN << endl;

  return 0;
}
