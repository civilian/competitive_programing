#include <cstdio>
#include <iostream>
#include <algorithm>

using namespace std;

const int maxn = 100010;

int a[maxn];

int main() {
  ios_base::sync_with_stdio(false);
  int n;
  while(cin >> n) {
    if(n == 0)
      break;
    for(int i = 0; i < n; i++)
      cin >> a[i];
    sort(a, a+n);

    double ans;
    if(n & 1) {
      ans = a[n >> 1];
    }
    else {
      int mid = n>>1;
      ans = (a[n>>1] + a[(n>>1)-1]) * .5;
    }
    printf("%.1lf\n", ans);
  }
  return 0;
}
