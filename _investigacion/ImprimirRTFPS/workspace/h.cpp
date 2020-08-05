#include <cstdlib>
#include <cstring>
#include <cmath>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
#ifdef LOCAL
    freopen("h.in", "r", stdin);
    freopen("h.out", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
#endif
    int n;
    while(cin >> n) {
        cout << ((n % 6 == 0)? 'Y' : 'N') << endl;
    }
}
