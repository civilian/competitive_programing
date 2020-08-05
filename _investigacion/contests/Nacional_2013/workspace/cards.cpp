#include <cstring>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

const int maxn = 1050;

int n;
int p[maxn], a[maxn], ciclo[maxn], ans[maxn];

int p_total[maxn][maxn];

long long R;

int find_ciclo(int i) {
  int pos = i;
  int length = 1;
  p_total[0][i] = pos;
  while (i != p[pos]) {
    pos = p[pos];
    p_total[length][i] = pos;
    length++;
  }

  return length;
}

int main() {
#ifdef LOCAL
  freopen("big_f.in", "r", stdin);
  freopen("cards.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
#endif

  while (cin >> n >> R) {
    for (int i = 0; i < n; i++) {
      cin >> a[i];
      a[i]--;
      p[a[i]] = i;
    }

    if (R == 0) {
      for (int i = 0; i < n; i++) {
        if (i) cout << ' ';
        cout << i+1;
      }
      cout << '\n';
      continue;
    }

    for (int i = 0; i < n; i++) {
      ciclo[i] = find_ciclo(i);
    }

    for (int i = 0; i < n; i++) {
      if (ciclo[i] == 1) {
        ans[p[i]] = i+1;
      }
      else {
        long long next = R % ciclo[i];
        int pos = p_total[next][i];
        ans[pos] = i+1;
      }
    }

    for (int i = 0; i < n; i++) {
      if (i) cout << ' ';
      cout << ans[i];
    }
    cout << '\n';
  }

  return 0;
}
