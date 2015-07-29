#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int MAX_N = 1010;

char T[MAX_N];
int n;
int RA[MAX_N], tempRA[MAX_N];
int SA[MAX_N], tempSA[MAX_N];
int c[MAX_N];

int Phi[MAX_N], PLCP[MAX_N], LCP[MAX_N];

void countingSort(int k) {
  int i, sum, maxi = max(300, n);
  memset(c, 0, sizeof c);
  for(i = 0; i < n; i++)
    c[i + k < n ? RA[i + k] : 0]++;
  for(i = sum = 0; i < maxi; i++) {
    int t = c[i]; c[i] = sum; sum += t;
  }
  for(i = 0; i < n; i++)
    tempSA[c[SA[i] + k < n ? RA[SA[i] + k] : 0]++] = SA[i];
  for(i = 0; i < n; i++)
    SA[i] = tempSA[i];
}

void constructSA() {
  int i, k, r;
  T[n++] = '.';
  for(i = 0; i < n; i++) RA[i] = T[i] - '.';
  for(i = 0; i < n; i++) SA[i] = i;
  for(k = 1; k < n; k <<= 1) {
    countingSort(k);
    countingSort(0);
    tempRA[SA[0]] = r = 0;
    for(i = 1; i < n; i++)
      tempRA[SA[i]] = (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
    for(i = 0; i < n; i++)
      RA[i] = tempRA[i];
  }
}

void computeLCP() {
  int i, L;
  Phi[SA[0]] = -1;
  for(i = 1; i < n; i++)
    Phi[SA[i]] = SA[i-1];
  for(i = L = 0; i < n; i++) {
    if(Phi[i] == -1) {
      PLCP[i] = 0;
      continue;
    }
    while(T[i + L] == T[Phi[i] + L]) L++;
    PLCP[i] = L;
    L = max(L-1, 0);
  }
  for(i = 1; i < n; i++)
    LCP[i] = PLCP[SA[i]];
  LCP[n] = 0;
}

int main() {
  int t;
  sscanf(gets(T), "%d", &t);
  
  while(t--) {
    n = (int)strlen(gets(T));
    //cerr << T << endl;
    
    constructSA();
    computeLCP();
    
    int best = 2;
    
    //for(int i = 1; i <= n; i++)
    //  cerr << i << " " << SA[i] << " " << LCP[i] << " " << (T+SA[i]) << endl;
    
    for(int i = 2; i < n; i++)
      if(LCP[i] > LCP[best]) best = i;
    
    //cerr << "best = " << best << endl;
    int best_length = LCP[best];
    
    if(best_length == 0) {
      printf("No repetitions found!\n");
      continue;
    }
    string ans = T+SA[best];
    ans = ans.substr(0, best_length);

    int cnt = 0;
    int k = best;
    while(k >= 0) {
      string curr = T+SA[k];
      curr = curr.substr(0, best_length);
      if(curr == ans) {
        cnt++;
      }
      else {
        break;
      }
      k--;
    }
    
    k = best+1;
    while(k < n) {
      string curr = T+SA[k];
      curr = curr.substr(0, best_length);
      if(curr == ans) {
        cnt++;
      }
      else {
        break;
      }
      k++;
    }
    
    //cout << ans << " " << cnt+1 << endl;
    printf("%s %d\n", ans.c_str(), cnt);
  }
  
  return 0;
}
