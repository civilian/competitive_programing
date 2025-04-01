package uva.tomo120;

//Uva  12024.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.StringTokenizer;

public class Hats {
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
		// input = new BufferedReader(new InputStreamReader(System.in));
		// input = new BufferedReader(new FileReader("Hats"));
		InputReader in = new InputReader(System.in);
		// readln();
		// int tc = nextInt();
		int tc = in.readInt();
		int n;
		long ans;
		fact(12 + 3);
		for (int i = 0; i < tc; i++) {
			// readln();
			n = in.readInt();
			ans = dearrangements(n);
			System.out.printf("%d/%d\n", ans, fact);
		}

	}

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

	public int readInt() {
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

	public char readCharacter() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}
}