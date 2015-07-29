package uva.tomo8;

//Uva 821.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class PageHopping {
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

	//
	// static void dbg(Object... o) {
	// System.out.println(Arrays.deepToString(o));
	// }

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("PageHopping"));

		int size = 107;
		AdjMatrix = new int[size][size];

		while (true) {
			from = nextIntS();
			to = nextIntS();
			if (from == 0 && to == 0) {
				break;
			}

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					AdjMatrix[i][j] = INF;
				}
				AdjMatrix[i][i] = 0;
			}
			map.clear();
			from = mappear(from);
			to = mappear(to);
			AdjMatrix[from][to] = 1;
			while (true) {
				from = nextIntS();
				to = nextIntS();
				if (from == 0 && to == 0) {
					break;
				}
				from = mappear(from);
				to = mappear(to);
				AdjMatrix[from][to] = 1;
			}
			V = map.size();
			// dbg(V);
			floydWarshall();
		}

	}

	// FLOYD WARSHALL
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(107);
	static int INF = 1000000000;
	static int V, E, from, to, w, caminos;// no necesariamente tienen que ser
											// globales
	static double average;
	static int idCases = 1;
	// porque
	// todo se puede meter en el main
	static int AdjMatrix[][];

	private static void floydWarshall() {
		caminos = 0;
		average = 0.0;
		// Floyd Warshall
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					AdjMatrix[i][j] = Math.min(AdjMatrix[i][j], AdjMatrix[i][k]
							+ AdjMatrix[k][j]);
				}
			}
		}
		// Mostrando:
		// dbg(AdjMatrix);
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (i != j) {
					average = average + AdjMatrix[i][j];
					caminos++;
				}
			}
		}
		// dbg(average);
		average = average / caminos;
		System.out.printf(
				"Case %d: average length between pages = %.3f clicks\n",
				idCases++, average);
	}

	// FLOYD WARSHALL END

	static int mappear(int id) {
		Integer idx = map.get(id);
		if (idx == null) {
			idx = map.size();
			map.put(id, idx);
		}
		return idx;
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
}
