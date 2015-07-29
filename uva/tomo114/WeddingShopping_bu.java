package uva.tomo114;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class WeddingShopping_bu { /* UVa 11450 - Wedding Shopping - Bottom Up */

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
//
//	public static void main1(String[] args) throws IOException {// easy to code
//																// if you are
//																// already
//																// familiar with
//																// it
//		Locale.setDefault(Locale.US);
//		// input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("WeddingShopping"));
//		int i, j, l, TC, M, C, K;
//		int[][] price = new int[25][25]; // price[g (<= 20)][model (<= 20)]
//		Boolean[][] can_reach = new Boolean[210][25]; // can_reach table[money
//														// (<= 200)][g (<= 20)]
//		readln();
//		TC = nextInt();
//		while (TC-- > 0) {
//			readln();
//			M = nextInt();
//			C = nextInt();
//			for (i = 0; i < C; i++) {
//				readln();
//				K = nextInt();
//				price[i][0] = K; // to simplify coding, we store K in
//									// price[i][0]
//				for (j = 1; j <= K; j++)
//					price[i][j] = nextInt();
//			}
//
//			for (i = 0; i < 210; i++)
//				for (j = 0; j < 25; j++)
//					can_reach[i][j] = false; // clear everything
//
//			for (i = 1; i <= price[0][0]; i++)
//				// initial values
//				if (M - price[0][i] >= 0)
//					can_reach[M - price[0][i]][0] = true; // if only using first
//															// garment g = 0
//
//			for (j = 1; j < C; j++)
//				// for each remaining garment (note: this is written in column
//				// major)
//				for (i = 0; i < M; i++)
//					if (can_reach[i][j - 1]) // if can reach this state
//						for (l = 1; l <= price[j][0]; l++)
//							if (i - price[j][l] >= 0) // flag the rest
//								can_reach[i - price[j][l]][j] = true; // as long
//																		// as it
//																		// is
//																		// feasible
//
//			for (i = 0; i <= M && !can_reach[i][C - 1]; i++)
//				; // the answer is in the last column
//
//			if (i == M + 1)
//				System.out.printf("no solution\n"); // nothing in this last
//													// column has its bit turned
//													// on
//			else
//				System.out.printf("%d\n", M - i);
//		}
//	}

	public static void main(String[] args) throws IOException {// easy to code
		// if you are
		// already
		// familiar with
		// it
		Locale.setDefault(Locale.US);
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("WeddingShopping"));
		int i, j, l, TC, M, C, K;
		int[][] price = new int[25][25]; // price[g (<= 20)][model (<= 20)]
		Boolean[][] can_reach = new Boolean[25][210]; // can_reach table[money
		// (<= 200)][g (<= 20)]
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

			for (i = 0; i < 25; i++)
				for (j = 0; j < 210; j++)
					can_reach[i][j] = false; // clear everything

			for (i = 1; i <= price[0][0]; i++)
				// initial values
				if (M - price[0][i] >= 0)
					can_reach[0][M - price[0][i]] = true; // if only using first
			// garment g = 0
//			dbg(can_reach);
			for (j = 1; j < C; j++){
				// for each remaining garment (note: this is written in column
				// major)
				dbg("curr",j,"prev",j-1);
				for (i = 0; i < M; i++)
					if (can_reach[j-1][i]) // if can reach this state
						for (l = 1; l <= price[j][0]; l++)
							if (i - price[j][l] >= 0) // flag the rest
								can_reach[j][i - price[j][l]] = true; // as long
				
//				dbg(can_reach);
			// as it
			// is
			// feasible
			}
			dbg(C-1);
			for (i = 0; i <= M && !can_reach[C-1][i]; i++)
				; // the answer is in the last column

			if (i == M + 1)
				System.out.printf("no solution\n"); // nothing in this last
				// column has its bit turned
				// on
			else
				System.out.printf("%d\n", M - i);
		}
	}
}
