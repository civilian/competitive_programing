package uva.tomo5;

//Uva 507
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class JillRidesAgain {
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
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("JillRidesAgain"));
		readln();

		int numCases = nextInt();

		for (int casesId = 1; casesId <= numCases; casesId++) {
			readln();
			n = nextInt() - 1;
			for (int i = 0; i < n; i++) {
				readln();
				array[i] = nextInt();
			}
			long out = maxSumON();
			if (out > 0) {
				System.out
						.printf("The nicest part of route %d is between stops %d and %d\n",
								casesId, iniMax + 1, finMax + 2);
			} else {
				System.out.printf("Route %d has no nice parts\n", casesId);
			}
		}

	}

	static int MAX_SUM = 20007;
	static int[] array = new int[MAX_SUM];
	static int n;

	static int iniMax, finMax;
	static long maxSum;

	private static long maxSumON() {
		long sum = 0;
		int ini = 0, fin = 0;
		maxSum = -1000000000;
		iniMax = 0;
		finMax = 0;

		boolean first = true;

		for (int i = 0; i < n; i++) {
			sum += array[i];
			if (first
					|| (sum >= maxSum && ((finMax + 1) - iniMax) < ((i + 1) - ini))) {
				maxSum = sum;
				iniMax = ini;
				finMax = i;
				first = false;
			}
			if (sum < 0) {
				sum = 0;
				ini = i + 1;
			}
			fin = i;
		}
		return maxSum;
	}
}
