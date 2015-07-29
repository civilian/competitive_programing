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
    freopen("e1_2_3_6.in.txt", "r", stdin);
    freopen("e1_2_3_6.out.txt", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
#endif
  int p[10], N = 10;
  for (int i = 0; i < N; i++) p[i] = i;
  do{
	  for(int i = 0; i < N; i++) printf("%d ", p[i]);
	  printf("\n");
  } while(next_permutation(p, p))
}
