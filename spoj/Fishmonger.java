package spoj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Fishmonger {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l, " ");
		return l;
	}

	static String next() {
		return _stk.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static void dbg(Object... o) {
		output.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Fishmonger"));

		int t;
		while (true) {
			readln();
			n = nextInt();
			t = nextInt();
			if (n == 0 && t == 0) {
				break;
			}
			for (int i = 0; i < n + 7; i++) {
				for (int j = 0; j < t + 7; j++) {
					memo[i][j] = pDummy;
				}
			}
			
			for (int i = 0; i < n; i++) {
				readln();
				for (int j = 0; j < n; j++) {
					travelTime[i][j] = nextInt();
				}
			}
			readln();
			for (int i = 0; i < n; i++) {
				readln();
				for (int j = 0; j < n; j++) {
					toll[i][j] = nextInt();
				}
			}
			readln();

			Point ans = go(0, t);
			output.printf("%d %d\n", ans.x, ans.y);
		}
		output.close();
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
