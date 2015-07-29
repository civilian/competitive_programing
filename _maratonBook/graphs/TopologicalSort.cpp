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

#define D(x) cout << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef pair<int, int> ii;      // In this chapter, we will frequently use these
typedef vector<ii> vii;      // three data type shortcuts. They may look cryptic
typedef vector<int> vi;   // but shortcuts are useful in competitive programming

// TopoSort Stuff
const int MAX_V=110;
int V, E, a, b, counter;
vector<vi> AdjList;
vi topoSort;
int indegree[MAX_V];
queue <int> pDeg;

// Sssp
int dist [MAX_V];
const int INF = 1000000000;
int s;

// TOPOSORT
void topologicalSort() {
	topoSort.clear();
	int to;
	int act;
	while (!pDeg.empty()) {
		act = pDeg.front();
		pDeg.pop();
		topoSort.push_back(act);
		for (int j = 0; j < AdjList[act].size(); j++) {
			to = AdjList[act][j];
			indegree[to]--;
			if (indegree[to] == 0) {
				pDeg.push(to);
			}
	   }
    }
}

void initTopologicalSort(int V) {
	while(!pDeg.empty()) pDeg.pop();
	
	for (int i = 0; i < V; i++) {
		if (indegree[i] == 0) {
			pDeg.push(i);
		}
	}

	topologicalSort();
	// Mostrar el toposort
}
// TOPOSORT END

// ss longest path
void SSLongestPathOnDag(int s) {
	memset(dist, -1, sizeof dist);
	dist[s] = 0;
	int e, v;
//	REP(i,0, topoSort.size()){
//        D(dist[i]);
//    }
	REP(i,0,topoSort.size()) {
        v=topoSort[i];
		REP(j,0,AdjList[v].size()){
            e=AdjList[v][j];
			if (dist[e] < dist[v] + 1) {// > para el mas corto
				dist[e] = dist[v] + 1;
            }
		}
	}
}

int main() {
    ios_base::sync_with_stdio(false);
    
//    freopen("LongestPaths.txt", "r", stdin);
//    freopen("LongestPaths_out.txt", "w", stdout);
    
    int n, s, caseId=0, mx,fin;//s start
    int p,q;//pair places p q visitar q after p
    while(scanf("%d", &n) && n){
        memset(indegree, 0, sizeof indegree);
        AdjList.assign(n, vi());
        scanf("%d", &s);
        s--;
        while(scanf("%d %d", &p, &q) && (p || q) ){
            p--; q--;
            AdjList[p].push_back(q);
            indegree[q]++;
        }
        initTopologicalSort(n);
//        REP(i,0, n){
//            D(topoSort[i]);
//        }
        SSLongestPathOnDag(s);
        mx=0;
        fin=s+1;
        REP(i,0, n){
//            D(i);
//            D(dist[i]);
            if(dist[i]>mx){
                mx=dist[i];
                fin=i+1;
            }
        }
        printf("Case %d: The longest path from %d has length %d, finishing at %d.\n\n",
                ++caseId, s+1, mx, fin);
    }
}

