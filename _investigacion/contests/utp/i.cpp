#include <cstdio>
#include <iostream>
#include <iomanip>
#include <set>
#include <algorithm>

#define D(x) cerr << #x " = " << x << endl

using namespace std;

int main() {
  int n;
  while(scanf("%d", &n)) {
    if(n == 0)
      break;

    multiset<int> s;
    multiset<int>::iterator it;
    long long sum = 0;
    int med;

    for(int i = 0; i < n; i++) {
      int x;
      scanf("%d", &x);
      s.insert(x);

      if(s.size() == 1) {
        it = s.begin();
        med = *it;
        sum += *it;
        continue;
      }

      if(x < *it && !(s.size() & 1)) {
        advance(it, -1);
      }
      else if(x >= *it && (s.size() & 1)) {
        advance(it, 1);
      }

      sum += *it;
    }

    printf("%.2lf\n", ((double)sum / (double)n)+1e-3);
  }
  return 0;
}

