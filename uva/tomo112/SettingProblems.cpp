//Uva 11269.
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
int todosHechos, problems;
int memo[(1<<5)+7][(1<<5)+7][3];//20 20 2
int tiempos[27][3];//20 2


int solve(int f,int s,int t){
	if(f==todosHechos && s== todosHechos)
		return 0;
	if(memo[f][s][t]!= -1) 
        return memo[f][s][t];
	
	int fp, sp;
	REP(i,0,problems){
		if(t){
			if(( f & 1<<i )!= 0) continue;
			else fp= f | 1<<i;
		}else{
			if(( s & 1<<i )!= 0) continue;
			else sp= s | 1<<i;	
		}

		memo[f][s][t]=min(memo[f][s][t],
			tiempos[i][t]+solve(fp,sp,(t+1)%2 ) );
	}
	
	return memo[f][s][t];
}

int main() {
    ios_base::sync_with_stdio(false);
    
    freopen("SettingProblems.txt", "r", stdin);
    freopen("SettingProblems_out.txt", "w", stdout);
    
    while(scanf("%d", &problems) == 1){
        REP(i,0,problems)
            scanf("%d",&tiempos[i][0]);

        REP(i,0,problems)
            scanf("%d",&tiempos[i][1]);
            
        memset(memo, -1, sizeof memo);
        
        todosHechos=(1<<problems+1)-1;//no se si son muchos 1
        
        printf("%d\n", solve(0,0,0));
    }
}

