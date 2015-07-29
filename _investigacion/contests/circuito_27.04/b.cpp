#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <ctime>
#include <cctype>
#include <cassert>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <list>
#include <set>
#include <map>
#include <bitset>
#include <algorithm>

#define REP(i,a,n) for(int i=(a); i<(int)(n); i++)
#define FOREACH(it,v) for(typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

#define ALL_BITS 0xFFFFFFFF

typedef long long int64;
const int INF = (int)(1e9);
const int64 INFLL = (int64)(1e18);
const double EPS = (1e-19);

using namespace std;

class GeometricProgressions {
  public:
    int count(int b1, int q1, int n1, int b2, int q2, int n2);
};

void factor(int q, set<int>& f) {
  for(int p = 2; p*p <= q; p++) {
    if(q % p == 0) {
      while(q % p == 0)
        q /= p;
      f.insert(p);
    }
  }

  if(q > 1)
    f.insert(q);
}

void decompose(int q, vector<int>& rep, const vector<int>& F) {
  for(int i = 0; i < F.size(); i++) {
    int cnt = 0;

    while(q % F[i] == 0) {
      q /= F[i];
      cnt++;
    }

    rep.push_back(cnt);
  }
}

int GeometricProgressions::count(int b1, int q1, int n1, int b2, int q2, int n2) {
  if(b2 == 0 || q2 <= 1) {
    swap(b1, b2); swap(q1, q2); swap(n1, n2);
  }

  if(b1 == 0 || q1 <= 1) {
    set<long long> S;
    S.insert(b1);
    if(n1 > 1)
      S.insert(b1*q1);

    long long curr = b2;
    for(int i = 0; i < n2; i++) {
      S.insert(curr);
      curr *= q2;

      if(curr > 500000000)
        return (n2 - i - 1) + S.size();
    }
    return S.size();
  }

  set<int> factors;
  factor(b1, factors); factor(q1, factors);
  factor(b2, factors); factor(q2, factors);

  vector<int> F(ALL(factors));
  vector<int> repb1, repq1, repb2, repq2;

  decompose(b1, repb1, F); decompose(q1, repq1, F);
  decompose(b2, repb2, F); decompose(q2, repq2, F);

  set< vector<int> > S;

  for(int i = 1; i <= n1; i++) {
    S.insert(repb1);
    for(int j = 0; j < repb1.size(); j++)
      repb1[j] += repq1[j];
  }

  for(int i = 1; i <= n2; i++) {
    S.insert(repb2);
    for(int j = 0; j < repb2.size(); j++)
      repb2[j] += repq2[j];
  }

  return S.size();
}

int main() {
  ios_base::sync_with_stdio(false);

  int b1, q1, n1, b2, q2, n2;
  GeometricProgressions obj;

  while(cin >> b1 >> q1 >> n1 >> b2 >> q2 >> n2) {
    cout << obj.count(b1,q1,n1,b2,q2,n2) << endl;
  }

  return 0;
}
