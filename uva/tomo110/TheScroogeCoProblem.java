package uva.tomo110;
//Uva 11047.
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

public class TheScroogeCoProblem {
	static BufferedReader input;

	static StringTokenizer _stk;

	static String readln() throws IOException {
		String l = input.readLine();
		if (l != null)
			_stk = new StringTokenizer(l);
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
		input = new BufferedReader(new FileReader("TheScroogeCoProblem"));

		readln();
		int testCases = nextInt();
		AdjMatrix = new int[places][places];
		p = new int[places][places];
		for (int idCases = 0; idCases < testCases; idCases++) {
			readln();
			V = nextInt();
			readln();
			map.clear();
			invMap.clear();
			for (int i = 0; i < V; i++) {
				mappear(next());
			}
			for (int i = 0; i < V; i++) {
				readln();
				for (int j = 0; j < V; j++) {
					p[i][j] = i;
					AdjMatrix[i][j] = nextInt();
					AdjMatrix[i][j] = (AdjMatrix[i][j] == -1) ? INF
							: AdjMatrix[i][j];
				}
			}
			readln();
			R = nextInt();
			floydWarshallP();
			for (int i = 0; i < R; i++) {
				readln();
				employee = next();
				s = mappear(next());
				destini = mappear(next());
				if (AdjMatrix[s][destini] < INF) {
					output.printf(
							"Mr %s to go from %s to %s, you will receive %d euros\n",
							employee, invMap.get(s), invMap.get(destini),
							AdjMatrix[s][destini]);
					output.printf("Path:");
					if (s == destini) {
						output.printf("%s %s\n", invMap.get(s),
								invMap.get(destini));
					} else {
						output.printf("%s", invMap.get(s));
						printPath(s, destini);
						output.println();
					}
				} else {
					output.printf("Sorry Mr %s you can not go from %s to %s\n",
							employee, invMap.get(s), invMap.get(destini));
				}
			}
		}
		output.close();
	}

	// FLOYD WARSHALL IMPRIMIR CAMINO
	// Asi se imprime el camino:

	private static int mappear(String next) {
		Integer idx = map.get(next);
		if (idx == null) {
			idx = map.size();
			map.put(next, idx);
			invMap.put(idx, next);
		}
		return idx;
	}

	static int INF = 1000000000;
	static int V, E, from, to, w, R, s, destini;// no necesariamente tienen que
												// ser globales
	// porque todo se puede meter en el main
	static String employee;
	static int AdjMatrix[][];
	static int p[][];
	static int places = 99 + 7;
	static HashMap<String, Integer> map = new HashMap<String, Integer>(places);
	static HashMap<Integer, String> invMap = new HashMap<Integer, String>(
			places);

	static void floydWarshallP() {
		// Floyd Warshall
		for (int k = 0; k < V; k++) {
			// common error: remember that loop order is k->i->j
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (AdjMatrix[i][k] + AdjMatrix[k][j] < AdjMatrix[i][j]) {
						AdjMatrix[i][j] = AdjMatrix[i][k] + AdjMatrix[k][j];
						p[i][j] = p[k][j];
					}
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

	static void printPath(int i, int j) {
		if (i != j) {
			printPath(i, p[i][j]);// [i][j] tiene el papa de j
			output.printf(" %s", invMap.get(j));
		}
	}
}
