package uva.tomo7;

//Uva 763.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class FibinaryNumbers {
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

	// static void dbg(Object... o) {
	// output.println(Arrays.deepToString(o));
	// }

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("FibinaryNumbers"));

		fibDP(100 + 7);

		// dbg(fiboBigDP);
		for (int idCases = 0;; idCases++) {
			d1 = nextS();
			d2 = nextS();
			sum = parseFib(d1).add(parseFib(d2));
			// dbg(sum, d1, d2);
			// dbg(parseFib(d1), parseFib(d2));
			if (idCases != 0) {
				output.println();
			}
			output.println(toFibbinary(sum));
		}
	}

	static BigInteger res, sum;
	static int[] fibOut = new int[100 + 7];
	static int imax;
	static boolean prim;

	static StringBuilder ans;

	private static String toFibbinary(BigInteger sum) {
		ans = new StringBuilder();
		Arrays.fill(fibOut, 0);
		imax = -1;
		prim = true;
		fillFib(sum, 102);

		if (prim) {
			return "0";
		}
		for (int i = imax; i > -1; i--) {
			ans.append(fibOut[i]);
		}
		return ans.toString();
	}

	private static void fillFib(BigInteger sum, int idx) {
		if (sum.compareTo(BigInteger.ZERO) == 0) {
			return;
		}
		int i;
		for (i = idx; i > -1; i--) {
			if (fibDP(i).compareTo(sum) <= 0) {
				if (prim) {
					imax = i;
					prim = false;
				}
				fibOut[i] = 1;
				break;
			}
		}
		// dbg(i, fibDP(i), sum);
		fillFib(sum.subtract(fibDP(i)), idx);
	}

	private static BigInteger parseFib(String numero) {

		res = BigInteger.ZERO;
		// dbg(numero);
		for (int i = numero.length() - 1, K = 0; i > -1; K++, i--) {
			int digito = numero.charAt(i) - '0';

			res = res.add(fibDP(K).multiply(BigInteger.valueOf(digito)));
			// dbg(resultado);
		}
		return res;
	}

	private static String nextS() throws IOException {
		String resultado;
		try {
			resultado = next();
			if (resultado.equals("")) {
				throw new Exception();
			}
		} catch (Exception e) {
			if (readln() == null) {
				output.close();
				System.exit(0);
			}

			resultado = nextS();
		}
		return resultado;
	}

	static String d1, d2;
	static BigInteger smu;

	// FIBO DP BIGINTEGER

	static HashMap<Long, BigInteger> fiboBigDP = new HashMap<Long, BigInteger>();
	static {
		fiboBigDP.put(0l, BigInteger.ONE);
		fiboBigDP.put(1l, BigInteger.valueOf(2));
	}

	static BigInteger fibDP(long n) {/*
									 * O(n) el mas rapido para muchos
									 */
		BigInteger a = BigInteger.ONE, b = BigInteger.valueOf(2), c = fiboBigDP
				.get(n);
		long i;
		if (c == null) {
			for (i = 2; i <= n; i++) {
				c = a.add(b);
				a = b;
				b = c;
				fiboBigDP.put(i, c);
			}
		}// if
		return c;
	}

	// FIBO DP BIGINTEGER END
}
