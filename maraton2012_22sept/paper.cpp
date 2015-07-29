#include <cstdlib>
#include <iostream>
#include <string>
#include <climits>
#include <vector>
#include <map>
#include <utility>
#include <algorithm>

#define mp make_pair

using namespace std;

typedef long long int64;

const int64 INFLL = int64(1e18);

int n;
int64 sum;
vector< vector< pair<int, int64> > > G;
vector<int64> cost;
vector<bool> visited;
int64 ans;

void dfs(int u, int64 sum) {
    if(visited[u]) return;
    visited[u] = true;
    
    for(int i = 0; i < G[u].size(); i++) {
        pair<int, int> v = G[u][i];
        if(visited[v.first]) continue;
        int64 temp = sum - v.second;
        //cout << "temp  = " << temp << " u = " << u << " cost[u] = " << cost[u] << endl;
        ans = min(ans, temp + cost[v.first]);
        dfs(v.first, temp);
    }
}

int main(int argc, char *argv[])
{
//    freopen("e_in.txt", "r", stdin);
//    freopen("e_out.txt", "w", stdout);
    
    int t; cin >> t;
    
    while(t--) {
        G.clear();
        
        cin >> n;
        G.clear();
        G.resize(n+1);
        cost.assign(n+1,0);
        
        for(int i = 0; i <= n; i++)
            cin >> cost[i];
        
        sum = 0;
        for(int i = 0; i < n; i++) {
            int a, b;
            int64 c; cin >> a >> b >> c;
            sum += c;
            G[a].push_back(mp(b, c));
            G[b].push_back(mp(a, c));
        }
        
        sum = 2 * sum;
        
        //cout <<  "sum = " << sum << endl;
        ans = sum + cost[0];
        
        visited.assign(n+1, false);
        dfs(0, sum);
        
        cout << ans << endl;
    }
    
    return 0;
}
