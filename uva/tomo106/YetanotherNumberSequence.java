package uva.tomo106;

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
import java.util.Scanner;
import java.util.StringTokenizer;

public class YetanotherNumberSequence {
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

	// FIBONACCI LOG(N), UTILIZA POTENCIACION DE MATRICES [[1 1] [0 1]]=MAT FIB

	private static int fibMasRapido(long n, int a, int b, int m) {/*
																 * funciona el
																 * log(n)
																 */
		int i, h, j, k, t;
		i = h = b;
		j = k = a;
		while (n > 0) {
			if (n % 2 == 1) { // if n is odd
				t = (j * h) % m;
				j = ((i * h) % m + (j * k) % m + t) % m;
				i = (i * k + t) % m;
			}
			t = (h * h) % m;
			h = (2 * k * h + t) % m;
			k = (k * k + t) % m;
			n = (int) n / 2;
		}
		return j;
	}

	// FIBONACCI LOG(N) END

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		// input = new BufferedReader(new InputStreamReader(System.in));
		// input = new BufferedReader(new
		// FileReader("YetanotherNumberSequence"));

		// InputReader in = new InputReader(System.in);
		Scanner in = new Scanner(new FileReader("YetanotherNumberSequence"));

		int tc = in.nextInt(), a, b, n, m;// m son cifras 1 a 4
		int modulo;
		int dp, rap;
		for (int idCases = 0; idCases < tc; idCases++) {
			a = in.nextInt();
			b = in.nextInt();
			n = in.nextInt();
			m = in.nextInt();
			modulo = (int) Math.pow(10, m);

			// papa:
			// for (int j = 0; j < 100; j++) {
			// for (int j2 = 0; j2 < 100; j2++) {
			// rap = fibMasRapido(n, j, j2, modulo);
			// System.out.println(rap);
			// }
			// }

			rap = fibMasRapido(n, a, b, modulo);
			System.out.println(rap);
		}

	}

}

class InputReader {

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int nextInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public char nextChar() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}
}