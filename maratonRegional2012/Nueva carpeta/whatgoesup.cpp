#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cmath>
#include <ctime>
#include <cctype>
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
#define REP(i,a,n) for(int i=(a); i<(n); i++)
#define FOREACH(it,v) for(typeof((v).begin()) it=(v).begin(); it!=(v).end(); ++it)
#define ALL(v) (v).begin(), (v).end()

using namespace std;

template<typename T,typename U> inline
std::ostream& operator<<(std::ostream& os, const pair<T,U>& z){
  return ( os << "(" << z.first << ", " << z.second << ",)" );
}
template<typename T> inline
std::ostream& operator<<(std::ostream& os, const vector<T>& z){
  os << "[ ";
  REP(i,0,z.size())os << z[i] << ", " ;
  return ( os << "]" << endl);
}
template<typename T> inline
std::ostream& operator<<(std::ostream& os, const set<T>& z){
  os << "set( ";
  FOREACH(p,z)os << (*p) << ", " ;
  return ( os << ")" << endl);
}
template<typename T,typename U> inline
std::ostream& operator<<(std::ostream& os, const map<T,U>& z){
  os << "{ ";
  FOREACH(p,z)os << (p->first) << ": " << (p->second) << ", " ;
  return ( os << "}" << endl);
}

const int INF = 1000000000;
const long long INFLL = 1000000000000000000LL;
const double EPS = 1e-13;

// 481 - What Goes Up
// Algorithm : (LIS nlog k)

typedef pair < int , int > PI;

vector<int> solvenlogk(const vector<int>& A) {
  int N = A.size(), j=-1, t;
  vector<int> pre(N,-1), res;
  map<int,int> m;
  map<int,int>::iterator k, l;
  REP(i, 0, N) {
    if(m.insert(PI(A[i], i)).second) {
      k = m.find(A[i]);
      l = k;
      k++;
      if(l == m.begin())
        pre[i]=-1;
      else {
        l--;
        pre[i]=l->second;
      }
      if(k != m.end())
        m.erase(k);
    }
  }
  k = m.end();
  k--;
  j = k->second;
  while (j != -1) {
    res.push_back(A[j]);
    j = pre[j];
  }
  reverse(ALL(res));
  return res;
}

int main() {
  vector<int> v;
  for(int n; scanf("%d", &n) == 1; v.push_back(n));
  vector<int> ans = solvenlogk(v);
  printf("%d\n-\n", (int)ans.size());
  for(int i = 0; i < ans.size(); i++) printf("%d\n", ans[i]);
}

