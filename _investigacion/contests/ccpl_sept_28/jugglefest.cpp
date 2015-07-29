#include <bits/stdc++.h>

using namespace std;

int N, a[10];
char ans[22];

bool solve() {
  int p = 0;
  char ch = 'A';
  while (p < 20) {
    for (int i = 0; i < N && p < 20; i++) {
      if (ans[p] == ' ')
        ans[p] = ch++;
      if (p + a[i] < 20) {
        if (ans[p+a[i]] != ' ')
          return false;
        ans[p+a[i]] = ans[p];
      }
      p++;
    }
  }
  return true;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> N) {
    if (N == 0)
      break;
    for (int i = 0; i < N; i++)
      cin >> a[i];

    memset(ans, ' ', sizeof(ans));
    if (!solve())
      cout << "CRASH\n";
    else {
      ans[20] = 0;
      cout << ans << '\n';
    }
  }

  return 0;
}
