//Uva 10067.
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
#define D1(x) cout << #x << " = " << (x);
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

struct state {
    int n[4];
    int pasos;
} s, meta;

bool  visitados [10][10][10][10];

bool compararConMeta(state c){
    return c.n[0]== meta.n[0] && c.n[1]== meta.n[1] &&
            c.n[2]== meta.n[2] && c.n[3]== meta.n[3];
}

void imp(state st){
    REP(i,0,4){
        cout<<st.n[i];
    }
    cout<<" pasos= "<<st.pasos<<endl;
}

int bfs(state s, int pasos){
  // as an example, we start from this source
  // see Figure 4.2
    s.pasos=pasos;
    queue<state> q; q.push(s);                                  // start from source
    state cur;
    
    while (!q.empty()) {
        cur = q.front(); q.pop();
//        imp(cur):
        if(compararConMeta(cur))
            return cur.pasos;
            
        REP(i,0,4){
            state u, d;
            d.pasos = u.pasos = cur.pasos+1;
            REP(idx,0,4){
                u.n[idx] = d.n[idx] = cur.n[idx];
            }
            
            if( u.n[i] == 9 )
                u.n[i] = 0;
            else 
                u.n[i]++;

            if( d.n[i] == 0 ) 
                d.n[i] = 9;
            else 
                d.n[i]--;
            
            if( ! visitados[u.n[0]][u.n[1]][u.n[2]][u.n[3]] ){
                visitados[u.n[0]][u.n[1]][u.n[2]][u.n[3]]=true;
                q.push(u);
            }
            
            if( ! visitados[d.n[0]][d.n[1]][d.n[2]][d.n[3]] ){
                visitados[d.n[0]][d.n[1]][d.n[2]][d.n[3]]=true;
                q.push(d);
            }
        }
    }
    return -1;
}



int main() {
    ios_base::sync_with_stdio(false);
    
//    freopen("PlayingwithWheels.txt", "r", stdin);
//    freopen("PlayingwithWheels_out.txt", "w", stdout);
    
    int tc, prob, a, b, c, d;
    scanf("%d", &tc);
    REP(idCases, 0, tc){
        scanf("%d %d %d %d", &s.n[0], &s.n[1], &s.n[2], &s.n[3]);
        scanf("%d %d %d %d", &meta.n[0], &meta.n[1], &meta.n[2], &meta.n[3]);
        memset(visitados, false , sizeof visitados);
        scanf("%d", &prob);
        REP(i,0,prob){
            scanf("%d %d %d %d", &a, &b, &c, &d);
            visitados[a][b][c][d]=true;
        }
        printf("%d\n", bfs(s, 0));
    }
    return 0;
}

