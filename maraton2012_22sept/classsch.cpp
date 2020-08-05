#include <cstdlib>
#include <iostream>
#include <string>
#include <climits>
#include <vector>
#include <map>
#include <utility>
#include <algorithm>
#include <cstring>
#include <cmath>

using namespace std;

typedef long long int64;

const int64 INFLL = int64(1e18);

const int maxt = 1010;
const int maxc = 30;

int64 dp[maxc][maxt];
int energy[maxc][maxt], location[maxc][maxt], C, T, L;

int main(int argc, char *argv[])
{
    //freopen("h_in.txt", "r", stdin);
    //freopen("h_out.txt", "w", stdout);
    
    int cases; cin >> cases;
    
    while(cases--) {
        cin >> C >> T >> L;
        
        for(int i = 0; i < C; i++) {
            for(int j = 0; j < T; j++) {
                cin >> location[i][j] >> energy[i][j];
            }
        }
        
        //memset(dp, 0x3f, sizeof(dp));
        
        for(int i = 0; i < T; i++)
            dp[0][i] = energy[0][i] + location[0][i];
            
        for(int i = 1; i < C; i++) {
            for(int j = 0; j < T; j++) {
                int64 r = INFLL;
                for(int k = 0; k < T; k++)
                    r = min(r, dp[i-1][k] + abs(location[i][j] - location[i-1][k]));
                dp[i][j] = r + energy[i][j];
            }
        }
        
        int64 ans = INFLL;
        for(int i = 0; i < T; i++)
            ans = min(ans, dp[C-1][i] + abs(L - location[C-1][i]));
        cout << ans << endl;
    }
    
    return 0;
}
