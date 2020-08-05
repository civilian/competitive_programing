//Uva 321
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

//#define D(x) cout << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

typedef pair<int, int> ii;      // In this chapter, we will frequently use these
typedef vector<ii> vii;      // three data type shortcuts. They may look cryptic
typedef vector<int> vi;   // but shortcuts are useful in competitive programming

struct move{
    int room;
    char accion;//m o f -move on off
};

struct estado{
    int pos;
    int luces;
    vector <move> movs;
} s, meta, u;

int V, E, mLuces, tc;
vector<vi> grafo;
vector<vi> suiches;
vi p;


int visitados [10 +7][(1<<10)+11];

void imprimirMovs( vector <move> movs){
    printf("The problem can be solved in %d steps:\n", movs.size());
    REP(i,0,u.movs.size()){
        if(u.movs[i].accion== 'o')
            printf("- Switch on light in room %d.\n", u.movs[i].room);
        else if(u.movs[i].accion== 'f')
            printf("- Switch off light in room %d.\n", u.movs[i].room);
        else if(u.movs[i].accion== 'm')
            printf("- Move to room %d.\n", u.movs[i].room);
    }
}

bool llegue;

void bfs(){
  // as an example, we start from this source
  // see Figure 4.2
  llegue=false;
  s.luces= 1<<1;
  s.pos=1;
  s.movs.clear();
  memset(visitados, -1, sizeof visitados);
  
  mLuces= 1<<V;
  // BFS routine
  // inside int main() -- we do not use recursion, thus we do not need to create separate function!
//  D(mLuces);
  queue<estado> q; q.push(s);                                  // start from source
    while (!q.empty()) {
        u = q.front(); q.pop();
//        D(u.luces);
//        D(u.pos);
//        imprimirMovs(u.movs);
        
        if(u.pos == V && u.luces == mLuces){
            llegue=true;
            break;
        }
        if(visitados[u.pos][u.luces]==1){
            continue;
        }
    
        visitados[u.pos][u.luces]=1;
  //      D(u.pos);
        for (int j = 0; j < (int)suiches[u.pos].size(); j++) {
            int luzDe=suiches[u.pos][j];
    //        D(luzDe);
            if(luzDe== u.pos)
                continue;
            if(u.luces & (1<<luzDe)){
                estado n;
                n.luces = u.luces & ~(1<<luzDe);
                n.pos = u.pos;
                    
                if(visitados[n.pos][n.luces]==1)
                    continue;
                
                n.movs = u.movs;
                
                move movi;
                movi.accion='f';
                movi.room=luzDe;
                    
                n.movs.push_back(movi);
                q.push(n);
            } else {
                estado n;
                n.luces = u.luces | (1<<luzDe);
                n.pos = u.pos;
                
                if(visitados[n.pos][n.luces]==1)
                    continue;
                    
                n.movs = u.movs;
                    
                move movi;
                movi.accion='o';
                movi.room=luzDe;
                
                n.movs.push_back(movi);
                q.push(n);
            }
        }
        
        for (int j = 0; j < (int)grafo[u.pos].size(); j++) {
            int v = grafo[u.pos][j];                           // for each neighbors of u
            if(u.luces & (1 << v)){
                    estado n;
                    n.luces = u.luces;
                    n.pos = v;
                    
                    if(visitados[n.pos][n.luces]==1)
                        continue;
                
                    n.movs = u.movs;
                
                    move movi;
                    movi.accion='m';
                    movi.room=v;
                
                    n.movs.push_back(movi);
                    q.push(n);
                }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    
//    freopen("TheNewVilla.txt", "r", stdin);
//    freopen("TheNewVilla_out.txt", "w", stdout);
    
    int s, from , to , en, apaga;//r V, d E
    tc=1;
    while(scanf("%d %d %d", &V, &E, &s) && (V || E || s)){
        grafo.assign(V+1, vi());
        suiches.assign(V+1, vi());
        for(int i=0; i< E; i++){
            scanf("%d %d", &from, &to);
            grafo[from].push_back(to);
            grafo[to].push_back(from);
        }
        for(int i=0; i< s; i++){
            scanf("%d %d", &en, &apaga);
            suiches[en].push_back(apaga);
        }
        bfs();
        if(llegue){
            printf("Villa #%d\n", tc);
            imprimirMovs(u.movs);
            printf("\n");
        } else {
            printf("Villa #%d\n", tc);    
            printf("The problem cannot be solved.\n");
            printf("\n");
        }
        tc++;
    }
    
    return 0;
}
