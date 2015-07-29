#include <bits/stdc++.h>

using namespace std;

const int maxn = 10010;

bool a[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int n, r;
  while (cin >> n >> r) {
    memset(a, 0, n+1);
    for (int i = 0, v; i < r; i++) {
      cin >> v;
      a[v] = 1;
    }

    if (n == r) {
      cout << "*\n";
      continue;
    }

    bool first = false;
    for (int i = 0; i < n; i++) {
      if (!a[i+1]) {
        cout << i+1 << ' ';
      }
    }
    cout << '\n';
  }

  return 0;
}
