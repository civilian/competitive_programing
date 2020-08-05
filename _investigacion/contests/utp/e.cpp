#include <cstdio>
#include <cmath>
#include <algorithm>

using namespace std;

int main() {
  int n;
  while(scanf("%d", &n)) {
    if(n == 0)
      break;

    int bb, be;
    scanf("%d%d", &bb, &be);

    double best = be*log(bb);
    for(int i = 1; i < n; i++) {
      int b, e;
      scanf("%d%d", &b, &e);
      double curr = e*log(b);
      if(curr > best) {
        best = curr;
        bb = b; be = e;
      }
    }
    printf("%d %d\n", bb, be);
  }
  return 0;
}

