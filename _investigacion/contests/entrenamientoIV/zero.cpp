#include <bits/stdc++.h>

using namespace std;

char winner(char a, char b, char c) {
  if (a == b && b == c)
    return '*';

  if (b == c && a != b)
    return 'A';
  if (a == c && a != b)
    return 'B';
  if (a == b && a != c)
    return 'C';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  char a, b, c;
  while (cin >> a >> b >> c)
    cout << winner(a, b, c) << '\n';

  return 0;
}
