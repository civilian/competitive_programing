//Uva 543 
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
bitset<1000007> bs;   // 10^7 should be enough for most cases
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
//    freopen("GoldbachsConjecture.txt", "r", stdin);
//    freopen("GoldbachsConjecture_out.txt", "w", stdout);
    
    sieve((1000000 +7));
    int n=0,k, s, posS;
    bool can=false;
    while(scanf("%d", &n) && n){//1050
//    while(++n < 1000001){
        can=false;
        REP(i,1,primes.size()){
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
            printf("%d = %d + %d\n", n, k, s);
        }else{
            printf("Goldbach's conjecture is wrong.\n", n);
        }
    }
}

