#include <bits/stdc++.h>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

const int maxn = 200010;

#define LSOne(S) (S & (-S))

int n;

class BIT {
public:
  long long t[maxn], n;
  BIT(int m) {
    n = ++m;
    fill(t, t+n, 0);
  }

  long long rsq(int idx) {
    long long sum = 0;
    for (; idx; idx -= (idx & -idx))
      sum += t[idx];
    return sum;
  }

  // not use with adjust range
  int rsq(int a, int b) {
    return rsq(b) - (a == 1 ? 0 : rsq(a-1));
  }

  void adjust(int k, int v) {
    for (; k <= n; k += (k & -k))
      t[k] += v;
  }

  void increment_at(int index) {
    adjust(index, 1);
  }

  void adjust_range(int a, int b, int v) {
    adjust(a, v);
    adjust(b+1, -v);
  }
};

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n) {
    if (n == 0)
      break;

    BIT t(n);

    map<string,int> a;
    for (int i = 0; i < n; i++) {
      string name;
      cin >> name;
      a[name] = i+1;
    }

    long long ans = 0;
    for (int i = 0; i < n; i++) {
      string name;
      cin >> name;

      int v = a[name];
      ans += t.rsq(v+1, n);
      t.adjust(v, 1);
    }
    cout << ans << '\n';
  }

  return 0;
}
