// uva 11799
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
#ifdef LOCAL
    freopen("horror_dash.in.txt", "r", stdin);
    freopen("horror_dash.out.txt", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
#endif

    int T;
    cin >> T;
    T += 1;
    for(int caso = 1; caso < T; caso++) {
    	int fastest = -1, n, c_i;
    	cin >> n;
    	for (int i = 0; i < n; i++){
    		cin >> c_i;
    		fastest = max(fastest, c_i);
    	}
    	printf("Case %d: %d\n", caso, fastest);
    }
  
}
