#include <bits/stdc++.h>

#define foreach(it,v) for (__typeof((v).begin()) it=(v).begin(); it!=(v).end(); it++)

using namespace std;

struct mtuple {
  int x,y,z;
  mtuple(int x, int y, int z) :
    x(x), y(y), z(z) {}
};

int n, m;
set<string> runes;
vector<mtuple> st;
bool is_missing, has_repeated, val[25];

bool read_st() {
  is_missing = has_repeated = false;
  st.clear();
  foreach (it, runes) {
    string s = *it;
    stringstream ss(s);
    //cerr << "s => " << s << '\n';
    if (s.find("-0") != string::npos)
      return false;
    int a, b, c;
    ss >> a >> b >> c;
    //cerr << "a => " << a << " b => " << b << " c => " << c << '\n';
    if (a == 0 || b == 0 || c == 0)
      return false;

    if ((a < -n || a > n) || (b < -n || b > n) || (c < -n || c > n))
      is_missing = true;

    if (abs(a) == abs(b) || abs(a) == abs(c) || abs(b) == abs(c))
      has_repeated = true;

    st.push_back(mtuple(a,b,c));
  }
  return true;
}

bool check() {
  foreach (it, st) {
    mtuple t = *it;
    bool a = (t.x < 0)? !val[-t.x] : val[t.x];
    bool b = (t.y < 0)? !val[-t.y] : val[t.y];
    bool c = (t.z < 0)? !val[-t.z] : val[t.z];

    if (!(a || b || c))
      return false;
  }
  return true;
}

bool solve() {
  for (int mask = 0; mask < (1 << n); mask++) {
    for (int i = 0; i < n; i++)
      val[i+1] = (mask & (1 << i)) != 0;
    if (check())
      return true;
  }
  return false;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  int t;
  cin >> t;

  while (t--) {
    cin >> n >> m;
    cin.get();

    runes.clear();
    for (int i = 0; i < m; i++) {
      string ln;
      getline(cin, ln);
      runes.insert(ln);
    }

    if (!read_st()) {
      cout << "INVALID: NULL RING\n";
    }
    else if (is_missing) {
      cout << "INVALID: RING MISSING\n";
    }
    else if (has_repeated) {
      cout << "INVALID: RUNE CONTAINS A REPEATED RING\n";
    }
    else {
      if (solve())
        cout << "RUNES SATISFIED!\n";
      else
        cout << "RUNES UNSATISFIABLE! TRY ANOTHER GATE!\n";
    }
  }

  return 0;
}

