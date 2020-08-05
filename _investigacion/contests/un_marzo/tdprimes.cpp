#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <ctime>
#include <cctype>
#include <cassert>
#include <iostream>
#include <sstream>
#include <iomanip>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <deque>
#include <list>
#include <set>
#include <map>
#include <bitset>
#include <algorithm>
#include <numeric>
#include <complex>

#define D(x) cerr << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

//const int INF = (int)(1e9);
//const int64 INFLL = (int64)(1e18);
//const double EPS = 1e-13;

//long long sieve_size;
//bitset<100000010> sieve;
#define max_n 100000000
bool sieve[max_n+2];

void sieveEratosthenes() {
  int root = 10000;
  for(int i = 3; i < root; i += 2) {
    if(!sieve[i])
      for(int j = i*i, k = (i<<1); j < max_n; j += k)
        sieve[j] = true;
  }
  printf("2\n");
  for(int i = 3, j = 1, k = 1; i < max_n; i+=2) {
    if(!sieve[i]) {
      j++;
      if(j-k == 100) {
        printf("%d\n", i);
        k = j;
      }
    }
  }
}

/*
void sieve(long long upperbound) {
  sieve_size = upperbound+1;
  bs.set();
  bs[0] = bs[1] = 0;
  for(long long i = 2; i <= sieve_size; i++) if(bs[i]) {
    for(long long j = i*i; j <= sieve_size; j+=i)
      bs[j] = 0;
    primes.push_back(i);
  }
}

void sieveOfAtkin(){
  int N = sqrt(max_n);
  memset(sieve, 0, sizeof sieve);
  for (int x = 1; x <= N; x++){
    for (int y = 1; y <= N; y++){
      int n = (4*x*x)+(y*y);
      if (n <= max_n && (n % 12 == 1 || n % 12 == 5))
        sieve[n] ^= true;
      n = (3*x*x)+(y*y);
      if (n <= max_n && n % 12 == 7)
        sieve[n] ^= true;
      n = (3*x*x)-(y*y);
      if (x > y && n <= max_n && n % 12 == 11)
        sieve[n] ^= true;
    }
  }
  sieve[2] = sieve[3] = true;
  primes.push_back(2);
  primes.push_back(3);
  int a;
  for (a = 5; a <= N; a+=2){
    if (sieve[a]){
      for (int i = a*a; i < max_n; i += a*a)
        sieve[i] = false;
      primes.push_back(a);
    }
  }
  for (; a < max_n; a+=2) if (sieve[a])
    primes.push_back(a);
}
*/
int main() {
  sieveEratosthenes();

  return 0;
}

