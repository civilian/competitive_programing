#include <bits/stdc++.h>

using namespace std;

string nums[5][10] = {
  {"***", "  *", "***", "***", "* *", "***", "***", "***", "***", "***"},
  {"* *", "  *", "  *", "  *", "* *", "*  ", "*  ", "  *", "* *", "* *"},
  {"* *", "  *", "***", "***", "***", "***", "***", "  *", "***", "***"},
  {"* *", "  *", "*  ", "  *", "  *", "  *", "* *", "  *", "* *", "  *"},
  {"***", "  *", "***", "***", "  *", "***", "***", "  *", "***", "***"},
};

string num[5];
bool ok;

bool check(int x, int l, int r) {
  for (int i = 0; i < 5; i++)
    for (int j = 0; j < 3; j++)
      if (nums[i][x][j] != num[i][l+j])
        return false;
  return true;
}

char solve(int l, int r) {
  for (int i = 0; i < 10; i++)
    if (check(i, l, r))
      return '0' + i;

  ok = false;
  return 'X';
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  for (int i = 0; i < 5; i++)
    getline(cin, num[i]);

  string s;
  ok = true;
  for (int i = 0; i < num[0].size(); i++) {
    s.push_back(solve(i, i+3));
    i += 3;
  }

  cout << ((ok && (atoi(s.c_str()) % 6 == 0))? "BEER!!" : "BOOM!!") << '\n';

  return 0;
}
