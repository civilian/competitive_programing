NO SE PUEDE CON FAST FIBONNACCI
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

typedef long long ll;
ll MOD;

#define MAX_N 2                                  // increase this if needed
struct Matrix { ll mat[MAX_N][MAX_N]; };     // to let us return a 2D array

Matrix matMul(Matrix a, Matrix b) {              // O(n^3), but O(1) as n=2
  Matrix ans; int i, j, k;
  for (i = 0; i < MAX_N; i++)
    for (j = 0; j < MAX_N; j++)
      for (ans.mat[i][j] = k = 0; k < MAX_N; k++) {
        ans.mat[i][j] += (a.mat[i][k] % MOD) * (b.mat[k][j] % MOD);
        ans.mat[i][j] %= MOD;             // modulo arithmetic is used here
      }
  return ans;
}

Matrix matPow(Matrix base, int p) {    // O(n^3 log p), but O(log p) as n=2
  Matrix ans; int i, j;
  for (i = 0; i < MAX_N; i++)
    for (j = 0; j < MAX_N; j++)
      ans.mat[i][j] = (i == j);                  // prepare identity matrix
  while (p) {       // iterative version of Divide & Conquer exponentiation
    if (p & 1)                    // check if p is odd (the last bit is on)
      ans = matMul(ans, base);                                // update ans
    base = matMul(base, base);                           // square the base
    p >>= 1;                                               // divide p by 2
  }
  return ans;
}

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;


int main() {
    ios_base::sync_with_stdio(false);
    
    freopen("YetanotherNumberSequence.txt", "r", stdin);
    freopen("YetanotherNumberSequence_out.txt", "w", stdout);

    int tc, a, b, n, m;// m son cifras 1 a 4
	int modulo;
	int dp, rap;
	
	scanf("%d", &tc);
	Matrix ans;
	for (int idCases = 0; idCases < tc; idCases++) {
        scanf("%d %d %d %d", &a, &b, &n, &m);
		MOD = (ll) pow((double)10, m);
//		D(MOD);
//		D(a);
//        D(b);
//        D(n);
//        D(m);
        ans.mat[0][0]=a+b;
        ans.mat[0][1]=b;
        ans.mat[1][0]=b;
        ans.mat[1][1]=a;
        
//        cout<<ans.mat[0][0];
//        cout<<ans.mat[0][0];
//        cout<<endl;
//        cout<<ans.mat[1][0];
//        cout<<ans.mat[1][1]<<endl;
        
		ans= matPow(ans,n);
		printf("%lld\n", ans.mat[1][0] );
	}
}
