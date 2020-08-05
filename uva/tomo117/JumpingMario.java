package uva.tomo117;

//Uva 11764
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.StringTokenizer;


public class JumpingMario {
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

	static byte nextInt() {
		return Byte.parseByte(next());
	}

	static void dbg(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		// input = new BufferedReader(new InputStreamReader(System.in));
		// input = new BufferedReader(new FileReader("JumpingMario"));

		InputReader in = new InputReader(System.in);

		// readln();
		byte tc = in.nextInt();
		tc++;
		byte ant, act, n, low, high;
		for (int idCase = 1; idCase < tc; idCase++) {
			// readln();
			n = in.nextInt();
			low = high = 0;
			// readln();
			ant = in.nextInt();
			for (int i = 1; i < n; i++) {
				act = in.nextInt();
				if (act > ant) {
					high++;
				} else if (act < ant) {
					low++;
				}
				ant = act;
			}

			System.out.printf("Case %d: %d %d\n", idCase, high, low);
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

	public byte nextInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		byte sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		byte res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return (byte) (res * sgn);
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
