package uva.tomo10;

//Uva 1056
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class DegreesofSeparation {
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
	// System.out.println(Arrays.deepToString(o));
	// }

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		input = new BufferedReader(new InputStreamReader(System.in));
		input = new BufferedReader(new FileReader("DegreesofSeparation"));

		AdjMatrix = new int[50 + 7][50 + 7];
		int from, to;
		for (int idCases = 1;; idCases++) {
			readln();
			V = P = nextInt();
			E = R = nextInt();
			if (P == 0 && R == 0) {
				break;
			}
			map.clear();
			for (int i = 0; i < P + 6; i++) {
				for (int j = 0; j < P + 6; j++) {

					AdjMatrix[i][j] = INF;
					if (i == j) {
						AdjMatrix[i][j] = 0;
					}
				}
			}
			for (int i = 0; i < R; i++) {
				from = mapear(nextS());
				to = mapear(nextS());
				if (to == from) {
					continue;
				}
				AdjMatrix[from][to] = 1;
				AdjMatrix[to][from] = 1;
			}

			floydWarshall();
			// dbg(AdjMatrix);
			int max = -INF;
			for (int i = 0; i < P; i++) {
				for (int j = 0; j < P; j++) {
					max = Math.max(max, AdjMatrix[i][j]);
				}
			}
			// dbg(AdjMatrix);
			if (max == 1000000000) {
				System.out.printf("Network %d: DISCONNECTED\n", idCases);
			} else {
				System.out.printf("Network %d: %d\n", idCases, max);
			}
			System.out.println();
		}
	}

	static int P, R;

	private static String nextS() throws IOException {
		String resultado;
		try {
			resultado = next();
		} catch (Exception e) {
			if (readln() == null)
				System.exit(0);

			resultado = nextS();
		}
		return resultado;
	}

	// MAPER SIN INVERSO
	static HashMap<String, Integer> map = new HashMap<String, Integer>();

	static int mapear(String next) {
		Integer idx = map.get(next);
		if (idx == null) {
			idx = map.size();
			map.put(next, idx);
		}
		return idx;
	}

	// MAPER SIN INVERSO END

	// FLOYD WARSHALL
	static int INF = 1000000000;
	static int V, E, from, to, w;// no necesariamente tienen que ser globales
									// porque todo se puede meter en el main
	static int AdjMatrix[][];

	static void floydWarshall() {
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
	}

	// FLOYD WARSHALL END
}
