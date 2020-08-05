#include <bits/stdc++.h>

using namespace std;

const double EPS = 1e-9;

int compareTo(double x, double y, double tol=EPS) {
  return (x <= y + tol)? (x + tol < y)? -1 : 0 : 1;
}

bool valid(const vector<double>& v) {
  double sum = 0.0;

  for(int i = 0; i < v.size()-1; i++) {
    sum += v[i];
    for(int j = i+1; j < v.size(); j++) {
      if(compareTo(sum, v[j]) >= 0)
        return true;
    }
  }

  return false;
}

int main() {
#ifdef LOCAL
  freopen("e.in", "r", stdin);
  freopen("e.txt", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
#endif

  int n;
  while(cin >> n) {
    if(n == 0)
      break;

    vector<double> v(n);
    for(int i = 0; i < n; i++)
      cin >> v[i];
    sort(v.begin(), v.end());

    if(n == 1) {
      cout << "NO" << endl;
    }
    else if(n == 2) {
      if(compareTo(v[0], v[1]) == 0)
        cout << "YES" << endl;
      else
        cout << "NO" << endl;
    }
    else {
      if(valid(v))
        cout << "YES" << endl;
      else
        cout << "NO" << endl;
    }
  }

  return 0;
}
