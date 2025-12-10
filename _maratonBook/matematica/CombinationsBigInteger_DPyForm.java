package maratonBook.matematica;

import java.math.BigInteger;
import java.util.Scanner;

public class CombinationsBigInteger_DPyForm {

	public static void main(String[] args) {

		for (int i = 0; i < memo.length; i++) {
			for (int j = 0; j < memo[1].length; j++) {
				memo[i][j] = BigInteger.valueOf(-1);
			}
		}

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), k = sc.nextInt();
		System.out.println(C(n, k));
		System.out.println(Cdp(n, k));
	}

	// COMBINATIONS GCD

	static BigInteger C(long n, long k) {
		/*
		 * si no es Long ( objeto ) no se puede mandar por referencia
		 */
		if (k > n) {
			return BigInteger.ZERO;
		}
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

	// COMBINATIONS DP

	static int MAX_N = 1000;// definirlos bn
	static int MAX_K = 1000;

	static BigInteger memo[][] = new BigInteger[MAX_N][MAX_K];

	private static BigInteger Cdp(int n, int k) {
		if (k > n) {
			return BigInteger.ZERO;
		}
		if (k > n - k) {
			k = n - k; /* use smaller k */
		}
		return Cr(n, k);
	}

	static BigInteger Cr(int n, int k) {
		if (k == 0 || k == n) {
			return BigInteger.ONE;
		}
		if (memo[n][k].compareTo(BigInteger.valueOf(-1)) != 0) {
			return memo[n][k];
		}
		return memo[n][k] = Cr(n - 1, k - 1).add(Cr(n - 1, k));
	}

	// COMBINATIONS DP END
}
