#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<char> v[2];

void solve(string a, string b) {
  v[0] = vector<char>(a.begin(), a.end());
  v[1] = vector<char>(b.begin(), b.end());

  for (int i = v[0].size(), j = v[1].size(); i > 0 && j > 0; i--, j--) {
    if (v[0].size() == 1 || v[1].size() == 1)
      break;
    if (v[0].back() == v[1].back()) {
      v[0].pop_back();
      v[1].pop_back();
    }
    else break;
  }

  cout << "   The input words are " << a << " and " << b << ".\n";
  cout << "   The words entered in the notebook are ";
  cout << string(v[0].begin(), v[0].end()) << " and " << string(v[1].begin(), v[1].end());
  cout << ".\n";
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  for (int tc = 1; tc <= t; tc++) {
    cout << "Game #" << tc << ":\n";
    string s, t;
    cin >> s >> t;
    solve(s, t);
    cout << '\n';
  }

  return 0;
}
