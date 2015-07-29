#include <bits/stdc++.h>

using namespace std;

bool digit[10];

bool check() {
  for (int i = 0; i < 10; i++)
    if (!digit[i])
      return false;
  return true;
}

void add(long long x) {
  while (x > 0) {
    digit[x % 10] = 1;
    x /= 10;
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  long long n;
  while (cin >> n) {
    long long sum = 0;
    memset(digit, 0, sizeof(digit));
    for (int k = 1; ; k++) {
      sum += n;
      add(sum);
      
      if (check()) {
        cout << k << '\n';
        break;
      }
    }
  }

  return 0;
}
