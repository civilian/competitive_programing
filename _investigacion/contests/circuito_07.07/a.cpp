#include <bits/stdc++.h>

using namespace std;

const int maxn = 40010;

int m;
string s;

class SuffixArray {
public:
  char T[maxn];
  int n;
  int RA[maxn], tempRA[maxn];
  int SA[maxn], tempSA[maxn];
  int LCP[maxn], PLCP[maxn], Phi[maxn];
  int c[maxn];

  SuffixArray() {
    n = 0;
    memset(T, 0, sizeof(T));
  }

  SuffixArray(string str) {
    n = (int)str.size();
    memset(T, 0, sizeof(T));
    for(int i = 0; i < n; i++)
      T[i] = str[i];
  }

  void countingSort(int k) {
    int maxi = max(300, n);

    memset(c, 0, sizeof(c));

    for(int i = 0; i < n; i++) {
      if(i + k < n)
        c[RA[i+k]]++;
      else
        c[0]++;
    }

    for(int i = 0, sum = 0; i < maxi; i++) {
      int t = c[i];
      c[i] = sum;
      sum += t;
    }

    for(int i = 0; i < n; i++) {
      if(SA[i] + k < n) {
        tempSA[ c[RA[SA[i] + k]] ] = SA[i];
        c[RA[SA[i] + k]]++;
      }
      else {
        tempSA[c[0]] = SA[i];
        c[0]++;
      }
    }

    for(int i = 0; i < n; i++)
      SA[i] = tempSA[i];
  }

  void constructSA() {
    T[n++] = '$';

    for(int i = 0; i < n; i++)
      RA[i] = T[i] - '$';

    for(int i = 0; i < n; i++)
      SA[i] = i;

    int rank;

    for(int k = 1; k < n; k <<= 1) {
      countingSort(k);
      countingSort(0);

      tempRA[SA[0]] = rank = 0;

      for(int i = 1; i < n; i++) {
        if(RA[SA[i]] == RA[SA[i-1]] && RA[SA[i] + k] == RA[SA[i-1] + k]) {
          tempRA[SA[i]] = rank;
        }
        else {
          rank++;
          tempRA[SA[i]] = rank;
        }
      }

      for(int i = 0; i < n; i++)
        RA[i] = tempRA[i];
    }
  }

  void computeLCP() {
    Phi[SA[0]] = -1;

    for(int i = 1; i < n; i++) {
      Phi[SA[i]] = SA[i-1];
    }

    for(int i = 0, L = 0; i < n; i++) {
      if(Phi[i] == -1) {
        PLCP[i] = 0;
        continue;
      }

      while(T[i + L] == T[Phi[i] + L])
        L++;

      PLCP[i] = L;
      L = max(L-1, 0);
    }

    for(int i = 1; i < n; i++) {
      LCP[i] = PLCP[SA[i]];
    }
    LCP[n] = 0;
  }
};
// END Suffix Array

void solve() {
  SuffixArray sa(s);
  sa.constructSA();
  sa.computeLCP();
  int n = s.size();

  int length = 0, pos = -1;
  //fprintf(stderr, "i\tSA[i]\tLCP[i]\tSuffix\n");
  for (int i = 0; i <= n-m+1; i++) {
    //fprintf(stderr, "%d\t%d\t%d\t%s\n", i, sa.SA[i], sa.LCP[i], sa.T+sa.SA[i]);

    int t = (i == i+m-1)? n-i : n+1;
    if (i != i+m-1)
      for (int j = i+1; j < i+m && t; j++)
        t = min(t, sa.LCP[j]);
    length = max(length, t);
  }

  for (int i = 0; i <= n;) {
    if (sa.SA[i]+length > n) {
      i++;
      continue;
    }
    int j = i+1, curr = 0;
    while (j < sa.n && sa.LCP[j] >= length)
      curr = max(curr, sa.SA[j]), j++;

    if (j - i >= m) pos = max(pos, curr);
    i++;
  }

  if (length != 0)
    cout << length << ' ' << pos << endl;
  else
    cout << "none" << endl;
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);

  while (cin >> m) {
    if (m == 0)
      break;
    cin >> s;
    solve();
  }

  return 0;
}
