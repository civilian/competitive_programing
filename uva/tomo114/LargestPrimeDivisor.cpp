//Uva 11466
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

#define D(x) cout << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef map<int, int> mii;

ll _sieve_size;
bitset<10000010> bs;   // 10^7 should be enough for most cases
vi primes;   // compact list of primes in form of vector<int>

void sieve(ll upperbound) {          // create list of primes in [0..upperbound]
  _sieve_size = upperbound + 1;                   // add 1 to include upperbound
  bs.set();                                                 // set all bits to 1
  bs[0] = bs[1] = 0;                                     // except index 0 and 1
  for (ll i = 2; i <= _sieve_size; i++) if (bs[i]) {
    // cross out multiples of i starting from i * i!
    for (ll j = i * i; j <= _sieve_size; j += i) bs[j] = 0;
    primes.push_back((int)i);  // also add this vector containing list of primes
} }                                           // call this method in main method

ll lpd(ll N) {   // remember: vi is vector of integers, ll is long long
    ll mx=0, val=N, prims=0;
    ll PF_idx = 0, PF = primes[PF_idx];     // using PF = 2, 3, 4, ..., is also ok
    
    while (N != 1 && (PF * PF <= N)) {   // stop at sqrt(N), but N can get smaller
        while (N % PF == 0) { N /= PF; }    // remove this PF
        if(N<val){
            mx=PF;
            prims++;
            val=N;
        }
        PF = primes[++PF_idx];                              // only consider primes!
    }
    if (N != 1) prims++;
    if( prims > 1 )
        return max(mx, N);
    else
        return -1;
}

int main() {
    ios_base::sync_with_stdio(false);
    
//    freopen("LargestPrimeDivisor.txt", "r", stdin);
//    freopen("LargestPrimeDivisor_out.txt", "w", stdout);
    sieve(10000000l);
    ll n=0, res;
    
    while( scanf("%lld", &n) && n){
        if(n < 0)
            n=n*-1;

        res=lpd(n);
//        D(res);
//        D(n);
        if( res == 0 )
            res=-1;
        printf("%lld\n", res);
    }
    return 0;
}

