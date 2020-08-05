#include <bits/stdc++.h>

using namespace std;

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N = 3, p[N];
    for (int i = 0; i < N; i++) p[i] = i;
    for (int i = 0; i < (1 << N); i++){
    	for(int j = 0; j < N; j++){
    		if(((1 << j) & i) != 0)
    			printf("%d ", p[j]);
    	}
    	printf("\n");
    }
  
}
