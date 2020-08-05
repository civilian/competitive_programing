#include <cstdlib>
#include <iostream>
#include <string>
#include <climits>
#include <vector>
#include <map>
#include <set>
#include <utility>
#include <algorithm>

#define ALL(v) (v).begin(), (v).end()
#define mp make_pair

using namespace std;

int n, m;
vector< pair<int, int> > points, sorted_points;
set< pair<int, int> > visited;

int main(int argc, char *argv[])
{
    //freopen("b_in.txt", "r", stdin);
    //freopen("b_out.txt", "w", stdout);
    int t; cin >> t;
    
    while(t--) {
        cin >> n >> m;
        
        points.clear();
        sorted_points.clear();
        
        for(int i = 0; i < n; i++) {
            pair<int, int> p;
            cin >> p.first >> p.second;
            points.push_back(p);
            
            sorted_points.push_back(p);
            sorted_points.push_back(mp(p.second, p.first));
        }
        
        sort(ALL(sorted_points));
        
        while(m--) {
            int x; cin >> x;
            visited.clear();
            int ans = 0;
            for(int i = 0; i < n; i++) {
                pair<int, int> p;
                if(x - points[i].second >= 0) {
                    p = make_pair(x - points[i].second, points[i].first); // verificar negativos
                
                    if(!visited.count(p)) {
                        int j = int(lower_bound(ALL(sorted_points), p) - sorted_points.begin());
                        if(sorted_points[j] == p) {
                            ans++;
                            visited.insert(p);
                            visited.insert(mp(p.second, p.first));
                        }
                    }
                }
                
                if(x - points[i].first >= 0) {
                   p = make_pair(points[i].second, x - points[i].first);
                
                    if(!visited.count(p)) {
                        int j = int(lower_bound(ALL(sorted_points), p) - sorted_points.begin());
                        if(sorted_points[j] == p) {
                            ans++;
                            visited.insert(p);
                            visited.insert(mp(p.second, p.first));
                        }
                    }
                }
                visited.insert(points[i]);
                visited.insert(mp(points[i].second, points[i].first));
            }
            cout << ans << endl;
        }
    }
}
