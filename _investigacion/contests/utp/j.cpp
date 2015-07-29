#include <iostream>
#include <cmath>
#include <algorithm>

#define D(x) cerr << #x " = " << x << endl

using namespace std;

typedef unsigned long long ULL;

bool solve(ULL n) {
  ULL lo = 1ULL, hi = 4000000000ULL;

  while(lo < hi) {
    ULL mid = lo + (hi-lo) / 2ULL;
    ULL curr = (mid*mid + mid) / 2ULL;

    if(curr >= n) hi = mid;
    else lo = mid+1;
  }

  return n == ((lo*lo + lo) / 2);
}

int main() {
  ios_base::sync_with_stdio(false);

  ULL n;
  while(cin >> n) {
    if(n == 0)
      break;
    cout << (solve(n)? "SI" : "NO") << endl;
  }

  return 0;
}
