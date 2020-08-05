package uva.tomo103;
//Uva 10334.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.StringTokenizer;

public class RayThroughGlasses {
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

	// FIBO DP BIGINTEGER

	static HashMap<Long, BigInteger> fiboBigDP = new HashMap<Long, BigInteger>();
	static {
		fiboBigDP.put(0l, BigInteger.ONE);
		fiboBigDP.put(1l, BigInteger.ONE);
	}

	static BigInteger fibDPBigMemo(long n) {/*
											 * O(n) el mas rapido para muchos
											 */
		BigInteger a = BigInteger.ONE, b = BigInteger.ONE, c = fiboBigDP
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
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("RayThroughGlasses"));
		fibDPBigMemo(1000);
		int n;
		while(readln()!=null){
			n=nextInt();
			System.out.println(fibDPBigMemo(n+1));
		}

	}
	
	
}