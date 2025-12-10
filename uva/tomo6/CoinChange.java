package uva.tomo6;

/*Uva 674*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class CoinChange {
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
//		 input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("CoinChange"));

		for (int i = 0; i < memo.length; i++) {
			for (int j = 0; j < memo[0].length; j++) {
				memo[i][j] = -1;
			}
		}
		// dbg(memo);
		while (readln() != null) {
			System.out.printf("%d\n", ways(0, nextInt()));
		}
	}

	static int N = 5, V, coinValue[] = { 1, 5, 10, 25, 50 },
			memo[][] = new int[6][7500];

	// N and coinValue are fixed for this problem, max V is 7489

	static int ways(int type, int value) {
		if (value == 0)
			return 1;
		if (value < 0 || type == N)
			return 0;
		if (memo[type][value] != -1)
			return memo[type][value];
		return memo[type][value] = ways(type + 1, value)
				+ ways(type, value - coinValue[type]);
	}

}
