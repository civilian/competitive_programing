#include <cmath>
#include <iostream>
#include <cstdlib>
#include <cstdio>

using namespace std;

int main() {
	int n;
	freopen("silver.in", "r", stdin);
	//freopen("output.txt", "w", stdout);
	while(cin >> n && n) {
		int ans = floor(log(n) / log(2));
		cout << ans << endl;
	}
}


