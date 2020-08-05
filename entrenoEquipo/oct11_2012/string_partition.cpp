#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <cstring>
#include <climits>
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

const int maxn = 210;

int n;
string num;
int64 memo[maxn];


int64 solve(int i) {
    if(i == n) return 0;
    if(memo[i] != -1) return memo[i];
    int64 x = 0;
    for(int k = i; k < n; k++) {
        x *= 10;
        x += num[k] - '0';
        if(x > INT_MAX) break;
        
        memo[i] = max(memo[i], x + solve(k+1));
    }
    return memo[i];
}

int main() {
    ios_base::sync_with_stdio(false);
    int t; cin >> t;
    while(t--) {
        cin >> num;
        n = (int)num.size();
        memset(memo, -1, sizeof(memo));
        cout << solve(0) << endl;
    }
}
