package uva.tomo100;

//Uva 10003
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class CuttingSticks { /* Cutting Sticks */
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

	// static PrintWriter output = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
//		 input = new BufferedReader(new FileReader("CuttingStciks"));
		int i, j, l, n;

		while (true) {
			readln();
			l = nextInt();
			if (l == 0)
				break;

			arr[0] = 0;
			readln();
			n = nextInt();

			readln();
			for (i = 1; i <= n; i++)
				arr[i] = nextInt();
			arr[n + 1] = l;

			for (i = 0; i < 55; i++)
				for (j = 0; j < 55; j++)
					memo[i][j] = -1;

			// start with left = 0 and right = n + 1
			 System.out.printf("The minimum cutting is %d.\n", cut(0, n + 1));
		}
	}

	private static int[] arr = new int[55];
	private static int[][] memo = new int[55][55];

	private static int cut(int left, int right) {
		if (left + 1 == right)
			return 0;
		if (memo[left][right] != -1)
			return memo[left][right];

		int ans = 2000000000;
		int tmp = 2000000000;
		for (int i = left + 1; i < right; i++) {
			tmp = cut(left, i) + cut(i, right) + (arr[right] - arr[left]);
			ans = Math.min(ans, tmp);
		}
		return memo[left][right] = ans;
	}
}
