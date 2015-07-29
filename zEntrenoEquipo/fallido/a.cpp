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

#define D(x) cerr << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

const int maxn = 21;
const int maxm = 2012;

struct node {
    int x, y, z, color;
    node(int _x = 0, int _y = 0, int _z = 0, int _c = 0) {
        x = _x; y = _y; z = _z;
        colort = _c;
    }

    bool valid() {
        if(x < 0 || x > n) return false;
        if(y < 0 || y > n) return false;
        if(z < 0 || z > n) return false;

        return (x+y+z) == n;
    }
};

int n;
map<node, int> mapping;
vector< vector<int> > G;

int getindex(int x, int y, int z) {
    return (x+1) * 100 + (y+1) * 10 + z + 1;
}

void addx(int u, int x, int y, int z) {
    // x-1
    node next = node(x-1, y, z+1);
    int v;
    if(next.valid()) {
        v = mapping[next];
        G[u].push_back(v);
        G[v].push_back(u);
    }

    next = node(x-1, y+1, z);
    if(next.valid()) {
        v = mapping[next];
        G[u].push_back(v);
        G[v].push_back(u);
    }
}

void addy(int u, int x, int y, int z) {
    // y-1
    next = node(x, y-1, z+1);
    if(next.valid()) {
        v = mapping[next];
        G[u].push_back(v);
        G[v].push_back(u);
    }

    next = node(x, y+1, z-1);
    if(next.valid()) {
        v = mapping[next];
        G[u].push_back(v);
        G[v].push_back(u);
    }
}

void addz(int u, int x, int y, int z) {
    // z
    next = node(x+1, y-1, z);
    if(next.valid()) {
        v = mapping[next];
        G[u].push_back(v);
        G[v].push_back(u);
    }

    next = node(x+1, y, z-1);
    if(next.valid()) {
        v = mapping[next];
        G[u].push_back(v);
        G[v].push_back(u);
    }
}

void addnodes(int u, int x, int y, int z) {
    addx(u, x, y, z);
    addy(u, x, y, z);
    addz(u, x, y, z);
}

void create() {
    int cnt = 0;
    for(int x = 0; x <= n; x++) {
        for(int y = 0; y <= n; y++) {
            for(int z = 0; z <= n; z++) {
                node n(x,y,z);
                mapping[n] = cnt++;
            }
        }
    }

    for(int x = 0; x <= n; x++) {
        for(int y = 0; y <= n; y++) {
            for(int z = 0; z <= n; z++) {
                node current(x,y,z);
                int u = mapping[current];
                addnodes(u, x, y, z);
            }
        }
    }

}

int main() {
}

