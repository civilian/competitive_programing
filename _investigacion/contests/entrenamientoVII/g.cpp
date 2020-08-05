#include <bits/stdc++.h>

using namespace std;

int n;
string s, t;

void transform() {
  for (int i = 0; i < n; i++)
    s[i] = s[i] == '1'? '0' : '1';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int N;
  cin >> N >> s >> t;

  n = s.size();
  for (int i = 0; i < N; i++)
    transform();

  cout << (s == t? "Deletion succeeded" : "Deletion failed") << '\n';

  return 0;
}
