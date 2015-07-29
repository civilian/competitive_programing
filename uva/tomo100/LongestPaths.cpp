//Uva 10000.
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

#define INF = 1000000000
#define D(x) cout << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()



using namespace std;

int V, E, u, v, w, AdjMatrix[110][110];
int s;

int main() {
    ios_base::sync_with_stdio(false);
    
//    freopen("LongestPaths.txt", "r", stdin);
//    freopen("LongestPaths_out.txt", "w", stdout);
    
    int V, s, caseId=0, mx,fin;//s start
    int p,q;//pair places p q visitar q after p
    while(scanf("%d", &V) && V){
        
        REP(i,0,V){
            REP(j,0,V){
              AdjMatrix[i][j] = -1000000000;
            }
            AdjMatrix[i][i] = 0;
        }

        
        scanf("%d", &s);
        s--;
        while(scanf("%d %d", &p, &q) && (p || q) ){
            p--; q--;
            AdjMatrix[p][q]=1;
        };
        
  
        REP(k,0,V)
            REP(i,0,V)
                REP(j,0,V)
                    AdjMatrix[i][j] = max(AdjMatrix[i][j], 
                                    AdjMatrix[i][k] + AdjMatrix[k][j]); 
        
        mx=0;
        fin=s;
    
        REP(i,0, V){
//            D(i);
//            D(dist[i]);
            if(AdjMatrix[s][i]>mx){
                mx=AdjMatrix[s][i];
                fin=i;
            }
        }
        printf("Case %d: The longest path from %d has length %d, finishing at %d.\n\n",
                ++caseId, s+1, mx, fin+1);
    }
}

