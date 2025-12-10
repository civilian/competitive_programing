#include <cstdlib>
#include <cstring>
#include <cassert>
#include <iostream>
#include <vector>
#include <algorithm>

#define D(x) cout << #x " = " << x << endl

using namespace std;

typedef long long int64;

const int maxn = 100010;
vector<int64> t, a;

int sign(int x) {
    return (x < 0)? -1 : (x > 0)? 1 : 0;
}

void build(int v, int tl, int tr) {
    if(tl == tr)
        t[v] = sign(a[tl]);
    else {
        int tm = (tl + tr) >> 1;
        build(2*v, tl, tm);
        build(2*v+1, tm+1, tr);
        t[v] = t[2*v] * t[2*v+1];
    }
}

void update(int v, int tl, int tr, int pos, int64 new_val) {
    if(tl == tr) {
        t[v] = sign(new_val);
    }
    else {
        int tm = (tl + tr) >> 1;
        if(pos <= tm)
            update(2*v, tl, tm, pos, new_val);
        else
            update(2*v+1, tm+1, tr, pos, new_val);

        t[v] = t[2*v] * t[2*v+1];
    }
}

int64 query(int v, int tl, int tr, int l, int r) {
    if(l > tr || r < tl)
        return 1LL;
    if(tl >= l && tr <= r)
        return t[v];

    int tm = (tl + tr) >> 1;

    int64 left_part = query(2*v, tl, tm, l, r);
    int64 right_part = query(2*v+1, tm+1, tr, l, r);
    
    //cout << "tl = " << tl << ", tr = " << tr << ", l = " << l << ", r = " << r <<", left_part" << left_part << ", right_part = " << right_part << endl;

    return (left_part * right_part);
}

int main() {
#ifdef LOCAL
    freopen("i_2.in", "r", stdin);
    freopen("i.out", "w", stdout);
#else
    ios_base::sync_with_stdio(false);
#endif

    int n, k;
    while(cin >> n >> k) {
        a.assign(maxn, 0);
        //cout << "a = ";
        for(int i = 1; i <= n; i++) {
            cin >> a[i];
            //cout << a[i] << " ";
        }
        //cout << endl;
        t.assign(4*maxn, 1LL);
        build(1, 1, n);
        //cout << "t = ";
        //for(int i = 1; i <= 4*n; i++)
        //    cout << t[i] << " ";
        //cout << endl;
        string ans;

        while(k--) {
            string op;
            cin >> op;

            assert(op == "C" || op == "P");
            if(op == "C") {
                int pos, v;
                cin >> pos >> v;
                update(1, 1, n, pos, v);
                //cout << "t = ";
                //for(int i = 1; i <= 4*n; i++)
                //    cout << t[i] << " ";
                //cout << endl;
            }
            else if(op == "P") {
                int i, j;
                cin >> i >> j;
                //D(i); D(j);
                int64 ret = query(1, 1, n, i, j);
                //cout << "ret = " << ret << endl;

                if(ret < 0)
                    ans.push_back('-');
                else if(ret > 0) {
                    ans.push_back('+');
                }
                else {
                    ans.push_back('0');
                }
            }
        }
        
        cout << ans << endl;
    }
}
