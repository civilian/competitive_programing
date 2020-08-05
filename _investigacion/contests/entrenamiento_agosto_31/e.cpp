#include <cstdlib>
#include <cassert>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include <utility>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

string gas[3];

bool check(string a, string b) {
  return atoi(a.c_str()) < atoi(b.c_str());
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int t;
  cin >> t;
  
  for (int tc = 1; tc <= t; tc++) {
    for (int i = 0; i < 3; i++)
      cin >> gas[i];

    cout << "Gas Station #" << tc << ":\n";
    cout << "   Input: ";
    for (int i = 0; i < 3; i++)
      cout << ' ' << gas[i];
    cout << '\n';

    for (int i = 0; i < 3; i++) {
      int pos = gas[i].find('-');
      
      if (pos != string::npos) {
        if (i == 0) {
          if (pos == 0) gas[i][pos] = '2';
          else gas[i][pos] = '0';
        }
        else {
          for (char ch = (pos == 0)? '2' : '0'; ch <= '9'; ch++) {
            gas[i][pos] = ch;
            if (check(gas[i-1], gas[i]))
              break;
          }
        }
      }
    }
    assert(check(gas[0], gas[1]) && check(gas[1], gas[2]));
    cout << "   Output:";
    for (int i = 0; i < 3; i++)
      cout << ' ' << gas[i];
    cout << "\n\n";
  }
  
  return 0;
}
