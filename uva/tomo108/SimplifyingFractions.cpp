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
    
    int tc;
    scanf("%d", &tc);
    int num,denom,g;
    while(tc--){
        scanf("%d", &num); 
        scanf("%d", &denom); 
        g=gcd(num,denom);
        num=num/g;
        denom=denom/g;
    }
    
    //freopen("d_in.txt", "r", stdin);
    //freopen("d_out.txt", "w", stdout);
}

