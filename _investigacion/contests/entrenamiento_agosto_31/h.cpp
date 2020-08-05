#include <cstdlib>
#include <cstring>
#include <cassert>
#include <cmath>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <utility>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

int n, m;
string last_city;
vector<string> cities;

bool contains(string log) {
  for (int i = 0; i < n; i++) {
    if (cities[i] == last_city)
      continue;
    if (log.find(cities[i]) != string::npos) {
      last_city = cities[i];
      return true;
    }
  }
  return false;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int t;
  cin >> t;
  
  for (int tc = 1; tc <= t; tc++) {
    cities.clear();

    cin >> n;
    cin.get();
    
    string s;
    for (int i = 0; i < n; i++) {
      getline(cin, s);
      cities.push_back(s);
    }
    
    last_city = "";
    cin >> m;
    cin.get();
    
    cout << "Brett Log #" << tc << ":\n";
    
    for (int i = 0; i < m; i++) {
      getline(cin, s);
      if (contains(s)) {
        cout << "   " << s << '\n';
      }
    }
    cout << "\n";
  }
  
  return 0;
}
