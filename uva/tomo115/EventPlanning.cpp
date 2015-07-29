//Uva 11559
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

int main() {
#ifdef LOCAL
    freopen("EventPlanning.in.txt", "r", stdin);
    freopen("EventPlanning.out.txt", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
#endif
  
  int N, B, H, W;
  while(cin>>N>>B>>H>>W){
    int minVal = INF;
    for(int i=0; i<H; i++){
      int p;
      cin >> p;
      int costoHotel = p*N;
      for(int j=0; j < W; j++){
        int a;
        cin >> a;
        if( B >= costoHotel && a >= N ){
          minVal = min(minVal , costoHotel);  
        }
      }
    }
    
    if(minVal < INF){
      cout << minVal << endl;
    }else {
      cout << "stay home" << endl;      
    }
  }
}
