// 12522 - The Imperial Problem

#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <ctime>
#include <cctype>
#include <cassert>
#include <iostream>
#include <sstream>
#include <iomanip>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <deque>
#include <list>
#include <set>
#include <map>
#include <bitset>
#include <algorithm>
#include <numeric>
#include <complex>

#define D(x) cout << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;

int todec[256];

char lett[] = { 'M', 'C', 'X', 'I' };
string rep[3][3] = { { "CCCCCCCCC", "CCCCC", "CCCC"},
                     { "XXXXXXXXX", "XXXXX", "XXXX"},
                     { "IIIIIIIII", "IIIII", "IIII"} };
                    
string to[3][3] = { { "CM", "D", "CD" },
                   { "XC", "L", "XL" },
                   { "IX", "V", "IV"} };

char lett_remp[] = { 'M', 'D', 'C', 'L', 'X', 'V', 'I' };

string replace(string s, int i) {
    if(i < 0) return s; // M
    
    for(int j = 0; j < 3; j++) {
        int pos = s.find(rep[i][j]);
        if(pos != string::npos) {
            s.replace(pos, rep[i][j].size(), to[i][j]);
        }
    }
    return s;
}

string toroman(int n) {
    stringstream ans;
    for(int i = 0; i < 4; i++) { // {M, C, X, I}
        stringstream ss;
        while(n >= todec[lett[i]]) {
            ss << lett[i];
            n -= todec[lett[i]];
        }
        ans << replace(ss.str(), i-1);
    }
    return ans.str();
}

pair<int,int> solve(string fail, string good) {
    int n = (int)fail.size();
    int m = (int)good.size();
    
    int diff = n - m;
    int sum = INF;
    int e = INF;
    
    for(int i = 0; i < n - m + 1; i++) {
        int cnt = 0;
        for(int j = 0; j < m; j++) {
            if(fail[i+j] != good[j]) {
                cnt++;
            }
        }
        if(cnt < sum) {
            sum = cnt;
            e = diff + cnt;
        }
        else if(cnt == sum) {
            if(diff + cnt < e) {
                sum = cnt;
                e = diff + cnt;
            }
        }
    }
    return make_pair(e, sum);
}

int main() {
    ios_base::sync_with_stdio(false);
    //freopen("imperial.in", "r", stdin);
    //freopen("imperial.txt", "w", stdout);
    
    string roman;
    todec['I'] = 1;
    todec['V'] = 5;
    todec['X'] = 10;
    todec['L'] = 50;
    todec['C'] = 100;
    todec['D'] = 500;
    todec['M'] = 1000;
    
    while(cin >> roman) {
        if(roman == "*") break;
        
        int dec = 0;
        REP(i, 0, roman.size())
            dec += todec[roman[i]];
        
        string good = toroman(dec);
        //cout << dec << " " << toroman(dec) << endl;
        pair<int,int> ans = solve(roman, good);
        cout << ans.first << " " << ans.second << endl;
    }
}

