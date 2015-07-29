#include <bits/stdc++.h>

#define D(x) cerr <<  #x " = " << x << endl

using namespace std;

int main() {
  int a, u, v;
  while(~scanf("%d%d%d", &a, &u, &v)) {
    int t = 0;
    int x = a, y = 0, s = 0;

    while(x > s || y > s) {
      x += u; y += v;
      s += (t+1);
      t++;
    }

    printf("%d\n", t);
  }

  return 0;
}
