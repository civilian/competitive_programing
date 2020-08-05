#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <cstring>

#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <queue>

#define REP(i,a,b) for(int i = (a); i < (int)(b); i++)
#define D(x) cerr << #x " = " << (x) << endl;

using namespace std;

#define mp make_pair

typedef long long int64;

int n, m;
map<string, int> names;

const int maxn = 410;

struct Edge {
    int cost, from, to;
    Edge(int _f = 0, int _t = 0, int _c = 0) {
        cost = _c;
        from = _f;
        to = _t;
    }
    
    bool operator<(const Edge& other) const {
        return cost < other.cost;
    }
};

vector<Edge> G;
int pset[maxn], rank[maxn], size[maxn];

void init(int n) {
    REP(i,0, n) {
        pset[i] = i;
        rank[i] = 0, size[i] = 1;
    }
}

int find(int x) {
    return (x == pset[x])? x : pset[x] = find(pset[x]);
}

void unite(int x, int y) {
    int px = find(x);
    int py = find(y);
    
    if(px == py) return;
    
    if(rank[px] < rank[py]) {
        pset[px] = py;
        size[py] += size[px];
    }
    else {
        pset[py] = pset[px];
        size[px] += size[py];
        if(rank[px] < rank[py])
            rank[px]++;
    }
}

bool same_set(int x, int y) {
    return find(x) == find(y);
}

int64 kruskal(int n) {
    sort(G.begin(), G.end());
    
    int done = 0;
    int64 ans = 0;
    
    for(int i = 0; i < G.size() && done < n; i++) {
        if(!same_set(G[i].from, G[i].to)) {
            unite(G[i].from, G[i].to);
            ans += G[i].cost;
            done++;
        }
    }
    
    if(done != n-1) return -1;
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
	
    while(cin >> n >> m) {
        if((n|m) == 0) break;

        names.clear();
        REP(i, 0, n) {
            string s; cin >> s;
            names[s] = i;
        }

        G.clear();

        REP(i, 0, m) {
            string a, b; cin >> a >> b;
            int c; cin >> c;

            int x = names[a], y = names[b];
            G.push_back( Edge(x, y, c) );
		}
	
		string start; cin >> start;
		init(n);
		int64 ans = kruskal(n);
		
		if(ans < 0) cout << "Impossible" << endl;
		else cout << ans << endl;
	}
}
