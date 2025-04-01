#include <cstdio>
using namespace std;
//Uva 11764
int main() {
  		int tc;
  		  scanf("%d", &tc);
		tc++;
		int ant, act, n, low, high;
		for (int idCase = 1; idCase < tc; idCase++) {
			// readln();
          scanf("%d\n", &n);
			low = high = 0;
			// readln();
	    scanf("%d\n", &ant);
			for (int i = 1; i < n; i++) {
				  scanf("%d\n", &act);
				if (act > ant) {
					high++;
				} else if (act < ant) {
					low++;
				}
				ant = act;
			}

			printf("Case %d: %d %d\n", idCase, high, low);
		}
  return 0;
}
