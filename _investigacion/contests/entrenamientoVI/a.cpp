#include <bits/stdc++.h>

using namespace std;

int goods[] = {1, 2, 3, 3, 4, 10};
int bads[] = {1, 2, 2, 2, 3, 5, 10};

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int t;
  cin >> t;
  
  for (int tc = 1; tc <= t; tc++) {
    int sum_good = 0, sum_bad = 0;
    for (int i = 0, v; i < 6; i++) {
      cin >> v;
      sum_good += goods[i] * v;
    }
    
    for (int i = 0, v; i < 7; i++) {
      cin >> v;
      sum_bad += bads[i] * v;
    }
    
    cout << "Battle " << tc << ": ";
    if (sum_good == sum_bad)
      cout << "No victor on this battle field\n";
    else {
      if (sum_good > sum_bad)
        cout << "Good triumphs over Evil\n";
      else
        cout << "Evil eradicates all trace of Good\n";
    }
  }
  
  return 0;
}
