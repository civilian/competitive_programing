#include <cstring>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct hexagono {
  int a[6][6];
  hexagono() {}
  
  void rotate() {
    for (int i = 1; i < 6; i++)
      for (int j = 0; j < 6; j++)
        a[i][j] = a[i-1][(j+5) % 6];
  }
} h[7];

bool solved;
int pos_ans[7], pos_rot[7];

bool check() {
  //centro
  if (h[pos_ans[3]].a[pos_rot[pos_ans[3]]][0] != h[pos_ans[0]].a[pos_rot[pos_ans[0]]][5])
    return false;
    
  if (h[pos_ans[3]].a[pos_rot[pos_ans[3]]][1] != h[pos_ans[1]].a[pos_rot[pos_ans[1]]][4])
    return false;
    
  if (h[pos_ans[3]].a[pos_rot[pos_ans[3]]][2] != h[pos_ans[2]].a[pos_rot[pos_ans[2]]][3])
    return false;
    
  if (h[pos_ans[3]].a[pos_rot[pos_ans[3]]][3] != h[pos_ans[4]].a[pos_rot[pos_ans[4]]][2])
    return false;
    
  if (h[pos_ans[3]].a[pos_rot[pos_ans[3]]][4] != h[pos_ans[5]].a[pos_rot[pos_ans[5]]][1])
    return false;
    
  if (h[pos_ans[3]].a[pos_rot[pos_ans[3]]][5] != h[pos_ans[6]].a[pos_rot[pos_ans[6]]][0])
    return false;

  // anillo
  if (h[pos_ans[0]].a[pos_rot[pos_ans[0]]][2] != h[pos_ans[1]].a[pos_rot[pos_ans[1]]][3])
    return false;

  if (h[pos_ans[1]].a[pos_rot[pos_ans[1]]][5] != h[pos_ans[2]].a[pos_rot[pos_ans[2]]][0])
    return false;
    
  if (h[pos_ans[2]].a[pos_rot[pos_ans[2]]][4] != h[pos_ans[6]].a[pos_rot[pos_ans[6]]][1])
    return false;

  if (h[pos_ans[4]].a[pos_rot[pos_ans[4]]][1] != h[pos_ans[0]].a[pos_rot[pos_ans[0]]][4])
    return false;

  if (h[pos_ans[5]].a[pos_rot[pos_ans[5]]][0] != h[pos_ans[4]].a[pos_rot[pos_ans[4]]][5])
    return false;
    
  if (h[pos_ans[6]].a[pos_rot[pos_ans[6]]][3] != h[pos_ans[5]].a[pos_rot[pos_ans[5]]][2])
    return false;
    
  return true;
}

void backtracking(int k) {
  if (!solved) {
    if (k == 7) {
      if (check())
        solved = true;
      return;
    }
    for (int j = 0; j < 7; j++) if (pos_ans[j] < 0) { // traslacion
      pos_ans[j] = k;
      
      if (j == 3) {
        backtracking(k+1);

        if (solved) return;
        continue;
      }
      
      for (int i = 0; i < 6; i++) { // rotacion
        pos_rot[k] = i;

        backtracking(k+1);

        if (solved) return;
      }
      pos_ans[j] = -1;
    }
  }
}

int main() {
#ifdef LOCAL
  freopen("hexagonal.in", "r", stdin);
  freopen("hexagonal.out", "w", stdout);
#else
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
#endif

  string ln;
  while (true) {
    for (int i = 0; i < 7; i++) {
      if (!getline(cin, ln))
        return 0;
      stringstream ss(ln);
      for (int j = 0; j < 6; j++) {
        ss >> h[i].a[0][j];
      }
      h[i].rotate();
    }
    
    for (int i = 0; i < 7; i++) {
      pos_ans[i] = -1;
    }
    
    solved = false;
    backtracking(0);
    
    if (solved) cout << "YES\n";
    else cout << "NO\n";
    cout.flush();
  }

  return 0;
}
