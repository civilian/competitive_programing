#include <cstdio>
#include <algorithm>

using namespace std;

int main() {
  int t;
  scanf("%d", &t);

  for(int run = 1; run <= t; run++) {
    int k;
    scanf("%d", &k);
    int best = -1;
    for(int i = 0; i < k; i++) {
      int c;
      scanf("%d", &c);
      best = max(best, c);
    }
    printf("Caso %d: %d %d\n", run, k+1, best+1);
  }

  return 0;
}
