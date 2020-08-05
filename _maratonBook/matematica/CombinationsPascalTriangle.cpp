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

int64 C[60][60];

void init(int N) 
{ 
   memset(C,0,sizeof(C)); 
   for (int i=0; i<=N; i++) { 
       C[i][0] = 1; 
       for (int j=1; j<=i; j++) { 
           C[i][j] = C[i-1][j] + C[i-1][j-1]; 
       } 
       C[i][i] = 1;
   } 
}

int64 com(int n, int k) {
	if (k > n) {
		return 0;
	}
	return C[n][k];
}

int main() {
    ios_base::sync_with_stdio(false);
    
    int N = 50;
	init(N);
	int n,k; 
    cin>>n>>k;
	printf("%lld\n", com(n, k));

}

