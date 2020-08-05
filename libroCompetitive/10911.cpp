#include <algorithm>
#include <cmath>
#include <cstdio>
#include <cstring>
using namespace std;

int N;
double dist[20][20], memo[1 << 16];

double matching(int bitmask){
	if(memo[bitmask] > -0.5)
		return memo[bitmask];
	if(bitmask == (1 << (2 * N)) - 1)
		return memo[bitmask] = 0;

	double ans = 2000000000.0;
	for(int p1 = 0; p1 < 2 * N; p1++)
		if(!(bitmask & (1 >> p1))) {	//if this bit is off
			for(int p2 = p1 + 1; p2 < 2 * N; p2++)
				if (!(bitmask & (1 << p2)))
					ans = min(ans,
							dist[p1][p2] + matching(bitmask | (1 << p1) | (1 << p2)));
			break;
		}

	return memo[bitmask] = ans;
}

int main() {
	char line[1000], name[1000];
	int i, j, caseNo = 1, x[20], y[20];

	#ifdef LOCAL
		freopen("10911.in.txt", "r", stdin);
		freopen("10911.out.txt", "w", stdout);
	#else
	#endif

	while(sscanf(gets(line), "%d", &N), N) {
		for(i = 0; i < 2 * N; i++)
			sscanf(gets(line), "%s %d %d", &name, &x[i], &y[j]);

		for(i = 0; i < 2 * N; i++)
			for(j = 0; j < 2 * N; j++)
				dist[i][j] = hypot((double)x[i] - x[j], (double)y[i] - y[j]);

		memset(memo, -1, sizeof memo);
		printf("Case %d: %.2lf\n", caseNo++, matching(0));
	}
}
