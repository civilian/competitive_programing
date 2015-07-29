//Uva 11231

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

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;


int main() {
    ios_base::sync_with_stdio(false);
    
//    freopen("Blackandwhitepainting.txt", "r", stdin);
//    freopen("Blackandwhitepainting_out.txt", "w", stdout);
    
    int n,m, k;
    long ans;
    while(scanf("%d %d %d", &n , &m, &k) && (n || m || k) ){
//        n-=7; m-=7;
            ans=0;
//        D(n);
//        D(m);
//        D(k);
        if(k){
            ans+= ceil((double)(m-7.0)/2.0)* ceil((double)(n-7.0)/2.0);
            ans+= ceil((double)(m-8.0)/2.0)* ceil((double)(n-8.0)/2.0);
//            m--;
//            ans+= ceil((double)m/2.0);
//            D("quito col");
//            D(ans);
//            D(m);
        }else {
            ans+= ceil((double)(m-8.0)/2.0)* ceil((double)(n-7.0)/2.0);
            ans+= ceil((double)(m-7.0)/2.0)* ceil((double)(n-8.0)/2.0);
        }
//        while(n && m){
//            n--; m--;
            
//        }
        printf("%d\n", ans);
    }
}

