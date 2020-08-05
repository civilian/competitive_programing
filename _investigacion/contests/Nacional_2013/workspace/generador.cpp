#include <iostream>

using namespace std;

int main() {
  freopen("big_f.in", "w", stdout);
  cout << 1024 << ' ' << 9223372036854775807LL << '\n';
  for (int i = 1024; i > 0; i--)
    cout << i << ' ';
  return 0;
}
