#include <bits/stdc++.h>

using namespace std;

int cnt[13];

int main() {
  int n;
  scanf("%d", &n);

  memset(cnt, 0, sizeof(cnt));
  for (int i = 0; i < n; i++) {
    int id, d, m, y;
    scanf("%d %d/%d/%d", &id, &d, &m, &y);
    cnt[m]++;
  }

  for (int i = 1; i <= 12; i++)
    printf("%d %d\n", i, cnt[i]);

  return 0;
}
