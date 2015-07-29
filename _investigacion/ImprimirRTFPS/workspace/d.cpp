#include <cstdlib>
#include <cstring>
#include <iostream>
#include <vector>
#include <algorithm>



using namespace std;

bool repeats(int n) {
    vector<int> digits(10, 0);
    while(n > 0) {
        digits[n % 10]++;
        n /= 10;
    }
    
    for(int i = 0; i < 10; i++) {
        if(digits[i] > 1)
            return true;
    }
    return false;
}

int main() {
#ifdef LOCAL
    freopen("d.in", "r", stdin);
    freopen("d.out", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
#endif

    vector<bool> cantidad_repetidos(5001, false);
    for(int i = 1; i <= 5000; i++) {
        cantidad_repetidos[i] = repeats(i);
    }
    
    int n, m;
    while(cin >> n >> m) {
        int ans = 0;
        for(int i = n; i <= m; i++) {
            if(!cantidad_repetidos[i])
                ans++;
        }
        cout << ans << endl;
    }
}
