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
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

const int maxn = (int)1e5;

int L, P, B;
int64 t[4*maxn], powers[maxn];

// RMQ Build
void build (int a[], int v, int tl, int tr) {
	if (tl == tr)
		t[v] = a[tl];
	else {
		int tm = (tl + tr) / 2;
		build (a, v*2, tl, tm);
		build (a, v*2+1, tm+1, tr);
		t[v] = t[v*2] + t[v*2+1];
	}
}

void calculate_powers() {
    powers[0] = 1LL;
    for(int i = 1; i <= L; i++) {
        powers[i] = (powers[i-1] * B) % P;
    }
}

void edit(int v, int tl, int tr, int pos, int new_val) { // Update
    if(tl == tr) {
        t[v] = new_val;
    }
    else {
        int tm = (tl + tr) >> 1; // divided by 2

        if(pos <= tm)
            edit(2*v, tl, tm, pos, new_val);
        else
            edit(2*v + 1, tm + 1, tr, pos, new_val);
        
        t[v] = ((t[2*v] * powers[tr-tm]) % P);
        t[v] += t[2*v + 1];
        t[v] %= P;
    }
}

int64 hash(int v, int tl, int tr, int l, int r, int offset) {
    if(l > tr || r < tl)
        return 0;
    
    if(tl >= l && tr <= r)
        return (t[v] * powers[offset]) % P;
        
    int tm = (tl + tr) >> 1;
    
    int64 left_hash = hash(2*v, tl, tm, l, r, r - tm);
    int64 right_hash = hash(2*v + 1, tm+1, tr, l, r, r - tr);
    
    return (left_hash + right_hash) % P;
}

char cmd[5];

int main() {
#ifdef LOCAL
    freopen("j.in", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif

    int n;
    while(scanf("%d%d%d%d", &B, &P, &L, &n)) {
        if((B|P|L|n) == 0)
            break;
        
        memset(t, 0, sizeof(t));
        calculate_powers();

        REP(i, 0, n) {
            scanf("%s", cmd);
            
            if(cmd[0] == 'E') {
                int pos, val;
                scanf("%d%d", &pos, &val);
                
                edit(1, 1, L, pos, val);
            }
            else {
                int r, l;
                scanf("%d%d", &l, &r);
                
                printf("%lld\n", hash(1, 1, L, l, r, 0));
            }
        }
        puts("-");
    }
}

