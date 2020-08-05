#ifdef LOCAL
       #include "../../bits_stdc++.h"
#else
     #include <bits/stdc++.h>
#endif

#define D(x) cout << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

int p[50000], q[50000];
int s, a, f;

int main() {
#ifdef LOCAL
    freopen("LunchinGridCity.in.txt", "r", stdin);
    freopen("LunchinGridCity.out.txt", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
#endif
  int c;
  scanf("%d", &c);
  while (c--) {
    scanf("%d %d %d", &s, &a, &f);
    for (int i=0;i<f;i++) {
     scanf("%d %d", &(p[i]), &(q[i]));
    }
    sort(p, p+f);
    sort(q, q+f);

    if (f % 2 == 0)
     printf("(Street: %d, Avenue: %d)\n", p[(f-1)/2], q[(f-1)/2]);
    else
     printf("(Street: %d, Avenue: %d)\n", p[f/2], q[f/2]);
  }
}
