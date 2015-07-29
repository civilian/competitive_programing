package uva.tomo4;
//Uva 436.
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

public class ArbitrageII {
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
		output.println(Arrays.deepToString(o));
	}

	static PrintWriter output = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("ArbitrageII"));

		AdjMatrix = new double[currencis][currencis];
		int idCases = 1;
		while (true) {
			readln();
			V = nextInt();
			if (V == 0) {
				break;
			}
			map.clear();
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					AdjMatrix[i][j] = 0.0;
				}
				AdjMatrix[i][i] = 1.0;
			}

			for (int i = 0; i < V; i++) {
				readln();
				mappear(next());
			}
			readln();
			m = nextInt();
			for (int i = 0; i < m; i++) {
				readln();
				from = mappear(next());
				w = Double.parseDouble(next());
				to = mappear(next());
				AdjMatrix[from][to] = w;
			}
			readln();// vacio
			floydWarshall();
			can = false;
			for (int i = 0; i < V; i++) {
				if (AdjMatrix[i][i] > 1.0) {
					can = true;
					break;
				}
			}
			// dbg(AdjMatrix);
			if (can) {
				output.printf("Case %d: Yes\n", idCases++);
			} else {
				output.printf("Case %d: No\n", idCases++);
			}

		}
		output.close();
	}

	// FLOYD WARSHALL

	private static int mappear(String next) {
		Integer idx = map.get(next);
		if (idx == null) {
			idx = map.size();
			map.put(next, idx);
		}
		return idx;
	}

	static int INF = 1000000000;
	static int V, E, from, to, m, currencis = 30 + 7;// no necesariamente
														// tienen que ser
														// globales
	static HashMap<String, Integer> map = new HashMap<String, Integer>(
			currencis);
	static boolean can;
	// porque todo se puede meter en el main
	static double w;
	static double AdjMatrix[][];

	static void floydWarshall() {
		// Floyd Warshall
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					AdjMatrix[i][j] = Math.max(AdjMatrix[i][j], AdjMatrix[i][k]
							* AdjMatrix[k][j]);
				}
			}
		}

		// // Mostrando:
		// for (int i = 0; i < V; i++) {
		// for (int j = 0; j < V; j++) {
		// output.printf("APSP(%d, %d) = %d\n", i, j, AdjMatrix[i][j]);
		// }
		// }
	}

	// FLOYD WARSHALL END
}
