#include <bits/stdc++.h>

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
    cin.tie(NULL);

    int N = 3, p[N];
    for (int i = 0; i < N; i++) p[i] = i;
    for (int i = 0; i < (1 << N); i++){
    	for(int j = 0; j < N; j++){
    		if(((1 << j) & i) != 0)
    			printf("%d ", p[j]);
    	}
    	printf("\n");
    }
  
}
