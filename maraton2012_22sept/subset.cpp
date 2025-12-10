#include <cstdlib>
#include <iostream>
#include <string>
#include <climits>
#include <vector>
#include <map>
#include <utility>
#include <algorithm>

using namespace std;

typedef long long int64;

const int64 INFLL = int64(1e18);

#define mp make_pair

int n, m;
vector<int64> v;
map< pair<int, int>, int64 > memo;

int64 rec(int i, int val) {
    if(i >= m && val > 0)
        return INFLL;
    
    if(val <= 0) {
        return 0;
    }
    
    pair<int, int> p = mp(i, val);
    if(memo.count(p))
        return memo[p];
    
    int64 r = min(v[i] + rec(i+1, val - v[i]),
                  rec(i+1, val));
                  
    memo[p] = r;
    return r;
}

int main(int argc, char *argv[])
{
    //freopen("d_in.txt", "r", stdin);
    //freopen("d_out.txt", "w", stdout);
    int t; cin >> t;

    while(t--) {
        cin >> n >> m;
        
        memo.clear();

        v.assign(m, 0);
        int64 sum = 0;
        for(int i = 0; i < m; i++) {
            cin >> v[i];
            sum += v[i];
        }
        
        if(sum < n) {
            cout << "IMPOSSIBLE" << endl;
        } else if(sum == n) {
            cout << sum << endl;
        } else {
            int64 ans = rec(0, n);
            cout << ans << endl;
        }
    }
    
    return 0;
}
