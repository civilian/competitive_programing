#include <cstdlib>
#include <iostream>
#include <string>
#include <vector>
#include <cctype>
#include <algorithm>

using namespace std;

int nums[26];

void calculate_nums() {
    int n = 2;
    for(int i = 0; i < 25; i++) {
        for(int j = 0; j < 3; j++) {
            nums[i++] = n;
        }
        if(n == 7)
                nums[i++] = n;
        i--;
        n++;
    }
    nums[25] = n-1;
}

int main()
{
    //ios_base::sync_with_stdio(false);
    //freopen("g.in", "r", stdin);
    //freopen("g.txt", "w", stdout);
    int t; cin >> t;
    calculate_nums();
    
    while(t--) {
        string s; cin >> s;

        int n = s.size();
        vector<int> a(n);
        for(int i = 0; i < n; i++) {
            a[i] = nums[tolower(s[i]) - 'a'];
        }

        vector<int> b = a;
        reverse(b.begin(), b.end());

        if(a == b) cout << "YES" << endl;
        else cout << "NO" << endl;
    }
    return 0;
}
