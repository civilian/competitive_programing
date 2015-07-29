package CF.R135div2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class SpecialOfferSuperPrice999Bourles {
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

	static long nextInt() {
		return Long.parseLong(next());
	}

	// static void dbg(Object... o) {
	// System.out.println(Arrays.deepToString(o));
	// }

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader(
				"SpecialOfferSuperPrice999Bourles"));

		while (true) {
			if (readln() == null) {
				break;
			}
			numero = new StringBuilder(next());
			restar = new StringBuilder();
			d = nextInt();
			end = false;
			first = true;
			finito = false;

			for (int i = numero.length() - 1; i > -1; i--) {
				restar.insert(0, numero.charAt(i));
				if (Long.parseLong(restar.toString()) + 1 > d) {
					end = true;
					break;
				}
				first = false;
			}
			n = Long.parseLong(numero.toString());

//			// dbg("end, restar, numero, d no if//", end, restar, numero, d);
//			if (!end) {
//				restar.insert(0, (numero.charAt(0) - '0') - 1);
//				r = Long.parseLong(restar.toString());
//				if ((r + 1) < d) {
//					// r = Integer.parseInt(restar.toString());
//					System.out.println(n - (r + 1));
//					finito = true;
//				}
//			}
			if (!finito) {
				restar.replace(0, 1, "0");
				r = Long.parseLong(restar.toString());

				// dbg("end, restar, numero, d end t//", end, restar, numero,
				// d);
				if (first) {
					System.out.println(n - (r));
				} else {
					System.out.println(n - (r + 1));
				}

			}
		}
	}

	static StringBuilder numero, restar;
	static long d, n, r;
	static boolean end, first, finito;
}
