#include <bits/stdc++.h>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

int main() {
  int n = 100000;
  cout << n << endl;
  for (int i = 0; i < n; i++)
    cout << i+1 << ' ';
  cout << endl;
  for (int i = n; i > 0; i--)
    cout << i << ' ';
  cout << endl;
  cout << 0 << endl;
  return 0;
}
