package uva.tomo102;

//Uva 10219.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Findtheways {
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

	static void dbg(Object... objects) {
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("Findtheways"));

		while (true) {
			if (readln() == null) {
				break;
			}
			in = nextInt();
			ik = nextInt();
			n = Math.max(in, ik);
			k = Math.min(in, ik);

			ways = C1(n, k);
			System.out.println(ways.toString().length());
		}
	}

	static int n, k, in, ik;
	static BigInteger ways;

	static BigInteger C1(long n, long k) {
		/*
		 * si no es Long ( objeto ) no se puede mandar por referencia
		 */
		long i;
		BigInteger numerator = BigInteger.ONE, denominator = BigInteger.ONE, toMul, toDiv, tmpGcd = BigInteger.ONE;
		if (k > n - k)
			k = n - k; /* use smaller k */
		for (i = k; i > 0; i--) {
			toMul = BigInteger.valueOf(n - k + i);
			toDiv = BigInteger.valueOf(i);

			// dbg(toMul, toDiv);
			tmpGcd = toDiv.gcd(toMul);
			toMul = toMul.divide(tmpGcd);
			toDiv = toDiv.divide(tmpGcd);

			tmpGcd = numerator.gcd(toDiv);
			numerator = numerator.divide(tmpGcd);
			toDiv = toDiv.divide(tmpGcd);

			tmpGcd = toMul.gcd(denominator);
			toMul = toMul.divide(tmpGcd);
			denominator = denominator.divide(tmpGcd);

			numerator = numerator.multiply(toMul);
			denominator = denominator.multiply(toDiv);
		}
		return numerator.divide(denominator);
	}

	// COMBINATIONS GCD END

}
