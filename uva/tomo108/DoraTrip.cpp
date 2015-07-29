//Uva 10818.
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
int R,C;
const int MAX_V=27;
char mapa[MAX_V][MAX_V];
bool visitados[MAX_V][MAX_V];
int dc[]={1,0,0,-1};
int dr[]={0,1,-1,0};
char movs[]={'E', 'S', 'N', 'W'};

struct state{
    int r,c,metas;
    vector<char> movs;
} aMeta;


bool valid(int r,int c){
    if( -1 < r && r <= R && -1 < c && c <= C){
        return !visitados[r][c] && mapa[r][c] != '#' 
                && mapa[r][c] != 'X';
    }else{
        return false;
    }
}

void bfs(state s){
    queue<state> q; q.push(s);                              // start from source

    while (!q.empty()) {
        state u = q.front(); q.pop();
        visitados[u.r][u.c]=true;
        REP(i,0,4){
            state v;
            v.r=u.r + dr[i];
            v.c=u.c + dc[i];
            v.metas=u.metas;
            v.movs=u.movs;
            v.movs.push_back(movs[i]);
            
            if(valid( v.r , v.c )){
                if(mapa[v.r][v.c] == '*' ){
                    v.metas++;
//                    cout<<"r"<<v.r<<"c"<<v.c<<endl;

                    memset(visitados, false, sizeof visitados);
                    while(!q.empty()) q.pop();
                    
                    q.push(v);
                    
                    mapa[v.r][v.c] = ' ';
                    
                    break;
                }else if(mapa[v.r][v.c] == 'S' && aMeta.metas < v.metas){
                    aMeta=v;
                }
                
                q.push(v);
            }
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false);
    
    freopen("DoraTrip.txt", "r", stdin);
    freopen("DoraTrip_out.txt", "w", stdout);
    
    //Holly shit y las demas metas que?, sería llega 
    //a una meta guardo el best lo reinicio lo de los pasos
    //tengo best 0 metas no movs=stay home, luego llego a otro 
    //metas mas mas reinicio y lo corro hasta que no alcanzen más metas.
    string l;
    state s;
    while(cin>>R>>C && (R | C)){
//        scanf("%d %d", &R, &C);
        cin.get();
        memset(visitados, false, sizeof visitados);
//        memset(visitados, 0, sizeof visitados);//mapa

        
        REP(i,0,R){
            getline(cin,l);
            REP(j,0,C){
                mapa[i][j]=l[j];
                if(mapa[i][j] == 'S'){
                    s.r=i;
                    s.c=j;                
                }
                cout<<mapa[i][j];

            }
            cout<<endl;
        }
        
        s.metas=0;
        s.movs.clear();
        aMeta=s;
        bfs(s);
        cout<< aMeta.metas<<endl;
        if(aMeta.metas){
            REP(i,0,aMeta.movs.size()){
                cout << aMeta.movs[i];
            }
            cout << endl;
        }
        else
            cout << "Stay home!"<< endl;
        
        cin.get();
    }
}

