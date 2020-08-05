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

#define D(x) cerr << #x << " = " << (x) << endl;
#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(__typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

typedef long long int64;

const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = 1e-13;


int main() {
  ios_base::sync_with_stdio(false);

  int C, Q, K, case_id = 0;

  while(cin >> C >> Q >> K) {
    if((C|Q|K) == 0)
      break;

    map<string,int> g;
    vector<int> a;

    for(int i = 0; i < C; i++) {
      string name;
      int cnt;
      cin >> name >> cnt;
      if(!g.count(name))
        g[name] = i;
      for(int j = 0; j < cnt; j++)
        a.push_back(g[name]);
    }

    if(case_id)
      printf("\n");
    printf("Caso #%d:\n", ++case_id);

    cin.get();

    while(Q--) {
      string ln;
      getline(cin, ln);
      stringstream ss(ln);

      vector<int> days;
      int b;
      while(ss >> b)
        days.push_back(b-1);

      int n = a.size();
      int m = days.size();

      vector<bool> seen(n);
      seen[days[m-7]] = true;
      for(int i = 1; i <= K; i++)
        seen[days[m-i]] = true;

      int last_color = a[days[m-1]];
      vector<int> ans;
      for(int i = 0; i < n; i++) {
        if(a[i] != last_color && !seen[i]) {
          ans.push_back(i+1);
        }
      }

      if(ans.empty())
        printf("Comprar otra blusa\n");
      else {
        for(int i = 0; i < ans.size(); i++) {
          if(i) printf(" ");
          printf("%d", ans[i]);
        }
        printf("\n");
      }
    }
  }

  return 0;
}

