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

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

int sieve_size;
bitset<1200> bs;
vector<int> primes;

void sieve(int upperbound) {
  sieve_size = upperbound+1;
  bs.set();
  bs[0] = bs[1] = 0;
  for(int i = 2; i <= sieve_size; i++) if(bs[i]) {
    for(int j = i*i; j <= sieve_size; j+=i)
      bs[j] = 0;
    primes.push_back(i);
  }
}

int memo[1122][15][188];

int solve(int x, int k, int p) {
  if(x == 0 && k == 0) return 1;
  if(k == 0 || x < 0 || p == 187) return 0;

  if(memo[x][k][p] != -1) {
    return memo[x][k][p];
  }

  memo[x][k][p] = solve(x, k, p+1) + solve(x-primes[p], k-1, p+1);

  return memo[x][k][p];
}


int main() {
  ios_base::sync_with_stdio(false);

  sieve(1120);
  memset(memo, -1, sizeof memo);

  int n, k;
  while(cin >> n >> k) {
    if(!(n|k)) break;
    cout << solve(n, k, 0) << endl;
  }
  return 0;
}

