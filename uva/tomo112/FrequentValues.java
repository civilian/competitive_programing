package uva.tomo112;

/*uva 11235 TLE->temporal*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class FrequentValues {
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

	// static PrintWriter output = new PrintWriter(new BufferedWriter(new
	// OutputStreamWriter(System.out)));

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("FrequentValues"));

		// long []ocurrencias= new int[22];
		for (int casesId = 0;; casesId++) {
			readln();

			int n = nextInt();
			if (n == 0)
				break;

			int[] ocurrencias = new int[n + 10];
			TreeSet<Integer> ultimos = new TreeSet<Integer>();

			int q = nextInt();
			readln();

			int ultimo = 1000000;
			int actual = 1000000;
			int cantidad = 1;

			for (int i = 1; i <= n; i++) {
				actual = nextInt();
				if (actual == ultimo) {
					cantidad++;
				} else {
					ultimos.add(new Integer(i));
					ultimo = actual;
					cantidad = 1;
				}
				ocurrencias[i] = cantidad;

			}
			// dbg("values",ocurrencias, " n ", n, " q ", q);
			// dbg(ultimos);
			Integer[] st = null;
			st = st_create(st, ocurrencias);//

			for (int i = 0; i < q; i++) {
				readln();
				int f = nextInt(), s = nextInt();

				// parcial value
				int valuep = -1;
				// comienzo del new intervalo
				int begNew = -1;

				// dbg("f ", f," s ", s);
				if (ocurrencias[f] != 1) {
					Integer j = ultimos.higher(ocurrencias[f]);
					j = (j == null) ? n : j;
					// si j se pasa la busqueda
					if (j > s)
						j = s;
					// else {
					// System.out.println(ocurrencias[j]-ocurrencias[f]+1);
					// continue;
					// }
					// dbg("j ", j, "F ", f);
					begNew = j;
					// hago la busqueda de el mayor hasta el maximo de el mismo
					valuep = ocurrencias[j - 1];
					// y lo calculo maximo menos el primero mas 1
					valuep = valuep - ocurrencias[f] + 1;
				}
				// saco el mayor de el resto de el arreglo
				// dbg("begNe2 ",begNew, "s ", s, ocurrencias);

				int value = Integer.MIN_VALUE;
				if (begNew != -1) {
					if (begNew != n)
						value = ocurrencias[st_rmq(st, ocurrencias, begNew, s)];
				} else
					value = ocurrencias[st_rmq(st, ocurrencias, f, s)];

				System.out.println((value > valuep) ? value : valuep);
			}
		}
		// output.close();
	}

	static// Segment Tree Library: The segment tree is stored like a heap array
	void st_build(Integer[] st, int[] ocurrencias, int vertex, int L, int R) {
		if (L == R) // as L == R, either one is fine
			st[vertex] = L; // store the index
		else { // recursively compute the values in the left and right subtrees
			int nL = 2 * vertex, nR = 2 * vertex + 1;
			st_build(st, ocurrencias, nL, L, (L + R) / 2);// derecha
			st_build(st, ocurrencias, nR, (L + R) / 2 + 1, R);// izquierda
			int lContent = st[nL], rContent = st[nR];
			long lValue = ocurrencias[lContent];
			long rValue = ocurrencias[rContent];
			// se queda con el indice de el menor igual de sus hijos el mas a la
			// izq.
			st[vertex] = (lValue >= rValue) ? lContent : rContent;
		}
	}

	static Integer[] st_create(Integer[] st, int[] ocurrencias) { // if original
																	// array
																	// size is
																	// N,
		// the required segment tree array length is 2*2^(floor(log2(N)) + 1);
		int len = (int) (2 * Math.pow(2.0, Math.floor((Math
				.log((double) ocurrencias.length) / Math.log(2.0)) + 1)));
		st = new Integer[len];
		Arrays.fill(st, 0);// create vector with length `len' and fill it with
							// zeroes
		st_build(st, ocurrencias, 1, 0, (int) ocurrencias.length - 1); // recursively
																		// build
																		// the
																		// segment
																		// tree
		return st;
	}

	static int st_rmq(Integer[] t, int[] ocurrencias, int vertex, int L, int R,
			int i, int j) {
		if (i > R || j < L)
			return -1; // current segment outside query range
		if (L >= i && R <= j)
			return t[vertex]; // current segment inside query range

		// compute the maximum position in the left and right part of the
		// interval
		int p1 = st_rmq(t, ocurrencias, 2 * vertex, L, (L + R) / 2, i, j);
		int p2 = st_rmq(t, ocurrencias, 2 * vertex + 1, (L + R) / 2 + 1, R, i,
				j);

		// return the position where the overall minimum is
		if (p1 == -1)
			return p2; // if we try to access segment outside query
		if (p2 == -1)
			return p1; // same as above
		return (ocurrencias[p1] >= ocurrencias[p2]) ? p1 : p2;
	}

	static int st_rmq(Integer[] st, int[] ocurrencias, int i, int j) { // overloading,
																		// simpler
																		// arguments
		return st_rmq(st, ocurrencias, 1, 0, (int) ocurrencias.length - 1, i, j);
	}
}
