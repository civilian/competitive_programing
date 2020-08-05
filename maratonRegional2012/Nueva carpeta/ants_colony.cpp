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

const int maxn = 100010;
const int maxlgn = 20;

int n, P[maxn][maxlgn], L[maxn], T[maxn], cost[maxn];
int64 sumRoot[maxn];

int calc_level(int u) {
    if(L[u] > -1) return L[u];

    return L[u] = calc_level(T[u]) + 1;
}

int64 calc_sum(int u) {
    if(sumRoot[u] > -1) return sumRoot[u];

    return sumRoot[u] = cost[u] + calc_sum(T[u]);
}

void preprocess() {
    for(int i = 0; i < n; i++)
        for(int j = 0; (1 << j) < n; j++)
            P[i][j] = -1;

    //the first ancestor of every node i is T[i]
    for(int i = 0; i < n; i++)
        P[i][0] = T[i];

    for(int j = 1; (1 << j) < n; j++)
        for(int i = 0; i < n; i++)
            if(P[i][j-1] != -1)
                P[i][j] = P[P[i][j-1]][j-1];
}

int query(int p, int q) {
    int lg, i;

    //if p is situated on a higher level than q then we swap them
    if(L[p] < L[q])
        swap(p, q);

    //we compute the value of [log(L[p)]
    for(lg = 1; (1 << lg) <= L[p]; lg++);
    lg--;

    //we find the ancestor of node p situated on the same level
    //with q using the values in P
    for(int i = lg; i >= 0; i--) {
        if(L[p] - (1 << i) >= L[q]) {
            p = P[p][i];
        }
    }

    if(p == q) {
        return p;
    }

    //we compute LCA(p, q) using the values in P
    for(int i = lg; i >= 0; i--) {
        if(P[p][i] != -1 && P[p][i] != P[q][i]) {
            p = P[p][i];
            q = P[q][i];
        }
    }

    return T[p];
}

int main() {
    while(~scanf("%d", &n) && n) {
        cost[0] = 0;
        REP(i, 1, n) {
            int a, c; scanf("%d%d", &a, &c);
            T[i] = a;
            cost[i] = c;
        }

        memset(L, -1, n * sizeof(int));
        memset(sumRoot, -1, n * sizeof(int64));

        L[0] = 0;
        sumRoot[0] = 0;
        REP(u, 1, n) {
            calc_level(u);
            calc_sum(u);
        }

        preprocess();

        int Q; scanf("%d", &Q);

        REP(i, 0, Q) {
            int s, t; scanf("%d%d", &s, &t);

            int lca = query(s, t);
            int64 sum = sumRoot[s] + sumRoot[t] - sumRoot[lca] - sumRoot[lca];

            if(i) printf(" ");
            printf("%lld", sum);
        }
        printf("\n");
    }
}

