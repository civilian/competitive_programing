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

const int maxn = 100010;

int n, e;
vector< vector<int> > G;
bitset<maxn> bridges[maxn];

#define DFS_WHITE -1

int dfsNumberCounter, dfsRoot, rootChildren;
vector<int> dfs_num, dfs_low, dfs_parent;
vector<bool> articulation_vertex;

void articulationPointAndBridge(int u) {
    dfs_low[u] = dfs_num[u] = dfsNumberCounter++;
    for(int j = 0; j < (int)G[u].size(); j++) {
        int v = G[u][j];
        
        if(dfs_num[v] == DFS_WHITE) {
            dfs_parent[v] = u;
            if(u == dfsRoot) rootChildren++;
            
            articulationPointAndBridge(v);
            
            if(dfs_low[v] >= dfs_num[u])
                articulation_vertex[u] = 1;
            if(dfs_low[v] > dfs_num[u])
                bridges[v][u] = bridges[u][v] = 1;
            dfs_low[u] = min(dfs_low[u], dfs_low[v]);
        }
        else if(v != dfs_parent[u])
            dfs_low[u] = min(dfs_low[u], dfs_num[v]);
    }
}

int main() {
//#ifndef ONLINE_JUDGE
    freopen("in.txt", "r", stdin);
    freopen("out.txt", "w", stdout);
//#else
//    ios_base::sync_with_stdio(false);
//#endif

    while(cin >> n >> e) {
        G.clear();
        G.resize(n);
        REP(i, 0, n) bridges[i].reset();
        
        REP(i, 0, e) {
            int a, b;
            cin >> a >> b; a--, b--;
            G[a].push_back(b);
            G[b].push_back(a);
        }

        dfsNumberCounter = 0;
        dfs_num.assign(n, DFS_WHITE); dfs_low.assign(n, 0);
        dfs_parent.assign(n, 0); articulation_vertex.assign(n, false);
        
        REP(i, 0, n) {
            if(dfs_num[i] == DFS_WHITE) {
                dfsRoot = i; rootChildren = 0;
                articulationPointAndBridge(i);
                articulation_vertex[dfsRoot] = (rootChildren > 1);
            }
        }
        
        REP(i,0,n)
            if(articulation_vertex[i])
                cout << "art point: " << (i+1) << endl;
        int Q;
        cin >> Q;
        
        REP(i, 0, Q) {
            int opt; cin >> opt;
            if(opt == 1) { // bridge
                int a, b, g1, g2;
                cin >> a >> b >> g1 >> g2;
                a--, b--, g1--, g2--;
                if(bridges[g1][g2]) cout << "ne" << endl;
                else cout << "da" << endl;
            }
            else { // articulation point
                int a, b, c;
                cin >> a >> b >> c;
                a--, b--, c--;
                if(articulation_vertex[c]) cout << "ne" << endl;
                else cout << "da" << endl;
            }
        }
    }
}


















