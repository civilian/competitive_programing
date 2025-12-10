package uva.tomo114;

//UVa 11450
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class WeddingShopping { /* UVa 11450 - Wedding Shopping - Top Down */

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

//	static void dbg(Object... o) {
//		System.out.println(Arrays.deepToString(o));
//	}

	public static void main(String[] args) throws IOException {// easy to code
																// if you are
																// already
																// familiar with
																// it
		Locale.setDefault(Locale.US);
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("WeddingShopping"));
		int i, j, TC, score;

		readln();
		TC = nextInt();
		while (TC-- > 0) {
			readln();
			M = nextInt();
			C = nextInt();
			for (i = 0; i < C; i++) {
				readln();
				K = nextInt();
				price[i][0] = K; // to simplify coding, we store K in
									// price[i][0]
				for (j = 1; j <= K; j++)
					price[i][j] = nextInt();
			}

			for (i = 0; i < 210; i++)
				for (j = 0; j < 25; j++) {
					memo[i][j] = -1; // initialize DP memo table
				}

			score = shop(M, 0); // start the top-down DP
			
//			dbg(memo);
			if (score < 0)
				System.out.printf("no solution\n");
			else
				System.out.printf("%d\n", score);
		}
	}

	private static int M, C, K;
	private static int[][] price = new int[25][25]; // price[g (<= 20)][model
													// (<= 20)]
	private static int[][] memo = new int[210][25]; // dp table memo[money (<=
													// 200)][g (<= 20)]

	private static int shop(int money, int g) {
//		if (g == C)
//			dbg(M-money,"g",g,"money",money);
		if (money < 0)
			return -1000000000; // fail, return a large negative number (1B)
		if (g == C)
			return M - money; // we have bought last garment, done
		if (memo[money][g] != -1)
			return memo[money][g]; // this state has been visited before
		int ans = -1000000000;
		for (int model = 1; model <= price[g][0]; model++) {
			// try all possible models
//			dbg("model",model,"g",g);
			ans = Math.max(ans, shop(money - price[g][model], g + 1));
//			dbg("ans",ans,"model",model,"g",g);
		}
//		dbg("g",g,"ans",ans,"money", money);
//		dbg(memo);
		return memo[money][g] = ans; // assign ans to dp table + return it!
	}
}
