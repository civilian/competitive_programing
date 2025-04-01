package uva.otros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class WorstWeatherEver {
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
		// input=new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("WorstWeatherEver"));
		for (int casesId = 0;; casesId++) {
			readln();
			int iannos = nextInt();

			TreeMap<Integer, Integer> info = new TreeMap<Integer, Integer>();

			lluviaAnno[] lluvia = new lluviaAnno[iannos];
			int anterior = Integer.MAX_VALUE;
			for (int i = 0; i < iannos; i++) {
				readln();
				int anno = nextInt();

				int cant = nextInt();
				if (anterior + 1 != anno)
					info.put(anno, anno);

				mapear(anno);
				anterior = anno;
				lluvia[i] = new lluviaAnno(cant, anno);

			}

			readln();
			int q = nextInt();

			if (iannos == 0 && q == 0)
				break;
			Integer[] st = null;
			st = st_create(st, lluvia);//
			// hago la busqueda y si el f es el menor

			dbg(mapper);
			dbg(lluvia);

			for (int i = 0; i < q; i++) {
				readln();
				boolean maybe = false;
				Map.Entry<Integer, Integer> f = mapper.floorEntry(nextInt());
				int segundo = nextInt();
				Map.Entry<Integer, Integer> s = mapper.ceilingEntry(segundo);
				if (s == null) {
					maybe = true;
					s = mapper.floorEntry(segundo);
				}

				int grande = st_rmq(st, lluvia, f.getValue(), s.getValue());
				// System.out.println(grande!=f||grande!=s);
				if (grande == f.getValue() || grande == s.getValue()) {
					Integer malo = info.higherKey(f.getKey());

					dbg("malo ", malo, " f ", f, " info ", info, "s.value ",
							s.getValue());
					if (malo == null) {
						if (maybe)
							System.out.println("maybe");
						else
							System.out.println("true");
					} else {
						if (s.getKey() < malo)
							System.out.println("true");
						else
							System.out.println("maybe");
					}
				} else
					System.out.println("false");
			}
			System.out.println();
			readln();
			mapper.clear();
		}

	}

	static TreeMap<Integer, Integer> mapper = new TreeMap<Integer, Integer>();

	static Integer mapear(Integer i) {
		Integer elo = mapper.get(i);
		if (elo == null)
			mapper.put(i, mapper.size());

		return mapper.get(i);
	}

	static// Segment Tree Library: The segment tree is stored like a heap array
	void st_build(Integer[] st, lluviaAnno[] lluvia, int vertex, int L, int R) {
		if (L == R) // as L == R, either one is fine
			st[vertex] = L; // store the index
		else { // recursively compute the values in the left and right subtrees
			int nL = 2 * vertex, nR = 2 * vertex + 1;
			st_build(st, lluvia, nL, L, (L + R) / 2);// derecha
			st_build(st, lluvia, nR, (L + R) / 2 + 1, R);// izquierda
			int lContent = st[nL], rContent = st[nR];
			int lValue = lluvia[lContent].cantidad, rValue = lluvia[rContent].cantidad;
			// se queda con el indice de el menor igual de sus hijos el mas a la
			// izq.
			st[vertex] = (lValue > rValue) ? lContent : rContent;
		}
	}

	static Integer[] st_create(Integer[] st, lluviaAnno[] lluvia) { // if
																	// original
																	// array
																	// size is
																	// N,
		// the required segment tree array length is 2*2^(floor(log2(N)) + 1);
		int len = (int) (2 * Math.pow(2.0, Math.floor((Math
				.log((double) lluvia.length) / Math.log(2.0)) + 1)));
		st = new Integer[len];
		Arrays.fill(st, 0);// create vector with length `len' and fill it with
							// zeroes
		st_build(st, lluvia, 1, 0, (int) lluvia.length - 1); // recursively
																// build the
																// segment tree
		return st;
	}

	static int st_rmq(Integer[] t, lluviaAnno[] lluvia, int vertex, int L,
			int R, int i, int j) {
		if (i > R || j < L)
			return -1; // current segment outside query range
		if (L >= i && R <= j)
			return t[vertex]; // current segment inside query range

		// compute the minimum position in the left and right part of the
		// interval
		int p1 = st_rmq(t, lluvia, 2 * vertex, L, (L + R) / 2, i, j);
		int p2 = st_rmq(t, lluvia, 2 * vertex + 1, (L + R) / 2 + 1, R, i, j);

		// return the position where the overall minimum is
		if (p1 == -1)
			return p2; // if we try to access segment outside query
		if (p2 == -1)
			return p1; // same as above
		return (lluvia[p1].cantidad > lluvia[p2].cantidad) ? p1 : p2;
	}

	static int st_rmq(Integer[] st, lluviaAnno[] lluvia, int i, int j) { // overloading,
																			// simpler
																			// arguments
		return st_rmq(st, lluvia, 1, 0, (int) lluvia.length - 1, i, j);
	}
}

class lluviaAnno {
	int cantidad;
	int faltaInfo;

	public lluviaAnno(int cantidad, int faltaInfo) {
		this.cantidad = cantidad;
		this.faltaInfo = faltaInfo;
	}

	@Override
	public String toString() {
		return "[cant=" + cantidad + ", faltInf=" + faltaInfo + "]";
	}

}
