#include <bits/stdc++.h>

using namespace std;

const int maxn = 1000010;

pair<int,int> a[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int N, K1, K2, tc = 1;

  while (cin >> N >> K1 >> K2) {
    if ((N|K1|K2) == 0)
      break;
    for (int i = 0; i < N; i++) {
      cin >> a[i].first;
      a[i].second = i+1;
    }

    sort(a, a+N);

    cout << "Case " << tc++ << '\n';

    vector<int> ans;
    for (int i = 0; i < K1; i++)
      ans.push_back(a[i].second);
    sort(ans.begin(), ans.end());
    for (int i = 0; i < K1; i++) {
      if (i) cout << ' ';
      cout << ans[i];
    }
    cout << '\n';

    ans.clear();
    for (int i = 0; i < K2; i++)
      ans.push_back(a[N-i-1].second);
    sort(ans.rbegin(), ans.rend());
    for (int i = 0; i < K2; i++) {
      if (i) cout << ' ';
      cout << ans[i];
    }
    cout << '\n';
  }

  return 0;
}
