//Uva 10369.
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


typedef pair<int, int> ii;
typedef vector<int> vi;
typedef vector<ii> vii;

// Union-Find Disjoint Sets Library
// PS: This library code does not include the `union by rank' heuristic yet
// 1000 is just a rough number, adjustable by user
vi pset(1000), setSize(1000); int _numDisjointSets;
void initSet(int N) { setSize.assign(N, 1); _numDisjointSets = N; 
pset.assign(N, 0); for (int i = 0; i < N; i++) pset[i] = i; }
int findSet(int i) { return (pset[i] == i) ? i : (pset[i] = findSet(pset[i])); }
bool isSameSet(int i, int j) { return findSet(i) == findSet(j); }
void unionSet(int i, int j) { 
  if (!isSameSet(i, j)) { 
    _numDisjointSets--; 
    setSize[findSet(j)] += setSize[findSet(i)]; 
    pset[findSet(i)] = findSet(j); } }
int numDisjointSets() { return _numDisjointSets; }
int sizeOfSet(int i) { return setSize[findSet(i)]; }

vector<vii> AdjList;
vi taken;                                  // global boolean flag to avoid cycle
priority_queue<ii> pq;            // priority queue to help choose shorter edges
map<ii, int> mapp;
vii ou;
vector< pair<double, ii> > EdgeList;
int V, E, u, v, S, tc;
    
int mappear(ii point) {
    int idx;
	if (!mapp.count(point)) {
		idx = mapp.size();
		mapp[point]=idx;
	}
	return mapp[point];
}

double dist(ii p1, ii p2) { // Euclidean distance
  // hypot(dx, dy) returns sqrt(dx * dx + dy * dy)
    return hypot((double)p1.first - (double)p2.first, (double)p1.second - (double)p2.second); 
} // return double

void annadirVertices(){
    int u,v;
    double d;
    REP(i,0,V){
        REP(j,0,V){
            u=mappear(ou[i]);
            v=mappear(ou[j]);
            d= dist(ou[i],ou[j]);
            EdgeList.push_back(make_pair(d, ii(u, v)));
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    
//    freopen("ArcticNetwork.txt", "r", stdin);
//    freopen("ArcticNetwork_out.txt", "w", stdout);

    scanf("%d", &tc);
    REP(idCases,0,tc){
        ou.clear();
        mapp.clear();
        scanf("%d %d", &S, &V);
        REP(i,0,V){
            scanf("%d %d", &u, &v);   
            ou.push_back(ii(u, v));
        }
        EdgeList.clear();
        annadirVertices();
        
        sort(EdgeList.begin(), EdgeList.end());   // sort by edge weight in O(E log E)

        double mx = 0.0; initSet(V);             // all V are disjoint sets initially
        for (int i = 0; i < EdgeList.size(); i++) {
            if(_numDisjointSets<=S){
                break;
            }
            
            pair<double, ii> front = EdgeList[i];
            if (!isSameSet(front.second.first, front.second.second)) {    // if no cycle
                mx = max(mx, front.first);
                unionSet(front.second.first, front.second.second);
            } 
        }

        printf("%.2f\n", mx);
    }
}

