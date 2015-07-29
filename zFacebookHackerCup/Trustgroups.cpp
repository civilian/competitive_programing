#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <cstring>
#include <string>

#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <queue>

#define REP(i,a,b) for(int i = (a); i < (int)(b); i++)
#define D(x) cerr << #x " = " << (x) << endl;

#define DFS_WHITE -1

using namespace std;

#define mp make_pair

typedef long long int64;

map<string, int> names;
vector< vector<int> > G;
vector<int> dfs_num, dfs_low, S, visited;
int dfsNumberCounter, numSCC;

void tarjanSCC(int u) {
    dfs_low[u] = dfs_num[u] = dfsNumberCounter++;
    S.push_back(u);
    visited[u] = 1;
    for(int j = 0; j < (int)G[u].size(); j++) {
        int v = G[u][j];
        if(dfs_num[v] == DFS_WHITE)
            tarjanSCC(v);
        if(visited[v])
            dfs_low[u] = min(dfs_low[u], dfs_low[v]);
    }
    
    if(dfs_low[u] == dfs_num[u]) {
        ++numSCC;
        while(1) {
            int v = S.back(); S.pop_back(); visited[v] = 0;
            if(u == v) break;
        }
    }
}

int main(){
	ios_base::sync_with_stdio(false);
//    "Trustgroups"
    int P,T,u,v;
    string name, surname;
    while(cin >> P >> T && (P|T)){
        cin.get();
        names.clear();
        
        REP(i, 0, P) {
            string s; getline(cin, s);
            names[s] = i;
        }
        
        G.clear();
        G.resize(P);
        
        REP(i, 0, T) {
            string a, b;
            getline(cin, a); getline(cin, b);
            
            int x = names[a], y = names[b];
            G[x].push_back(y);
        }
        
        dfs_num.assign(P, DFS_WHITE); dfs_low.assign(P, 0); visited.assign(P, 0);
        S.clear();
        dfsNumberCounter = numSCC = 0;
        
        for(int i = 0; i < P; i++)
            if(dfs_num[i] == DFS_WHITE)
                tarjanSCC(i);
        cout << numSCC << endl;
    }
}

