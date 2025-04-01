package maratonBook.graphs;
//Spoj Fishmonger pag 109 CP 2
import java.awt.Point;
import java.io.IOException;

import java.util.Locale;
import java.util.Scanner;

public class DAG_ConvertingGeneralGraphInDag {

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		int t;
		n = sc.nextInt();
		t = sc.nextInt();
		for (int i = 0; i < n + 7; i++) {
			for (int j = 0; j < t + 7; j++) {
				memo[i][j] = pDummy;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				travelTime[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				toll[i][j] = sc.nextInt();
			}
		}

		Point ans = go(0, t);
		System.out.printf("%d %d\n", ans.x, ans.y);
	}

	// 1014
	static int INF = 1000000000, maxN = 50 + 7, maxT = 1000 + 7;
	static int n;
	static Point pDummy = new Point(-1, -1);
	static Point[][] memo = new Point[maxN][maxT];
	static int[][] travelTime = new int[maxN][maxN],
			toll = new int[maxN][maxN];

	static Point go(int cur, int t_left) {
		if (t_left < 0) {
			return new Point(INF, INF);
		}
		if (cur == n - 1) {
			return new Point(0, 0);
		}
		if (!memo[cur][t_left].equals(pDummy)) {
			return memo[cur][t_left];
		}
		Point ans = new Point(INF, INF);
		for (int X = 0; X < n; X++) {
			if (cur != X) {
				Point nextCity = go(X, t_left - travelTime[cur][X]);
				if (nextCity.x + toll[cur][X] < ans.x) {
					ans.x = nextCity.x + toll[cur][X];
					ans.y = nextCity.y + travelTime[cur][X];
				}
			}
		}
		return memo[cur][t_left] = ans;
	}
}
