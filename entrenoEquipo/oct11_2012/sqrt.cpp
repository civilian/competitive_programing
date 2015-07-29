#include <cstdio>
#include <cmath>
#include <iostream>
#include <map>
#include <cstring>

using namespace std;

typedef long long int64;

const int maxn = 1000010;

int64 memo[maxn];
const int mod = 1e6;

int64 rec(int i) {
	if(memo[i] != -1) return memo[i];
	
	int a = (int)floor(i - sqrt(i));
	int b = (int)log(i);
	int c = (int)((int64)i * sin(i) * sin(i));
	
	return memo[i] = (rec(a) + rec(b) + rec(c)) % mod;
}

int main() {
	int n;
	memset(memo, -1, sizeof(memo));
	memo[0] = 1;
	for(int i = 1; i <= 1000000; i++) rec(i);
	while(scanf("%d", &n) && ~n) {
		printf("%lld\n", rec(n));
	}
}
