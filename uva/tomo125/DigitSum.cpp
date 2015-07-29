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

const int INF = (int)(1e9);
const ll INFLL = (int64)(1e18);
const double EPS = 1e-13;

ll memo[10][10];

int main() {
    ios_base::sync_with_stdio(false);
    freopen("DigitSum.txt", "r", stdin);
    freopen("DigitSum_out.txt", "w", stdout);

    
    REP(i,1,11){
        memo[i][0] += i;
    }
    
//    int n,m;
//    while(scanf("%d %d", &m, &n) && (n | m)){
//        
//    }
}

