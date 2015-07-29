#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int N;

  while (cin >> N) {
    if (N == 0)
      break;

    while (N / 10 > 0) {
      cout << N << ' ';
      int mult = 1;

      while (N > 0) {
        mult *= N % 10;
        N /= 10;
      }

      N = mult;
    }
    cout << N << '\n';
  }

  return 0;
}
