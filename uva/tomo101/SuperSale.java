package uva.tomo101;

/*Uva 10130*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class SuperSale {
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
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("Knapsack01"));
		int i, T, G, ans;
		readln();
		T = nextInt();
		while (--T > -1) {
			for (int k = 0; k < memo.length; k++)
				for (int j = 0; j < memo[0].length; j++)
					memo[k][j] = -1;

			readln();
			N = nextInt();
			for (i = 0; i < N; i++) {
				readln();
				V[i] = nextInt();
				W[i] = nextInt();
			}

			ans = 0;
			readln();
			G = nextInt();
			// dbg("N ",N, "G ",G, " V ", V, " W ", W, " memo ",memo);
			while (G-- != 0) {
				readln();
				MW = nextInt();
				// dbg("MW ",MW);
				ans += value(0, MW);
			}

			System.out.printf("%d\n", ans);
		}

	}

	static int MAX_N = 1010;
	static int MAX_W = 40;

	static int N, MW, V[] = new int[MAX_N], W[] = new int[MAX_N],
			memo[][] = new int[MAX_N][MAX_W];

	static int value(int id, int w) {
		if (id == N || w == 0)
			return 0;
		if (memo[id][w] != -1)
			return memo[id][w];
		if (W[id] > w)
			return memo[id][w] = value(id + 1, w);
		return memo[id][w] = Math.max(value(id + 1, w),
				V[id] + value(id + 1, w - W[id]));
	}
}
