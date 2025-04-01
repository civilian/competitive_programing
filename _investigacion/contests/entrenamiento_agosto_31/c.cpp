#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool four_of_a_kind(string s) {
  int n = s.size();
  for (int i = 0; i < n; i++) {
    int cnt = 0;
    int j = i;
    while (j < n && s[i] == s[j])
      cnt++, j++;
    if (cnt >= 4)
      return true;
    i = j-1;
  }
  return false;
}

bool two_of_a_kind(string s) {
  int n = s.size();
  for (int i = 0; i < n; i++) {
    int cnt = 0;
    int j = i;
    while (j < n && s[i] == s[j])
      cnt++, j++;
    if (cnt >= 2)
      return true;
    i = j-1;
  }
  return false;
}

bool three_of_a_kind(string s) {
  int n = s.size();
  for (int i = 0; i < n; i++) {
    int cnt = 0;
    int j = i;
    while (j < n && s[i] == s[j])
      cnt++, j++;
    if (cnt >= 3)
      return true;
    i = j-1;
  }
  return false;
}

bool full_house(string s) {
  int n = s.size();
  for (int i = 0; i < n; i++) {
    int cnt = 0;
    int j = i;
    while (j < n && s[i] == s[j])
      cnt++, j++;
    if (cnt >= 3) {
      string t;
      for (int k = 0; k < n; k++)
        if (s[k] != s[i])
          t.push_back(s[k]);
      if (two_of_a_kind(t))
        return true;
    }
    i = j-1;
  }
  return false;
}

void solve(string s) {
  sort(s.begin(), s.end());
  cout << "Best possible hand: ";
  
  if (four_of_a_kind(s))
    cout << "FOUR OF A KIND";
  else if (full_house(s))
    cout << "FULL HOUSE";
  else if (three_of_a_kind(s))
    cout << "THREE OF A KIND";
  else if (two_of_a_kind(s))
    cout << "TWO OF A KIND";
  else
    cout << "BUST";
    
  cout << '\n';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int t;
  cin >> t;
  
  for (int tc = 1; tc <= t; tc++) {
    string s;
    cin >> s;
    
    cout << "UCF Hold-em #" << tc << ": " << s << '\n';
    solve(s);
    cout << '\n';
  }
  
  return 0;
}
