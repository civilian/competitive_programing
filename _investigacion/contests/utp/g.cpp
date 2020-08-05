#include <cstdio>
#include <iostream>
#include <vector>
#include <algorithm>
#include <bitset>

using namespace std;

long long sieve_size;
bitset<1000010> bs;
vector<int> primes;

void sieve(long long upp) {
  sieve_size = upp+1;
  bs.set();
  bs[0] = bs[1] = 0;

  for(long long i = 2; i <= sieve_size; i++) if(bs[i]) {
    for(long long j = i*i; j <= sieve_size; j += i)
      bs[j] = 0;
    primes.push_back(i);
  }
}

int main() {
  sieve(1000000);

  int a, b;

  while(scanf("%d%d", &a, &b)) {
    if(!(a|b)) break;
    int i = int(lower_bound(primes.begin(), primes.end(), a) - primes.begin());
    int j = int(upper_bound(primes.begin(), primes.end(), b) - primes.begin());

    printf("%d\n", j-i);
  }

  return 0;
}
