#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int N, tc = 1;
  while (cin >> N) {
    if (N == 0)
      break;
    string s;
    cin >> s;

    int m = s.size();
    list<char> cache;
    list<char>::iterator it;

    int cnt = 0;

    cout << "Simulation " << tc++ << '\n';
    for (int i = 0; i < m; i++) {
      if (s[i] == '!') {
        for (list<char>::reverse_iterator rit = cache.rbegin(); rit != cache.rend(); rit++)
          cout << *rit;
        cout << '\n';
      }
      else {
        for (it = cache.begin(); it != cache.end(); it++)
          if (*it == s[i])
            break;

        if (it != cache.end()) {
          cache.erase(it);
          cnt--;
        }
        else if (cnt >= N) {
          cache.pop_back();
          cnt--;
        }

        cache.push_front(s[i]);
        cnt++;
      }
    }
  }

  return 0;
}
