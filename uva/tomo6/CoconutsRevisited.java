package uva.tomo6;

//Uva 616
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class CoconutsRevisited {
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
		input = new BufferedReader(new FileReader("CoconutsRevisited"));

		while (true) {
			cleido = nextIntS();
			if (cleido < 0) {
				break;
			}
			for (i = ((int) Math.sqrt(cleido)) + 2; i > 1; i--) {
				c = cleido;
				for (j = 0; j < i; j++) {
					// dbg(i, c);
					c--;
					// dbg("monkey", c);
					op = c / i;
					// dbg("partes", op);
					if (c != op * i) {
						break;
					}
					c = c - op;
					// dbg("mela encaleto", c);
				}
				if (j == i && c % i == 0) {
					output.printf("%d coconuts, %d people and 1 monkey\n",
							cleido, i);
					break;
				}
			}
			if (i == 1) {
				output.printf("%d coconuts, no solution\n", cleido);
			}
		}
		output.close();
	}

	private static int nextIntS() throws IOException {
		int resultado;
		try {
			resultado = nextInt();
		} catch (Exception e) {
			if (readln() == null)
				System.exit(0);

			resultado = nextIntS();
		}
		return resultado;
	}

	static int cleido, c, op, j, i;
}
