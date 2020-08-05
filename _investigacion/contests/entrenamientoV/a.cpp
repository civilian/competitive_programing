#include <bits/stdc++.h>

using namespace std;

const int maxn = 1010;

int n, l, c;
int a[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> n >> l >> c) {
    for (int i = 0; i < n; i++) {
      string s;
      cin >> s;
      a[i] = s.size();
    }
    int pages = 1, lines = 1, line_count = 0;

    for (int i = 0; i < n; i++) {
      if (line_count + a[i] <= c) {
        line_count += a[i]+1;
      }
      else {
        line_count = a[i]+1;
        lines++;
      }

      if (lines > l) {
        pages++;
        lines = 1;
      }
    }

    cout << pages << '\n';
  }

  return 0;
}
