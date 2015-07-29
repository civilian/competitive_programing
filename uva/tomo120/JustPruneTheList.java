package uva.tomo120;

//Uva 12049 TLE TODAVIA
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.StringTokenizer;

public class JustPruneTheList {
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
		// input = new BufferedReader(new FileReader("JustPruneTheList"));
		InputReader in = new InputReader(System.in);
		int testCases;
		int x;
		int y;
		HashMap<Integer, Integer> lista = new HashMap<Integer, Integer>(10001);
		// readln();
		// testCases = nextInt();
		testCases = in.readInt();
		int cantidad;
		int tmp;
		for (int i = 0; i < testCases; i++) {
			// readln();
			// x = nextInt();
			// y = nextInt();
			x = in.readInt();
			y = in.readInt();
			lista.clear();
			// readln();
			cantidad = x;
			for (int j = 0; j < x; j++) {
				act = in.readInt();
				cuantos = lista.get(act);
				if (cuantos == null) {
					cuantos = 0;
				}
				lista.put(act, ++cuantos);
			}
			// readln();
			for (int j = 0; j < y; j++) {
				tmp = in.readInt();
				cuantos = lista.get(tmp);
				if (cuantos != null && cuantos > 0) {
					cantidad--;
					lista.put(tmp, --cuantos);
				} else {
					cantidad++;
				}
			}
			System.out.println(cantidad);
		}

	}

	static Integer cuantos;
	static int act;

	static class InputReader {

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
}