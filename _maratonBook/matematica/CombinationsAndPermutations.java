package maratonBook.matematica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*C(N,K) means how many ways that N things can be taken K at a time. 
 This can be a great challenge when N and/or K become very large. 
 Combination of (N,K) is defined as: 
 N!
 --------- 
 (N-K)!*K! 
 +++++++++++++++++++++++++++++++++++
 (permutaciones posicion=el orden importa.)
 Permutaciones con repeticion N^K(N cosas saco K de ellas)
 N!
 -sin repeticion:    ------  (N cosas saco K de ellas sin repetir)
 (N-K)!

 */

public class CombinationsAndPermutations {

	private static void dbg(Object... objects) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.deepToString(objects));
	}

	public static void main(String[] args) {
		// System.out.println(C(49 ,6 ));
		// System.out.println(P(4,2));
		// long resultado=0;
		// System.out.println(C(18,11));
		// for (int i = 0; i < 19; i++) {
		// resultado+=C(18 , i);
		// }
		// System.out.println(resultado);

		for (int i = 0; i < memo.length; i++) {
			for (int j = 0; j < memo[1].length; j++) {
				memo[i][j] = -1;
			}
		}

		Scanner sc = new Scanner(System.in);
		int k, n;
		long lk, ln;
		while (true) {
			ln = n = sc.nextInt();
			lk = k = sc.nextInt();
			System.out.println(C(n, k));// DP
			System.out.println(C(ln, lk));// con la division
		}
	}

	// PERMUTACIONES LA COSA ES QUE NECESITO UNO TENGA EN CUENTA EL ORDEN

	/* no esta probado y no es la formula hay que hacerlo */
	static long P(int n, int k) {
		long numerator = 1, denominator = 1, toMul, toDiv, i;
		if (k > n / 2)
			k = n - k; /* use smaller k */
		for (i = k; i > 0; i--) {
			toMul = n - k + i;
			toDiv = i;
			// divByGcd(toMul, toDiv); /* always divide before multiply */
			// divByGcd(numerator, toDiv);
			// divByGcd(toMul, denominator);
			numerator *= toMul;
			denominator *= toDiv;
		}
		return numerator / denominator;
	}

	// PERMUTACIONES LA COSA ES QUE NECESITO UNO TENGA EN CUENTA EL ORDEN

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
		if (n < k)
			return 0;
		/*
		 * si no es Long ( objeto ) no se puede mandar por referencia
		 */
		long numerator = 1, denominator = 1, toMul, toDiv, i, tmpGcd = 1;
		if (k > n - k)
			k = n - k; /* use smaller k */
		for (i = k; i > 0; i--) {
			toMul = n - k + i;
			toDiv = i;

			// dbg(toMul, toDiv);
			tmpGcd = gcd(toMul, toDiv); /* always divide before multiply */
			toMul /= tmpGcd;
			toDiv /= tmpGcd;

			tmpGcd = gcd(numerator, toDiv); /* always divide before multiply */
			numerator /= tmpGcd;
			toDiv /= tmpGcd;

			tmpGcd = gcd(toMul, denominator); /* always divide before multiply */
			toMul /= tmpGcd;
			denominator /= tmpGcd;

			numerator *= toMul;
			denominator *= toDiv;

		}
		return numerator / denominator;
	}

	// COMBINATIONS GCD END

	// COMBINATIONS DP

	static int MAX_N = 1010;// definirlos bn
	static int MAX_K = 1000;

	static int memo[][] = new int[MAX_N][MAX_K];

	static int C(int n, int k) {
		if (k > n) {
			return 0;
		}
		if (k > n - k) {
			k = n - k; /* use smaller k */
		}
		return Cr(n, k);
	}

	static int Cr(int n, int k) {
		if (k == 0 || k == n) {
			return 1;
		}
		if (memo[n][k] != -1) {
			return memo[n][k];
		}
		return memo[n][k] = Cr(n - 1, k - 1) + Cr(n - 1, k);
	}

	// COMBINATIONS DP END

	// COMBINATIONS DEARRANGEMENTS
	// La cantidad de combinaciones en donde ningun valor corresponde a su
	// puesto

	static long fact;
	static HashMap<Long, Long> hMFactoriales = new HashMap<Long, Long>();

	static {
		hMFactoriales.put(0L, 1L);
		hMFactoriales.put(1L, 1L);
	}

	static Long fact(long n) {
		Long tmp = hMFactoriales.get(n);
		if (tmp != null)
			return tmp;
		tmp = fact(n - 1) * n;
		hMFactoriales.put(n, tmp);
		return tmp;
	}

	static long dearrangements(long n) {
		long ans = 0;
		int sign = -1;
		fact = fact(n);
		for (int i = 0; i <= n; i++) {
			sign *= -1;
			ans += (sign * (fact / fact(i)));
		}
		return ans;
	}
	// COMBINATIONS DEARRANGEMENTS END
}
