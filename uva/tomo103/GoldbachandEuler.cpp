//Uva 10311. 1 no es primo porque divisible 2 positivos
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

const int INF = (int)(1e9);
const double EPS = 1e-13;


ll _sieve_size;
bitset<50000010> bs;   // 10^7 should be enough for most cases
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
    
    freopen("GoldbachandEuler.txt", "r", stdin);
    freopen("GoldbachandEuler_out.txt", "w", stdout);
    
    sieve((10000000 +7));
    int n,k, s, posS;
    bool can=false;
    while(scanf("%d", &n) == 1){//1050
//    while(++n < 100000000){
        can=false;
        REP(i,0,primes.size()){
            k=primes[i];
            s=n-k;
            if(k>s){
                can=false;
                break;
            }
            posS=int(lower_bound(primes.begin(), primes.end(), s) - primes.begin());
            if(primes[posS]==s){
                can=true;
                break;
            }
        }
        //tener en cuenta que puede ser mas grande
        if(can){
            printf("%d is the sum of %d and %d.\n", n, k, s);
        }else{
            printf("%d is not the sum of two primes!.\n", n);
        }
    }
}

