package uva.tomo1;

/*Uva 151*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class PowerCrisis {
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
		 input=new BufferedReader(new InputStreamReader(System.in));
//		input = new BufferedReader(new FileReader("PowerCrisis"));
		
		LinkedList<Integer> orig = new LinkedList<Integer>();
		while (true) {
			readln();
			int N = nextInt();
			if (N == 0)
				break;

			
			orig.clear();
			for (int i = 0; i < N; i++) {
				orig.add(i + 1);
			}
			int salida = -1;

			for (int m = 1;; m++) {
//				dbg(m);
				copi = new LinkedList<Integer>(orig);

				if (apagarCiudades(m)) {
					salida = m;
					break;
				}
			}
			System.out.println(salida);
		}

	}

	static ListIterator<Integer> it;
	static LinkedList<Integer> copi;

	private static boolean apagarCiudades(int m) {
		it = copi.listIterator();

		while (true) {
			int ciu = nextElo();
			it.remove();
//			dbg("ciu ", ciu);
			if (ciu == 13) {
				if (copi.isEmpty()) {
					return true;
				} else {
					return false;
				}
			}

			for (int i = 0; i < m - 1; i++) {
				nextElo();
			}
		}

	}

	private static int nextElo() {
		int out;
		if (it.hasNext()) {
			out = it.next();
			return out;
		} else {
			it = copi.listIterator();
			out = it.next();
			return out;
		}
	}

}
