package uva.tomo9;

/*Uva 991.*/
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

public class SafeSalutations {
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
		output.println(Arrays.deepToString(o));
	}
	
	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("SafeSalutations"));

		int n = 0;
		for (int caseId = 0;; caseId++) {
			if (readln() == null) {
				break;
			}
			if (caseId != 0) {
				readln();
				output.println();
			}
			n = nextInt();
			output.println(catalanNumber(n));
		}
		output.close();
	}

	// CATALAN NUMBERS

	static HashMap<Integer, BigInteger> hCatalanNumbers = new HashMap<Integer, BigInteger>();

	private static BigInteger catalanNumber(int n) {
		if (!hCatalanNumbers.containsKey(n)) {
			hCatalanNumbers.put(n,
					fact(2 * n).divide((fact(n + 1).multiply(fact(n)))// divide
							));// put
		}
		return hCatalanNumbers.get(n);
	}

	/*
	 * esta este metodo que aprovecha los calculos anteriores, pero tiene costo
	 * n para cada n con el for que tiene, y la otras alternativas del metodo
	 * necesita calculos decimales. private static double catR1(long n){
	 * if(catsR1.containsKey(n)){ return catsR1.get(n); } double sum = 0;
	 * for(int i = 0; i < n; i++){ sum += catR1(i) * catR1(n - 1 - i); }
	 * catsR1.put(n, sum); return sum; }
	 */

	/* Aux */
	static HashMap<Long, BigInteger> hMFactoriales = new HashMap<Long, BigInteger>();

	static {
		hMFactoriales.put(0L, BigInteger.ONE);
		hMFactoriales.put(1L, BigInteger.ONE);
	}

	static BigInteger fact(long n) {
		return fact(n, hMFactoriales);
	}

	static BigInteger fact(long n, HashMap<Long, BigInteger> listaFactoriales) {
		BigInteger tmp = hMFactoriales.get(n);
		if (tmp != null)
			return tmp;
		/* si no tiene nada utilizo tmp para las operaciones */
		/* hago la recursion */
		tmp = fact(n - 1, listaFactoriales);
		/* multiplico */
		tmp = tmp.multiply(BigInteger.valueOf(n));
		/* memoizo */
		listaFactoriales.put(n, tmp);
		return tmp;
	}

	// CATALAN NUMBERS END
}
