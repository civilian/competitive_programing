#include <cstdlib>
#include <cstring>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
#ifdef LOCAL
    freopen("in", "r", stdin);
    freopen("out", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
#endif
    vector<bool> angles(180, false);

    int min = 0, hour = 0;
    angles[0] = true;
    for(int i = 0; i < 60; i++) {
        min++;
        int angle_min = 6 * min;
        if(min % 12 == 0)
            hour++;
        int angle_hour = 6 * hour;
        angles[abs(angle_hour - angle_min)] = true;
    }
}
