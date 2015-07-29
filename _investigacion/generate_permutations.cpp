#include <bits/stdc++.h>

using namespace std;

int main() {
  int p[10], N = 10;
  for (int i = 0; i < N; i++) p[i] = i;
  do{
	  for(int i = 0; i < N; i++) printf("%d ", p[i]);
	  printf("\n");
  } while(next_permutation(p, p))
}
