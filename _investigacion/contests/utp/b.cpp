#include <cstdio>
#include <iostream>

using namespace std;

long long mod_pow(long long a, long long b, long long c = int(1e9)) {
  long long ans = 1LL;

  while(b > 0) {
    if(b & 1) ans = (ans * a) % c;
    a = (a * a) % c;
    b >>= 1;
  }

  return ans;
}

int main() {
  ios_base::sync_with_stdio(false);
  int t;
  cin >> t;
  while(t--) {
    long long a, b;
    cin >> a >> b;
    cout << mod_pow(a,b) << endl;
  }
  return 0;
}
