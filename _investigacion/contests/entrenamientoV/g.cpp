#include <bits/stdc++.h>

using namespace std;

int a[25], b[25], c[25];

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int n;
  while (cin >> n) {
    for (int i = 0; i < n; i++)
      cin >> a[i];
    for (int i = 0; i < n; i++)
      cin >> b[i];

    int ans = 0;
    for (int i = 0; i < n; i++) {
      if (b[i] != a[i]) {
        int j, l = 0;
        for (j = i+1; j < n; j++)
          if (b[i] == a[j])
            break;

        for (int k = 0; k < i; k++, l++)
          c[k] = a[l];

        c[i] = a[j];
        ans += j-i;

        for (int k = i+1; l < n; l++)
          if (l != j)
            c[k++] = a[l];


        for (int k = 0; k < n; k++)
          a[k] = c[k];
      }
    }

    cout << ans << '\n';
  }

  return 0;
}
