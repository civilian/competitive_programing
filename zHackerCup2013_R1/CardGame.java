package zHackerCup2013_R1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.*;

public class CardGame {

	static BufferedReader input;
	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null) {
			_stk = new StringTokenizer(l, " ");
		}
		return l;
	}

	static String next() {
		return _stk.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static long nextLong() {
		return Long.parseLong(next());
	}

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("CardGame"));
		readln();
		int TC = nextInt();
		ArrayList<Long> a = new ArrayList<Long>(10007);
		int N, K;
		long suma;
		for (int idCases = 0; idCases < TC; idCases++) {
			a.clear();
			readln();
			N = nextInt();
			K = nextInt();
			readln();
			for (int i = 0; i < N; i++) {
				a.add(nextLong());
			}

			Collections.sort(a);
			suma = 0l;
			for (int i = K - 1; i < N; i++) {
				suma = (suma + (a.get(i) * C(i, K - 1))) % 1000000007l;
			}

			System.out.printf("Case #%d: %d\n", idCases + 1, suma);
		}

		// output.print("ja");
		// output.close();
	}

	// COMBINATIONS GCD

	static long gcd(long a, long b) {
		while (b > 0) {
			a = a % b;
			a ^= b;
			b ^= a;
			a ^= b;
		}
		return a;
	}

	static long C(long n, long k) {
		if (n < k) {
			return 0l;
		}
		if (k == 0 || n == 0) {
			return 0l;
		}
		/*
		 * si no es Long ( objeto ) no se puede mandar por referencia
		 */
		long numerator = 1, denominator = 1, toMul, toDiv, i, tmpGcd = 1;
		if (k > n - k) {
			k = n - k; /*
						 * use smaller k
						 */
		}
		for (i = k; i > 0; i--) {
			toMul = n - k + i;
			toDiv = i;

			// dbg(toMul, toDiv);
			tmpGcd = gcd(toMul, toDiv); /*
										 * always divide before multiply
										 */
			toMul /= tmpGcd;
			toDiv /= tmpGcd;

			tmpGcd = gcd(numerator, toDiv); /*
											 * always divide before multiply
											 */
			numerator /= tmpGcd;
			toDiv /= tmpGcd;

			tmpGcd = gcd(toMul, denominator); /*
											 * always divide before multiply
											 */
			toMul /= tmpGcd;
			denominator /= tmpGcd;

			numerator = (numerator * toMul) % 1000000007;
			denominator = (denominator * toDiv) % 1000000007;

		}
		if (denominator == 0) {
			return 0;
		}
		return numerator / denominator;
	}
	// COMBINATIONS GCD END
}
