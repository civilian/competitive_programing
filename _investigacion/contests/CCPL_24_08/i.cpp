#include <bits/stdc++.h>

using namespace std;

// LiveArchive: 2640 - A Simple Question of Chemistry
// https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8&category=136&page=show_problem&problem=641

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  cout.precision(2);
  cout.setf(ios::fixed);

  string ln;
  bool first = true;
  double prev = 0;

  while (getline(cin, ln)) {
    if (ln == "999")
      break;

    double x = atof(ln.c_str());
    if (first)
      first = false;
    else
      cout << x - prev << '\n';
    prev = x;
  }
  cout << "End of Output\n";

  return 0;
}
