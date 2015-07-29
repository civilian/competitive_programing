//Uva 914.
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


// first part

void sieve(ll upperbound) {          // create list of primes in [0..upperbound]
  _sieve_size = upperbound + 1;                   // add 1 to include upperbound
  bs.set();                                                 // set all bits to 1
  bs[0] = bs[1] = 0;                                     // except index 0 and 1
  for (ll i = 2; i <= _sieve_size; i++) if (bs[i]) {
    // cross out multiples of i starting from i * i!
    for (ll j = i * i; j <= _sieve_size; j += i) bs[j] = 0;
    primes.push_back((int)i);  // also add this vector containing list of primes
} }                                           // call this method in main method


int main() {
    ios_base::sync_with_stdio(false);
    
//    freopen("JumpingChampion.txt", "r", stdin);
//    freopen("JumpingChampion_out.txt", "w", stdout);
    
    sieve(1000000 + 30);
    int frec[114 + 10];
    int tc,low,high, mx, cant, desde, hasta;
    scanf("%d", &tc);

//    REP(i,0,primes.size()){
//        printf("%d ", primes[i]);
//    }
    REP(idCases,0,tc){
        memset(frec, 0, sizeof frec);
        scanf("%d %d",&low, &high);
        
        if(low == high){
            printf("No jumping champion\n");
            continue;
        }
//        int(lower_bound(vect.begin(), vect.end(), valor) - vect.begin());
        desde=int(lower_bound(primes.begin(), primes.end(), low)- primes.begin());
        hasta=int(lower_bound(primes.begin(), primes.end(), high)- primes.begin());
        if(hasta > int(primes.end()- primes.begin())){
            hasta= int(primes.end()- primes.begin());
        }
        if(primes[hasta]> high)
            hasta--;
//        D(desde);
//        D(hasta);
        REP(i,desde,hasta){
            frec[primes[i+1]-primes[i]]++;
        }
        
        mx=-1; cant=0;
        REP(i,0,min(114 + 7, high)){
            if( cant < frec[i] ){
                mx = i;
                cant = frec[i];
            }else if( cant == frec[i] ){
                mx=-1;
            }
        }
        if(mx == -1){
            printf("No jumping champion\n");
        } else{
            printf("The jumping champion is %d\n", mx);
        }
    }
    return 0;
}
