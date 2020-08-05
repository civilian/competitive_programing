package uva.tomo3;

//Uva 369.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class CombinationsDP {

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

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Combinations"));

		for (int i = 0; i < memo.length; i++) {
			for (int j = 0; j < memo[1].length; j++) {
				memo[i][j] = -1;
			}
		}

		for (int casesId = 1;; casesId++) {
			readln();
			int N, M;
			N = nextInt();
			M = nextInt();
			if (N == 0 && M == 0)
				break;
			output.printf("%d things taken %d at a time is %d exactly.\n", N,
					M, C(N, M));
		}

		output.close();
		// C(30,14);

	}

	// COMBINATIONS DP

	static int MAX_N = 100 + 7;// definirlos bn
	static int MAX_K = 100 + 7;

	static long memo[][] = new long[MAX_N][MAX_K];

	static long C(int n, int k) {
		if (k > n) {
			return 0;
		}
		if (k > n - k)
			k = n - k; /* use smaller k */
		return Cr(n, k);
	}

	static long Cr(int n, int k) {
		if (k == 0 || k == n) {
			return 1;
		}
		if (memo[n][k] != -1) {
			return memo[n][k];
		}
		return memo[n][k] = Cr(n - 1, k - 1) + Cr(n - 1, k);
	}

	// COMBINATIONS DP END
}
