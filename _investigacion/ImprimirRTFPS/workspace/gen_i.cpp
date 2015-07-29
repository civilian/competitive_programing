#include <cstdlib>
#include <cstring>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
#ifdef LOCAL
    //freopen("in", "r", stdin);
    freopen("gen_i.in", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
#endif
    int n = 100000, k = 100000;
    cout << n << " " << k << endl;
    for(int i = 0; i < n; i++)
        cout << 100 << " ";
    cout << endl;
    for(int i = 0; i < k; i++)
        cout << "P 1 100000" << endl;
}
