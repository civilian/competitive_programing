package uva.tomo1;

//Uva 113
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Locale;
import java.util.StringTokenizer;

public class PowerOfCryptography {
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

	static BigInteger nextBigInteger() {
		return new BigInteger(next());
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("PowerOfCryptography"));

		for (;;) {
			if (readln() == null)
				return;
			int n = nextInt();
			readln();
			BigInteger p = nextBigInteger();
			System.out.printf("%d\n", raizIntBig(p, n));
		}
	}

	static int n;
	static BigInteger p, nint;

	static int loint, hiint, midint, ans;
	static BigInteger val;
	static int comp;
	static BigInteger out;

	private static int raizIntBig(BigInteger n, int m) {
		loint = 0;
		hiint = 1000000010;
		nint = n;
		midint = 0;
		ans = 0;
		
		while (loint < hiint) {
			midint = loint + ((hiint - loint) / 2);
			// esta es la parte
			// tricky
			// System.out.println("mid = "+midint.toString());
			val = pow(midint, m);
			comp = val.compareTo(nint);
			if (comp >= 0) {// cumple el predicado
				hiint = midint;
				ans = hiint;
			} else
				loint = midint + 1;
		}
		// falta el complain de cuando en el low no se cumple

		return ans;

	}

	private static BigInteger pow(int midint, int m) {
		out = BigInteger.ONE;
		for (int i = 0; i < m; i++) {
			out = out.multiply(BigInteger.valueOf(midint));
		}
		return out;
	}
}
